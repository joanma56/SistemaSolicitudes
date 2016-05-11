package co.com.inversiones_xyz.ss.dao;

import co.com.inversiones_xyz.ss.dto.Sucursal;
import co.com.inversiones_xyz.ss.excepcion.DaoException;

/**
 * Interface que define los metodos que va a proveer el dao sucursal
 * @author 
 * 		Juan Carlos Estrada
 * 		Rafael Luna Pérez
 * 		Joan Manuel Rodríguez
 * @version 1.0.0
 * 			08/05/2016
 * 
 */
public interface SucursalDAO {
	
	/**
	 * Entrega la informacion de la sucursal dado el codigo
	 * @param codigo: identificador de la sucursal
	 * @return instancia con los datos de la sucursal
	 * @throws DaoException cuando ocurre un error en la comunicacion con la BD
	 */
	public Sucursal obtener(String codigo) throws DaoException;
}
