package co.com.inversiones_xyz.ss.dao;

import co.com.inversiones_xyz.ss.dto.Rol;
import co.com.inversiones_xyz.ss.excepcion.DaoException;
/**
 * Interface que define los metodos que va a proveer el dao rol
 * @author 
 * 		Juan Carlos Estrada
 * 		Rafael Luna Pérez
 * 		Joan Manuel Rodríguez
 * @version 1.0.0
 * 			10/05/2016
 *
 */
public interface RolDAO {
	/**
	 * Este metodo permite obtener un rol dado el codigo
	 * @param codigo identificador del rol
	 * @return una instancia de rol
	 * @throws DaoException cuando ocurre un error en la comunicacion con la BD
	 */
	public Rol obtener(String codigo) throws DaoException;
}
