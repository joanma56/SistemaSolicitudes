package co.com.inversiones_xyz.ss.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import co.com.inversiones_xyz.ss.dao.ProductoDAO;
import co.com.inversiones_xyz.ss.dao.RolDAO;
import co.com.inversiones_xyz.ss.dao.SeguimientoDAO;
import co.com.inversiones_xyz.ss.dao.SolicitudDAO;
import co.com.inversiones_xyz.ss.dao.SucursalDAO;
import co.com.inversiones_xyz.ss.dao.TipoSolicitudDAO;
import co.com.inversiones_xyz.ss.dao.UsuarioDAO;
import co.com.inversiones_xyz.ss.dto.Producto;
import co.com.inversiones_xyz.ss.dto.Rol;
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
 * @author Juan Carlos Estrada Rafael Luna Pérez Joan Manuel Rodríguez
 * @version 1.0.0 12/05/2016
 */
@Transactional
public class SolicitudService {

	private SolicitudDAO solicitudDAO;
	private ProductoDAO productoDAO;
	private TipoSolicitudDAO tipoSolicitudDAO;
	private SucursalDAO sucursalDAO;
	private UsuarioDAO usuarioDAO;
	private SeguimientoDAO seguimientoDAO;
	private RolDAO rolDAO;
	private Rol rol;
	private UsuarioService userService;


	/**
	 * Permite generar una solicitud una vez el cliente haya ingresado los
	 * parametros relacionados a continuacion:
	 * 
	 * @param nombres
	 *            nombres del cliente
	 * @param apellidos
	 *            apellidos del cliente
	 * @param correo
	 *            correo del cliente
	 * @param telefono
	 *            telefono del cliente
	 * @param celular
	 *            celular del cliente
	 * @param descripcion
	 *            descripcion de la solicitud
	 * @param codigoSucursal
	 *            codigo de la sucursal donde se hizo la compra
	 * @param codigoTipo
	 *            codigo del tipo de solicitud
	 * @param codigoProducto
	 *            codigo del producto que adquirió el cliente
	 *            
	 * @return instancia a la nueva solicitud almacenada en el sistema
	 * 
	 * @throws DaoException
	 *             cuando ocurre un error al instanciar un seguimiento o un
	 *             usuario en la base de datos
	 * @throws ServiceException
	 *             cuando ocurre alguno de los parametros ingresados no son
	 *             validos
	 */
	public Solicitud generarSolicitud(String nombres, String apellidos, String correo, String telefono,
			String celular, String descripcion, String codigoSucursal, 
			int codigoTipo, int codigoProducto) throws DaoException, ServiceException {

		Solicitud solicitud = null;
		Seguimiento seguimiento = null;

		if (Validaciones.isTextoVacio(nombres)) {
			throw new ServiceException("Los nombres del cliente no puede ser nula, ni una cadena de caracteres vacia");
		}
		if (Validaciones.isTextoVacio(apellidos)) {
			throw new ServiceException(
					"Los apellidos del cliente no puede ser nula, ni una cadena de caracteres vacia");
		}
		if (Validaciones.isTextoVacio(correo)) {
			throw new ServiceException(
					"El correo electrónico del cliente no puede ser nula, ni una cadena de caracteres vacia");
		}
		if (Validaciones.isTextoVacio(telefono)) {
			throw new ServiceException("El telefono del cliente no puede ser nula, ni una cadena de caracteres vacia");
		}

		if (!Validaciones.isEmail(correo)) {
			throw new ServiceException("El correo electrónico del cliente debe ser válido");
		}

		seguimiento = new Seguimiento();
		seguimiento.setFechaCreacion(new Date());
		seguimiento.setEstado((byte) 0);
		Usuario usuario = userService.ObtieneGerenteCuentas();
		seguimiento.setResponsable(usuario);
		seguimiento = seguimientoDAO.insertar(seguimiento);
		if (null != seguimiento) {
			solicitud = new Solicitud();
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
			solicitudDAO.insertar(solicitud);
		}
		return solicitud;
	}
	
	/**
	 * Permite al gerente de cuentas hacer seguimiento a todas las solicitudes
	 * activas en el sistema. Validaremos primero que el usuario ingresado 
	 * tenga el rol de gerente de cuentas coorporativas.
	 * 
	 * @param nombreUsuario
	 *            nombre de usuario para obtener el rol y verificar que sea el
	 *            gerente de cuentas
	 * @param codigoRol
	 *            codigo del rol gerente de cuentas
	 * @return la lista de todas las solicitudes activas en el sistema
	 * @throws DaoException
	 *             cuando ocurre un error en la consulta en la BD
	 * @throws ServiceException
	 *             cuando el parametro ingresado no es valido
	 */
	public List<Solicitud> seguirSolicitudes(String nombreUsuario) throws DaoException, ServiceException {
		if (Validaciones.isTextoVacio(nombreUsuario)) {
			throw new ServiceException("El nombre de usuario no puede ser nulo, ni una cadena de caracteres vacia");
		}
		List<Solicitud> solicitudes = null;
		if (userService.EsGerenteCuentas(nombreUsuario)) {
			solicitudes=solicitudDAO.obtener();
		} else{
			throw new ServiceException("usuario no es el gerente de cuentas coorporativas");
		}
		return solicitudes;
	}

	/**
	 * Permite consultar las solicitudes que tiene asignadas el usuario que
	 * realiza la consulta para posteriormente mostrarlas en pantalla.
	 * 
	 * @param nombreUsuario
	 *            login del usuario que consulta
	 * @return instancia de una lista de las solicitudes consultadas
	 * @throws DaoException
	 *             cuando ocurre un error al consultar las solicitudes en la BD
     * @throws ServiceException
     * 			   cuando se ingresa mal el parámetro
	 *             cuando no existe usuario en el sistema con ese nombreUsuario
	 * 
	 */
	public List<Solicitud> obtenerSolicitudes(String nombreUsuario) throws DaoException, ServiceException {
		if (Validaciones.isTextoVacio(nombreUsuario)) {
			throw new ServiceException("El nombre de usuario no puede ser nulo, ni una cadena de caracteres vacia");
		}
		Usuario usuario=usuarioDAO.obtener(nombreUsuario);
		List<Solicitud> lista=new ArrayList<Solicitud>();
		if(usuario!=null){
			List<Seguimiento> seguimientos=seguimientoDAO.obtenerPorUsuario(usuario);
			if(seguimientos!=null && !seguimientos.isEmpty()){
				Solicitud solicitud;
				for(Seguimiento seguimiento:seguimientos){
					solicitud=solicitudDAO.obtenerPorSeguimiento(seguimiento);
					if(solicitud!=null){
						lista.add(solicitud);
					}
				}
			}else{
				throw new ServiceException("No existen solicitudes asociadas a este usuario");
			}
		}else{
			throw new ServiceException("No existe usuario en el sistema con ese nombre de usuario");
		}
		return lista;
	}

	/**
	 * Permite consultar una solicitud en el sistema dado su numero de radicado
	 * 
	 * @param radicado:
	 *            numero de radicado de la solicitud
	 * @param nombreUsuario
	 *            nombre de usuario del usuario que consulta
	 *            
	 * @return instancia de solicitud consultada con los detalles de la misma
	 * 
	 * @throws DaoException
	 *             cuando ocurre un error al consultar la solicitud en la BD
	 * @throws ServiceException
	 *             cuando el parametro ingresado no es valido
	 */
	public Solicitud consultarSolicitud(int radicado, String nombreUsuario)
			throws DaoException, ServiceException {
		if (0 == radicado) {
			throw new ServiceException("El radicado de la solicitud a buscar no puede ser 0");
		}
		if (Validaciones.isTextoVacio(nombreUsuario)) {
			throw new ServiceException("El nombre de usuario no puede ser nulo, ni una cadena de caracteres vacia");
		}
		Solicitud solicitud = solicitudDAO.obtener(radicado);
		Usuario usuarioResponsable = null;
		Usuario usuarioConsultor= null;
		if (null != solicitud){
			usuarioResponsable = solicitud.getSeguimiento().getResponsable();
			usuarioConsultor=usuarioDAO.obtener(nombreUsuario);
			if(usuarioConsultor!=null){
				if(usuarioResponsable.getNombreUsuario()==usuarioConsultor.getNombreUsuario()){
					return solicitud;
				}else{
					throw new ServiceException("Usuario"+ nombreUsuario +
							"no tiene permisos para ver detalles de esta solicitud");
				}
			}else{
				throw new ServiceException("No existe usuario con ese userName");
			}
		}else{
			throw new ServiceException("No existe solicitud con ese número de radicado");
		}
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

	public RolDAO getRolDAO() {
		return rolDAO;
	}

	public void setRolDAO(RolDAO rolDAO) {
		this.rolDAO = rolDAO;
	}
	
	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public UsuarioService getUserService() {
		return userService;
	}

	public void setUserService(UsuarioService userService) {
		this.userService = userService;
	}

}
