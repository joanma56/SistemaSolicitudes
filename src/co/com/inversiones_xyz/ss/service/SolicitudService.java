package co.com.inversiones_xyz.ss.service;

import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import org.springframework.transaction.annotation.Transactional;

import co.com.inversiones_xyz.ss.dao.ProductoDAO;
import co.com.inversiones_xyz.ss.dao.SeguimientoDAO;
import co.com.inversiones_xyz.ss.dao.SolicitudDAO;
import co.com.inversiones_xyz.ss.dao.SucursalDAO;
import co.com.inversiones_xyz.ss.dao.TipoSolicitudDAO;
import co.com.inversiones_xyz.ss.dao.UsuarioDAO;
import co.com.inversiones_xyz.ss.dto.Producto;
import co.com.inversiones_xyz.ss.dto.Seguimiento;
import co.com.inversiones_xyz.ss.dto.Solicitud;
import co.com.inversiones_xyz.ss.dto.Sucursal;
import co.com.inversiones_xyz.ss.dto.TipoSolicitud;
import co.com.inversiones_xyz.ss.dto.Usuario;
import co.com.inversiones_xyz.ss.enconde.Cifrar;
import co.com.inversiones_xyz.ss.excepcion.DaoException;
import co.com.inversiones_xyz.ss.excepcion.ServiceException;
import co.com.inversiones_xyz.ss.validacion.Validaciones;
/**
 * Clase que permite contiene la logica de negocio para una solicitud
 * 
 * @author 
 * 		Juan Carlos Estrada
 * 		Rafael Luna Pérez
 * 		Joan Manuel Rodríguez
 * @version 1.0.0
 * 			12/05/2016
 */
@Transactional
public class SolicitudService {
	
	private SolicitudDAO solicitudDAO;
	private ProductoDAO productoDAO;
	private TipoSolicitudDAO tipoSolicitudDAO;
	private SucursalDAO sucursalDAO;
	private UsuarioDAO usuarioDAO;
	private SeguimientoDAO seguimientoDAO;

	/**
	 * Permite generar una solicitud una vez el cliente haya ingresado los parametros relacionados a continuacion
	 * @param radicado: radicado de una solicitud
	 * @param nombres: nombres del cliente
	 * @param apellidos: apellidos del cliente
	 * @param correo: correo del cliente
	 * @param telefono: telefono del cliente
	 * @param celular: celular del cliente
	 * @param descripcion: descripcion de la solicitud
	 * @param codigoSucursal: codigo de la sucursal donde se hizo la compra
	 * @param codigoTipo: codigo del tipo de solicitud
	 * @param codigoProducto: codigo del producto que adquirió el cliente
	 * @param codigoSeguimiento: codigo del seguimiento asociado a la solicitud
	 * @param userName: nombre de usuario responsable de la solicitud
	 * @return instancia a la nueva solicitud almacenada en el sistema
	 * @throws DaoException cuando ocurre un error al instanciar un seguimiento o un usuario en la base de datos
	 * @throws ServiceException cuando ocurre alguno de los parametros ingresados no son validos
	 */
	public Solicitud generarSolicitud(int radicado, String nombres, String apellidos, String correo,
			String telefono, String celular, String descripcion, String codigoSucursal, int codigoTipo,
			int codigoProducto, int codigoSeguimiento, String userName) throws DaoException, ServiceException{
		
		Solicitud solicitud = null;
		Seguimiento seguimiento = null;
		
		if(Validaciones.isTextoVacio(nombres)){
			throw new ServiceException("Los nombres del cliente no puede ser nula, ni una cadena de caracteres vacia");
		}
		if(Validaciones.isTextoVacio(apellidos)){
			throw new ServiceException("Los apellidos del cliente no puede ser nula, ni una cadena de caracteres vacia");
		}
		if(Validaciones.isTextoVacio(correo)){
			throw new ServiceException("El correo electrónico del cliente no puede ser nula, ni una cadena de caracteres vacia");
		}
		if(Validaciones.isTextoVacio(telefono)){
			throw new ServiceException("El telefono del cliente no puede ser nula, ni una cadena de caracteres vacia");
		}
		
		if(!Validaciones.isEmail(correo)){
			throw new ServiceException("El correo electrónico del cliente debe ser válido");
		}
		
		if(solicitudDAO.obtener(radicado) != null){
			throw new ServiceException("Ya existe una solicitud con numero de radicado " + radicado +  " en el sistema");
		}
		
		seguimiento = new Seguimiento();
		seguimiento.setId(codigoSeguimiento);
		seguimiento.setFechaCreacion(new Date());
		seguimiento.setEstado((byte)0);
		Usuario usuario = usuarioDAO.obtener(userName);
		seguimiento.setResponsable(usuario);
		if(null!=seguimientoDAO.insertar(seguimiento)){
			solicitud = new Solicitud();
			solicitud.setRadicado(radicado);
			solicitud.setNombres(nombres);
			solicitud.setApellidos(apellidos);
			solicitud.setCorreo(correo);
			solicitud.setTelefono(telefono);
			solicitud.setCelular(celular);
			solicitud.setDescripcion(descripcion);
			Sucursal sucursal = sucursalDAO.obtener(codigoSucursal);
			solicitud.setSucursal(sucursal);
			TipoSolicitud tipoSolicitud = tipoSolicitudDAO.obtener(codigoTipo);
			solicitud.setTipoSolicitud(tipoSolicitud);
			Producto producto = productoDAO.obtener(codigoProducto);
			solicitud.setProducto(producto);
			solicitud.setSeguimiento(seguimiento);
		}
		return solicitud;
	}
	/**
	 * Permite consultar una lista de todas las solicitudes que hay en el sistema
	 * @return instancia de una lista de las solicitudes consultadas
	 * @throws DaoException cuando ocurre un error al consultar las solicitudes en la BD
	 */
	public List<Solicitud> obtener() throws DaoException{
		return solicitudDAO.obtener();
	}
	
	/**
	 * Permite consultar una solicitud en el sisdema dado su numero de radicado
	 * @param radicado: numero de radicado de la solicitud
	 * @return instancia de solicitud consultada
	 * @throws DaoException cuando ocurre un error al consultar la solicitud en la BD
	 * @throws ServiceException cuando el parametro ingresado no es valido
	 */
	public Solicitud consultarSolicitud(int radicado) throws DaoException, ServiceException{
		if(0 == radicado){
			throw new ServiceException("El radicado de la solicitud a buscar no puede ser 0");
		}
		return solicitudDAO.obtener(radicado);
	}
	
	/**
	 * Permite responder una solicitud dado el id de su correspondiente seguimiento
	 * Responder solicitud consiste en actualizar el estado y fecha de respondida del correspiente seguimiento
	 * @param id: codigo del seguimiento
	 * @throws DaoException cuando ocurre un error en instanciando el seguimiento en la BD
	 * @throws ServiceException cuando se ingresa un parametro invalido
	 */
	public void responderSolicitud(int id) throws DaoException, ServiceException{
		if(0 == id){
			throw new ServiceException("El id del seguimiento a buscar no puede ser 0");
		}
		Seguimiento seguimiento = seguimientoDAO.obtener(id);
		if(seguimiento!=null){
			seguimiento.setFechaRespondida(new Date());
			seguimiento.setEstado((byte)1);
			seguimientoDAO.modificarSeguimiento(seguimiento);
		}
	}//Validar el usuario que responde la solicitud
	
	/**
	 * Permite reasignar el responsable de una solicitud dado el id de su correspondiente seguimiento
	 * reasignar responsable consiste en modificar el usuario (Gerente de cuentas) por un usuario diferente y actualizar la fecha 
	 * @param id: codigo del seguimiento
	 * @param userName: login del nuevo usuario responsable
	 * @throws DaoException cuando ocurre un error al instanciar un seguimiento en la BD
	 * @throws ServiceException cuando se ingresan parametros no válidos
	 */
	public void reasignarSolicitud(int id, String userName) throws DaoException, ServiceException{
		if(0 == id){
			throw new ServiceException("El id del seguimiento a buscar no puede ser 0");
		}
		Seguimiento seguimiento = seguimientoDAO.obtener(id);
		seguimiento.setFechaReasignada(new Date());
		Usuario user = usuarioDAO.obtener(userName);
		seguimiento.setResponsable(user);
		seguimientoDAO.modificarSeguimiento(seguimiento);
	}
	
	public String seguirSolicitudes(Integer radicado, String userName)throws DaoException, ServiceException{
		if(0==radicado && userName == "GerenteCuentas"){
			throw new ServiceException("El radicado no puede ser nulo o una cadena vacia");
		}
		Solicitud solicitud = solicitudDAO.obtener(radicado);
		Seguimiento seguimiento = solicitud.getSeguimiento();
		return seguimiento.getSatisfaccion();
	}
	
	public Boolean autenticarUsuario(String userName, String clave) throws DaoException, ServiceException{
		
		Cifrar cifrar = new Cifrar();
		
		if(Validaciones.isTextoVacio(userName)){
			throw new ServiceException("El nombre de usuario no puede ser nula, ni una cadena de caracteres vacia");
		}
		
		if(Validaciones.isTextoVacio(clave)){
			throw new ServiceException("La clave del usuario no puede ser nula, ni una cadena de caracteres vacia");
		}
		
		Usuario usuario = usuarioDAO.obtener(userName);
		if(usuario == null){
			throw new ServiceException("Usuario o contraseña no válidos");
		}
		
		if(!cifrar.encrypt(clave).equals(usuario.getPassword())){
			throw new ServiceException("Usuario o contraseña no válidos");
		}
		
		return Boolean.TRUE;
	}

	public SolicitudDAO getSolicitudDAO() {
		return solicitudDAO;
	}

	public void setSolicitudDAO(SolicitudDAO solicitudDAO) {
		this.solicitudDAO = solicitudDAO;
	}

	public ProductoDAO getProductoDAO() {
		return productoDAO;
	}

	public void setProductoDAO(ProductoDAO productoDAO) {
		this.productoDAO = productoDAO;
	}

	public TipoSolicitudDAO getTipoSolicitudDAO() {
		return tipoSolicitudDAO;
	}

	public void setTipoSolicitudDAO(TipoSolicitudDAO tipoSolicitudDAO) {
		this.tipoSolicitudDAO = tipoSolicitudDAO;
	}

	public SucursalDAO getSucursalDAO() {
		return sucursalDAO;
	}

	public void setSucursalDAO(SucursalDAO sucursalDAO) {
		this.sucursalDAO = sucursalDAO;
	}

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	public SeguimientoDAO getSeguimientoDAO() {
		return seguimientoDAO;
	}

	public void setSeguimientoDAO(SeguimientoDAO seguimientoDAO) {
		this.seguimientoDAO = seguimientoDAO;
	}
	
	
}
