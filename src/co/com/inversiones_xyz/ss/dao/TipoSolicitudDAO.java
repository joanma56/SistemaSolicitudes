package co.com.inversiones_xyz.ss.dao;

import java.util.List;

import co.com.inversiones_xyz.ss.dto.TipoSolicitud;
import co.com.inversiones_xyz.ss.excepcion.DaoException;

/**
 * Interface que define los metodos que va a proveer el dao tipoSolicitud
 * @author 
 * 		Juan Carlos Estrada
 * 		Rafael Luna Pérez
 * 		Joan Manuel Rodríguez
 * @version 1.0.0
 * 			11/05/2016
 *
 */
public interface TipoSolicitudDAO {
	
	/**
	 * Devuelve la informacion de un tipo de solicitud dado su codigo
	 * @param codigo: codigo del tipo de solicitud
	 * @return instancia de los datos de un tipo de solicitud
	 * @throws DaoException cuando ocurre un error en la comunicacion con la BD
	 */
	public TipoSolicitud obtener(int codigo)throws DaoException;
	
	/**
	 * Entrega los tipos de solicitudes activos en el sistema
	 * @return lista de instancias de los datos de todos los tipos de solicitudes
	 * @throws DaoException cuando ocurre un error en la comunicacion con la BD
	 */
	public List<TipoSolicitud> obtener()throws DaoException;
}
