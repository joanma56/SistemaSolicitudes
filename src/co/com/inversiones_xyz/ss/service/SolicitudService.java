package co.com.inversiones_xyz.ss.service;

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

	/**
	 * Permite generar una solicitud una vez el cliente haya ingresado los
	 * parametros relacionados a continuacion
	 * 
	 * @param radicado
	 *            radicado de una solicitud
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
	 * @param codigoSeguimiento
	 *            codigo del seguimiento asociado a la solicitud
	 * @param nombreUsuario
	 *            nombre de usuario responsable de la solicitud
	 * @return instancia a la nueva solicitud almacenada en el sistema
	 * @throws DaoException
	 *             cuando ocurre un error al instanciar un seguimiento o un
	 *             usuario en la base de datos
	 * @throws ServiceException
	 *             cuando ocurre alguno de los parametros ingresados no son
	 *             validos
	 */
	public Solicitud generarSolicitud(int radicado, String nombres, String apellidos, String correo, String telefono,
			String celular, String descripcion, String codigoSucursal, int codigoTipo, int codigoProducto,
			int codigoSeguimiento, String nombreUsuario) throws DaoException, ServiceException {

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

		if (solicitudDAO.obtener(radicado) != null) {
			throw new ServiceException("Ya existe una solicitud con numero de radicado " + radicado + " en el sistema");
		}
		seguimiento = new Seguimiento();
		seguimiento.setId(codigoSeguimiento);
		seguimiento.setFechaCreacion(new Date());
		seguimiento.setEstado((byte) 0);
		Usuario usuario = usuarioDAO.obtener(nombreUsuario);
		if (null != usuario)
			seguimiento.setResponsable(usuario);
		if (null != seguimientoDAO.insertar(seguimiento)) {
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
		return solicitud;
	}

	/**
	 * Permite al gerente de cuentas hacer seguimiento a todas las solicitudes
	 * activas en el sistema
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
	public void seguirSolicitudes(String nombreUsuario, String codigoRol) throws DaoException, ServiceException {
		if (Validaciones.isTextoVacio(nombreUsuario)) {
			throw new ServiceException("El nombre de usuario no puede ser nulo, ni una cadena de caracteres vacia");
		}
		if (Validaciones.isTextoVacio(codigoRol)) {
			throw new ServiceException("El codigo de rol no puede ser nulo, ni una cadena de caracteres vacia");
		}
		List<Solicitud> solicitudes = null;
		Seguimiento seguimiento = null;
		rol = rolDAO.obtener(codigoRol);
		if (null != rol) {
			if ((rol.getNombre()).equals(usuarioDAO.obtener(nombreUsuario).getRol().getNombre())) {
				solicitudes = solicitudDAO.obtener();
				for (Solicitud solicitud : solicitudes) {
					seguimiento = solicitud.getSeguimiento();
					// Pendientes por operar las solicitudes y los seguimientos
				}
			}
		} else
			throw new ServiceException("No se encontró rol correspondiente al codigoRol ingresado");
	}

	/**
	 * Permite consultar las solicitudes que tiene asignadas el usuario que
	 * realiza la consulta
	 * 
	 * @param nombreUsuario
	 *            login del usuario que consulta
	 * @return instancia de una lista de las solicitudes consultadas
	 * @throws DaoException
	 *             cuando ocurre un error al consultar las solicitudes en la BD
	 */
	public List<Solicitud> consultarSolicitudes(String nombreUsuario) throws DaoException, ServiceException {
		if (Validaciones.isTextoVacio(nombreUsuario)) {
			throw new ServiceException("El nombre de usuario no puede ser nulo, ni una cadena de caracteres vacia");
		}
		return solicitudDAO.obtenerPorUsuario(nombreUsuario);
	}

	/**
	 * Permite consultar una solicitud en el sisdema dado su numero de radicado
	 * 
	 * @param radicado:
	 *            numero de radicado de la solicitud
	 * @param nombreUsuario
	 *            nombre de usuario del usuario que consulta
	 * @param codigoRol
	 *            codigo del rol gerente de cuentas
	 * @return instancia de solicitud consultada
	 * @throws DaoException
	 *             cuando ocurre un error al consultar la solicitud en la BD
	 * @throws ServiceException
	 *             cuando el parametro ingresado no es valido
	 */
	public Solicitud consultarSolicitud(int radicado, String nombreUsuario, String codigoRol)
			throws DaoException, ServiceException {
		if (0 == radicado) {
			throw new ServiceException("El radicado de la solicitud a buscar no puede ser 0");
		}
		if (Validaciones.isTextoVacio(nombreUsuario)) {
			throw new ServiceException("El nombre de usuario no puede ser nulo, ni una cadena de caracteres vacia");
		}
		if (Validaciones.isTextoVacio(codigoRol)) {
			throw new ServiceException("El codigo de rol no puede ser nulo, ni una cadena de caracteres vacia");
		}
		Solicitud solicitud = solicitudDAO.obtener(radicado);
		Usuario usuarioResponsable = null, usuarioConsultor;
		if (null != solicitud)
			usuarioResponsable = solicitud.getSeguimiento().getResponsable();
		usuarioConsultor = usuarioDAO.obtener(nombreUsuario);
		rol = rolDAO.obtener(codigoRol);
		if (null != rol) {
			if ((rol.getNombre()).equals(usuarioDAO.obtener(nombreUsuario).getRol().getNombre())
					|| usuarioResponsable == usuarioConsultor) {
				return solicitud;
			}
		} else
			throw new ServiceException("No se encontró rol correspondiente al codigoRol ingresado");
		return null;
	}

	/**
	 * Permite responder una solicitud dado el id de su correspondiente
	 * seguimiento Responder solicitud consiste en actualizar el estado y fecha
	 * de respondida del correspiente seguimiento
	 * 
	 * @param id:
	 *            codigo del seguimiento
	 * @param nombreUsuario
	 *            nombre de usuario del usuario que consulta
	 * @param codigoRol
	 *            codigo del rol gerente de cuentas
	 * @throws DaoException
	 *             cuando ocurre un error en instanciando el seguimiento en la
	 *             BD
	 * @throws ServiceException
	 *             cuando se ingresa un parametro invalido
	 */
	public void responderSolicitud(int id, String nombreUsuario, String codigoRol)
			throws DaoException, ServiceException {
		if (0 == id) {
			throw new ServiceException("El id del seguimiento a buscar no puede ser 0");
		}
		if (Validaciones.isTextoVacio(nombreUsuario)) {
			throw new ServiceException("El nombre de usuario no puede ser nulo, ni una cadena de caracteres vacia");
		}
		if (Validaciones.isTextoVacio(codigoRol)) {
			throw new ServiceException("El codigo del rol no puede ser nulo, ni una cadena de caracteres vacia");
		}
		Seguimiento seguimiento = seguimientoDAO.obtener(id);
		if (null != seguimiento && 0 == seguimiento.getEstado()) {
			rol = rolDAO.obtener(codigoRol);
			if (null != rol) {
				if ((rol.getNombre()).equals(usuarioDAO.obtener(nombreUsuario).getRol().getNombre())
						|| seguimiento.getResponsable() == usuarioDAO.obtener(nombreUsuario)) {
					seguimiento.setFechaRespondida(new Date());
					seguimiento.setEstado((byte) 1);
					seguimientoDAO.modificarSeguimiento(seguimiento);
				} else {
					throw new ServiceException("Usuario no autorizado para responder solicitud");
				}
			} else
				throw new ServiceException("No se encontró rol correspondiente al codigoRol ingresado");
		} else {
			throw new ServiceException("Verifique que la solicitud esté aun pendiente por responder");
		}
	}

	/**
	 * Permite reasignar el responsable de una solicitud dado el id de su
	 * correspondiente seguimiento reasignar responsable consiste en modificar
	 * el usuario (Gerente de cuentas) por un usuario diferente y actualizar la
	 * fecha
	 * 
	 * @param id:
	 *            codigo del seguimiento
	 * @param nombreUsuario:
	 *            login del gerente de cuentas
	 * @param nuevoResponsable
	 *            login del nuevo responsable
	 * @param codigo
	 *            del rol gerente de cuentas
	 * @throws DaoException
	 *             cuando ocurre un error al instanciar un seguimiento en la BD
	 * @throws ServiceException
	 *             cuando se ingresan parametros no válidos
	 */
	public Usuario reasignarSolicitud(int id, String nombreUsuario, String nuevoResponsable, String codigoRol)
			throws DaoException, ServiceException {
		if (0 == id) {
			throw new ServiceException("El id del seguimiento a buscar no puede ser 0");
		}
		if (Validaciones.isTextoVacio(nombreUsuario)) {
			throw new ServiceException("El nombre de usuario no puede ser nulo, ni una cadena de caracteres vacia");
		}
		if (Validaciones.isTextoVacio(nuevoResponsable)) {
			throw new ServiceException("El nombre de usuario del nuevo responsable no puede ser nulo,"
					+ "ni una cadena de caracteres vacia");
		}
		if (Validaciones.isTextoVacio(codigoRol)) {
			throw new ServiceException("El codigo de rol no puede ser nulo, ni una cadena de caracteres vacia");
		}
		Usuario usuarioResponsable = null;
		rol = rolDAO.obtener(codigoRol);
		if (null != rol) {
			if ((rol.getNombre()).equals(usuarioDAO.obtener(nombreUsuario).getRol().getNombre())) {
				Seguimiento seguimiento = seguimientoDAO.obtener(id);
				if (null != seguimiento && 0 == seguimiento.getEstado()) {
					seguimiento.setFechaReasignada(new Date());
					usuarioResponsable = usuarioDAO.obtener(nuevoResponsable);
					seguimiento.setResponsable(usuarioResponsable);
					seguimientoDAO.modificarSeguimiento(seguimiento);
				} else {
					throw new ServiceException("Verifique que la solicitud esté pendiente por responder");
				}
			} else {
				throw new ServiceException("Usted no tiene permisos para realizar esta operacion");
			}
		} else
			throw new ServiceException("No se encontró rol correspondiente al codigoRol ingresado");
		return usuarioResponsable;
	}

	/**
	 * Permite al gerente consultar el resultado de la encuesta hecha al cliente
	 * 
	 * @param radicado
	 *            numero de radicado de la solicitud a la cual corresponde la
	 *            encuesta que se desea consultar
	 * @param nombreUsuario
	 *            nombre de usuario que realiza la consulta
	 * @param codigoRol
	 *            codigo del rol gerente de cuentas
	 * @return la respuesta hecha por el cliente a la encuenta
	 * @throws DaoException
	 *             cuando ocurre un error al instanciar un usuario en la BD
	 * @throws ServiceException
	 *             cuando ingresan algun parametro no valido
	 */
	public String consultarResultadoEncuentas(Integer radicado, String nombreUsuario, String codigoRol)
			throws DaoException, ServiceException {
		if (0 == radicado) {
			throw new ServiceException("El radicado no puede ser nulo o una cadena vacia");
		}
		if (Validaciones.isTextoVacio(nombreUsuario)) {
			throw new ServiceException("El nombre de usuario no puede ser nulo, ni una cadena de caracteres vacia");
		}
		if (Validaciones.isTextoVacio(codigoRol)) {
			throw new ServiceException("El codigo de rol no puede ser nulo, ni una cadena de caracteres vacia");
		}
		Seguimiento seguimiento = null;
		rol = rolDAO.obtener(codigoRol);
		if (null != rol) {
			if ((rol.getNombre()).equals(usuarioDAO.obtener(nombreUsuario).getRol().getNombre())) {
				Solicitud solicitud = solicitudDAO.obtener(radicado);
				if (null != solicitud) {
					seguimiento = solicitud.getSeguimiento();
					return seguimiento.getSatisfaccion();
				}
			} else {
				throw new ServiceException("Usuario no autorizado para revisar encuesta");
			}
		} else
			throw new ServiceException("No se encontró rol correspondiente al codigoRol ingresado");
		return null;
	}

	/**
	 * Validar que el usuario este autenticado
	 * 
	 * @param nombreUsuario
	 *            nombre del usuario que desea autentica
	 * @param clave
	 *            password del usuario que desea autenticar
	 * @return verdadero si está autenticado o falso de lo contrario
	 * @throws DaoException
	 *             cuando ocurre un error instanciando el usuario en la BD
	 * @throws ServiceException
	 *             cuando ingresan un parametro no valido
	 */
	public Boolean autenticarUsuario(String nombreUsuario, String clave) throws DaoException, ServiceException {

		Cifrar cifrar = new Cifrar();

		if (Validaciones.isTextoVacio(nombreUsuario)) {
			throw new ServiceException("El nombre de usuario no puede ser nula, ni una cadena de caracteres vacia");
		}

		if (Validaciones.isTextoVacio(clave)) {
			throw new ServiceException("La clave del usuario no puede ser nula, ni una cadena de caracteres vacia");
		}

		Usuario usuario = usuarioDAO.obtener(nombreUsuario);
		if (usuario == null) {
			throw new ServiceException("Usuario o contraseña no válidos");
		}

		if (!cifrar.encrypt(clave).equals(usuario.getPassword())) {
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

	public RolDAO getRolDAO() {
		return rolDAO;
	}

	public void setRolDAO(RolDAO rolDAO) {
		this.rolDAO = rolDAO;
	}

}
