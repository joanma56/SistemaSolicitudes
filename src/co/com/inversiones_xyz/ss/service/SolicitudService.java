package co.com.inversiones_xyz.ss.service;

import java.util.Date;
import java.util.List;

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

@Transactional
public class SolicitudService {
	
	private SolicitudDAO solicitudDAO;
	private ProductoDAO productoDAO;
	private TipoSolicitudDAO tipoSolicitudDAO;
	private SucursalDAO sucursalDAO;
	private UsuarioDAO usuarioDAO;
	private SeguimientoDAO seguimientoDAO;
	
	public void generarSolicitud(int radicado, String nombres, String apellidos, String correo,
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
		seguimientoDAO.insertar(seguimiento);
		
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

		solicitudDAO.insertar(solicitud);
	}

	public List<Solicitud> obtener() throws DaoException{
		return solicitudDAO.obtener();
	}
	
	public Solicitud obtener(int radicado) throws DaoException, ServiceException{
		if(0 != radicado){
			throw new ServiceException("El radicado de la solicitud a buscar no puede ser 0");
		}
		
		return solicitudDAO.obtener(radicado);
	}
	
	public void responderSolicitud(int id) throws DaoException, ServiceException{
		if(0 == id){
			throw new ServiceException("El id del seguimiento a buscar no puede ser 0");
		}
		Seguimiento seguimiento = seguimientoDAO.obtener(id);
		seguimiento.setFechaRespondida(new Date());
		seguimiento.setEstado((byte)1);
		seguimientoDAO.modificarSeguimiento(seguimiento);
	}
	
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
