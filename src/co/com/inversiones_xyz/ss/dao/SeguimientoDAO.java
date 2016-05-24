package co.com.inversiones_xyz.ss.dao;

import java.util.List;

import co.com.inversiones_xyz.ss.dto.Seguimiento;
import co.com.inversiones_xyz.ss.dto.Usuario;
import co.com.inversiones_xyz.ss.excepcion.DaoException;

/**
 * Interface que define los metodos que va a proveer el dao seguimiento
 * 
 * @author Juan Carlos Estrada Rafael Luna Pérez Joan Manuel Rodríguez
 * @version 1.0.0 10/05/2016
 *
 */
public interface SeguimientoDAO {

	/**
	 * Modifica la informacion de un seguimiento en el sistema
	 * 
	 * @param seguimiento
	 *            instancia del seguimiento con los datos a modificar
	 * @return seguimiento modificado
	 * @throws DaoException
	 *             cuando ocurre un error en la comunicacion con la BD
	 */
	public Seguimiento modificarSeguimiento(Seguimiento seguimiento) throws DaoException;

	/**
	 * Crea un nuevo seguimiento en el sistema
	 * 
	 * @param seguimiento
	 *            instancia del seguimiento a crear
	 * @return solicitud insertada
	 * @throws DaoException
	 *             cuando ocurre un error en la comunicacion con la BD
	 */
	public Seguimiento insertar(Seguimiento seguimiento) throws DaoException;

	/**
	 * Devuelve la informacion de un seguimiento dado su id
	 * 
	 * @param id
	 *            Id del seguimiento
	 * @return instancia de los datos del seguimiento
	 * @throws DaoException
	 *             cuando ocurre un error en la comunicacion con la BD
	 */
	public Seguimiento obtener(int id) throws DaoException;

}
