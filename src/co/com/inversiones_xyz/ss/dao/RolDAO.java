package co.com.inversiones_xyz.ss.dao;

import co.com.inversiones_xyz.ss.dto.Rol;
import co.com.inversiones_xyz.ss.excepcion.DaoException;

public interface RolDAO {
	/**
	 * Este metodo permite obtener un rol dado el codigo
	 * @param codigo identificador del rol
	 * @return una instancia de rol
	 * @throws DaoException cuando ocurre un error en la comunicacion con la BD
	 */
	public Rol obtener(String codigo) throws DaoException;
}
