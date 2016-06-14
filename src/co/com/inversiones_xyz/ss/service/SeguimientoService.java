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
	private UsuarioService userService;
	

	/**
	 * Permite responder una solicitud dado su radicado
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
	public Seguimiento responderSolicitud(int radicado, String nombreUsuario,String respuesta)
			throws DaoException, ServiceException {
		if (0 == radicado) {
			throw new ServiceException("El id del seguimiento a buscar no puede ser 0");
		}
		if (Validaciones.isTextoVacio(nombreUsuario)) {
			throw new ServiceException("El nombre de usuario no puede ser nulo, ni una cadena de caracteres vacia");
		}
		if(Validaciones.isTextoVacio(respuesta)){
			throw new ServiceException("La respuesta a la solicitud no puede ser vacía");
		}
		Seguimiento seguimiento = solicitudDAO.obtener(radicado).getSeguimiento();
		Usuario usuarioSolicitante=usuarioDAO.obtener(nombreUsuario);
		if(usuarioSolicitante!=null){
			if (null != seguimiento && 0 == seguimiento.getEstado()) {
				if (seguimiento.getResponsable().getNombreUsuario() 
						== usuarioSolicitante.getNombreUsuario()) {
					seguimiento.setFechaRespondida(new Date());
					seguimiento.setEstado((byte) 1);
					seguimiento.setRespuesta(respuesta);
					seguimientoDAO.modificarSeguimiento(seguimiento);
				} else {
					throw new ServiceException("Usuario no autorizado para responder solicitud");
				}
			} else {
				throw new ServiceException("Verifique que la solicitud exista y esté aun pendiente por responder");
			}
		}else{
			throw new ServiceException("No existe usuario con ese userName");
		}
		return seguimiento;
	}

	/**
	 * Permite reasignar el responsable de una solicitud dado su radicado.
	 *  reasignar responsable consiste en modificar
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
	public Usuario reasignarSolicitud(int radicado, String nombreUsuario, String nuevoResponsable)
			throws DaoException, ServiceException {
		if (0 == radicado) {
			throw new ServiceException("El radiccado de la solicitud a buscar no puede ser 0");
		}
		if (Validaciones.isTextoVacio(nombreUsuario)) {
			throw new ServiceException("El nombre de usuario no puede ser nulo, ni una cadena de caracteres vacia");
		}
		if (Validaciones.isTextoVacio(nuevoResponsable)) {
			throw new ServiceException("El nombre de usuario del nuevo responsable no puede ser nulo,"
					+ "ni una cadena de caracteres vacia");
		}
		Usuario usuarioResponsable;
		
		if (userService.EsGerenteCuentas(nombreUsuario)) {
			
			Seguimiento seguimiento = solicitudDAO.obtener(radicado).getSeguimiento();
			if (null != seguimiento && 0 == seguimiento.getEstado()) {
				seguimiento.setFechaReasignada(new Date());
				usuarioResponsable = usuarioDAO.obtener(nuevoResponsable);
				if(usuarioResponsable!=null){
					seguimiento.setResponsable(usuarioResponsable);
					seguimientoDAO.modificarSeguimiento(seguimiento);		
				}else{
					throw new ServiceException("No existe usuario con nombre de usuario "+nuevoResponsable);
				}
			} else {
				throw new ServiceException("Verifique que la solicitud existe y está pendiente por responder");
			}
		} else
			throw new ServiceException("Usted no tiene permisos para realizar esta operacion");
		
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
	 *            
	 * @return la respuesta hecha por el cliente a la encuenta
	 * @throws DaoException
	 *             cuando ocurre un error al instanciar un usuario en la BD
	 * @throws ServiceException
	 *             cuando ingresan algun parametro no valido
	 */
	public String consultarResultadoEncuenta(Integer radicado, String nombreUsuario)
			throws DaoException, ServiceException {
		if (null == radicado) {
			throw new ServiceException("El radicado no puede ser nulo o una cadena vacia");
		}
		if (Validaciones.isTextoVacio(nombreUsuario)) {
			throw new ServiceException("El nombre de usuario no puede ser nulo, ni una cadena de caracteres vacia");
		}
		Seguimiento seguimiento = null;
		Usuario usuario=usuarioDAO.obtener(nombreUsuario);
		if (usuario != null) {
			if (userService.EsGerenteCuentas(nombreUsuario)) {
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
			throw new ServiceException("Usuario no existe en el sistema");
			}
		return null;
	}
	
	/**
	 * Permite al gerente consultar los resultados de las encuestas que han sido 
	 * respondidas por los clientes, al revisar la respuesta de sus solicitudes.
	 * 
	 * @param nombreUsuario
	 *            nombre de usuario que realiza la consulta
	 *            
	 * @return las respuestas hechas por los clientes a sus
	 * 		   correspiendientes encuentas.
	 * 
	 * @throws DaoException
	 *             cuando ocurre un error al instanciar un usuario en la BD
	 * @throws ServiceException
	 *             cuando ingresan algun parametro no valido
	 */
	public List<String> consultarResultadosEncuentas(String nombreUsuario)
			throws DaoException, ServiceException {
		
		if (Validaciones.isTextoVacio(nombreUsuario)) {
			throw new ServiceException("El nombre de usuario no puede ser nulo, ni una cadena de caracteres vacia");
		}
		Seguimiento seguimiento = null;
		Usuario usuario=usuarioDAO.obtener(nombreUsuario);
		if (usuario != null) {
			if (userService.EsGerenteCuentas(nombreUsuario)) {
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
			throw new ServiceException("No existe usuario con nombre de usuario "+ nombreUsuario);
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
	
	public UsuarioService getUserService() {
		return userService;
	}

	public void setUserService(UsuarioService userService) {
		this.userService = userService;
	}

}
