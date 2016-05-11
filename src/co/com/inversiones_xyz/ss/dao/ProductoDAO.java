package co.com.inversiones_xyz.ss.dao;

import co.com.inversiones_xyz.ss.dto.Producto;
import co.com.inversiones_xyz.ss.excepcion.DaoException;

/**
 * Interface que define los metodos que va a proveer el dao producto
 * @author 
 * 		Juan Carlos Estrada
 * 		Rafael Luna Pérez
 * 		Joan Manuel Rodríguez
 * @version 1.0.0
 * 			10/05/2016
 *
 */
public interface ProductoDAO {
	
	/**
	 * Entrega la informacion de un producto dado su codigo
	 * @param codigo: identificador de un producto
	 * @return instancia de los datos del producto
	 * @throws DaoException cuando ocurre un error en la comunicacion con la BD
	 */
	public Producto obtener(int codigo) throws DaoException;
}
