package co.com.inversiones_xyz.ss.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import co.com.inversiones_xyz.ss.dao.RolDAO;
import co.com.inversiones_xyz.ss.dao.SeguimientoDAO;
import co.com.inversiones_xyz.ss.dao.SolicitudDAO;
import co.com.inversiones_xyz.ss.dao.UsuarioDAO;
import co.com.inversiones_xyz.ss.dto.Rol;
import co.com.inversiones_xyz.ss.dto.Seguimiento;
import co.com.inversiones_xyz.ss.dto.Solicitud;
import co.com.inversiones_xyz.ss.dto.Usuario;
import co.com.inversiones_xyz.ss.excepcion.DaoException;
import co.com.inversiones_xyz.ss.excepcion.ServiceException;
import co.com.inversiones_xyz.ss.validacion.Validaciones;

/**
 * Clase que permite contiene la logica de negocio para el seguimiento de solicitudes
 * 
 * @author Juan Carlos Estrada Rafael Luna Pérez Joan Manuel Rodríguez
 * @version 1.0.0 15/05/2016
 */
@Transactional
public class SeguimientoService {

	private SolicitudDAO solicitudDAO;
	private UsuarioDAO usuarioDAO;
	private SeguimientoDAO seguimientoDAO;
	private RolDAO rolDAO;
	private Rol rol;
	
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
	public Seguimiento responderSolicitud(int id, String nombreUsuario, String codigoRol)
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
		return seguimiento;
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
		if (null == radicado) {
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
					if(null!=seguimiento.getFechaRespondida()&&null!=seguimiento.getSatisfaccion()){
						return seguimiento.getSatisfaccion();
					}
				}else{
					throw new ServiceException("No existe solicitud con ese número de radicado "+radicado);
				}	
			} else {
				throw new ServiceException("Usuario no autorizado para revisar encuesta");
			}
		} else{
			throw new ServiceException("No se encontró rol correspondiente al codigoRol ingresado");
			}
		return null;
	}
	
	/**
	 * Permite al gerente consultar los resultados de las encuestas que han sido 
	 * respondidas por los clientes, al revisar la respuesta de sus solicitudes.
	 * 
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
	public List<String> consultarResultadosEncuentas(String nombreUsuario, String codigoRol)
			throws DaoException, ServiceException {
		
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
				List<Solicitud> solicitudes = solicitudDAO.obtener();
				List<String> resultados=new ArrayList<String>();
				for(Solicitud solicitud:solicitudes){
					seguimiento=solicitud.getSeguimiento();
					if(null!=seguimiento.getFechaRespondida()&&null!=seguimiento.getSatisfaccion()){
						resultados.add(seguimiento.getSatisfaccion());
					}
				}
				return resultados;
			} else {
				throw new ServiceException("Usuario no autorizado para revisar encuestas");
			}
		} else{
			throw new ServiceException("No se encontró rol correspondiente al codigoRol ingresado");
			}
		//return null;
	}

	public SolicitudDAO getSolicitudDAO() {
		return solicitudDAO;
	}

	public void setSolicitudDAO(SolicitudDAO solicitudDAO) {
		this.solicitudDAO = solicitudDAO;
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

}
