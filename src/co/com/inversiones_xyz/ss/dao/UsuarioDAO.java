package co.com.inversiones_xyz.ss.dao;

import co.com.inversiones_xyz.ss.dto.Usuario;
import co.com.inversiones_xyz.ss.excepcion.DaoException;

/**
 * Interface que define los metodos que va a proveer el dao usuario
 * @author 
 * 		Juan Carlos Estrada
 * 		Rafael Luna Pérez
 * 		Joan Manuel Rodríguez
 * @version 1.0.0
 * 			10/05/2016
 *
 */
public interface UsuarioDAO {
	
	/**
	 * Entrega la informacion de un usuario en el sistema dado nombreUsuario
	 * @param nombreUsuario: nombreUsuario del usuario
	 * @return instancia de los datos del usuario
	 * @throws DaoException
	 */
	public Usuario obtener(String nombreUsuario)throws DaoException;
}
