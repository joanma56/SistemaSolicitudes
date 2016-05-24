package co.com.inversiones_xyz.ss.dao;

import java.util.List;

import co.com.inversiones_xyz.ss.dto.Solicitud;
import co.com.inversiones_xyz.ss.excepcion.DaoException;

/**
 * Interface que define los metodos que va a proveer el dao solicitud
 * @author 
 * 		Juan Carlos Estrada
 * 		Rafael Luna Pérez
 * 		Joan Manuel Rodríguez
 * @version 1.0.0
 * 			10/05/2016
 *
 */
public interface SolicitudDAO {
	
	/**
	 * crea una nueva solicitud en el sistema
	 * @param solicitud: instancia de la solicitud a crear
	 * @return solicitud insertada
	 * @throws DaoException cuando ocurre un error en la comunicacion con la BD
	 */
	public Solicitud insertar(Solicitud solicitud)throws DaoException;

	/**
	 * Entrega la informacion de una solicitud dado su numero de radicado
	 * @param radicado: radicado de la solicitud
	 * @return instancia de los datos de la solicitud
	 * @throws DaoException cuando ocurre un error en la comunicacion con la BD
	 */
	public Solicitud obtener(Integer radicado) throws DaoException;
	
	/**
	 * Modifica la información de una solicitud en el sistema
	 * @param solicitud: Instancia de la solicitud con los datos a modificar
	 * @return solicitud modificada
	 * @throws DaoException cuando ocurre un error en la comunicacion con la BD
	 */
	public Solicitud modificar(Solicitud solicitud)throws DaoException;

	/**
	 * Entrega las solicitudes dado un responsable asignado
	 * @param userName: nombre de usuario por el cual se desean consultar las solicitudes
	 * @return lista de solicitudes 
	 * @throws DaoException cuando ocurre un error en la comunicacion con la BD
	 */
	public List<Solicitud> obtenerPorUsuario(String userName)throws DaoException;
	
	/**
	 * Entrega todas las solicitudes activas en el sistema
	 * @return lista de solicitudes
	 * @throws DaoException cuando ocurre un error en la comunicacion con la BD
	 */
	public List<Solicitud> obtener()throws DaoException;
}
