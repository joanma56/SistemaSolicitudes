package co.com.inversiones_xyz.ss.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import co.com.inversiones_xyz.ss.dao.ProductoDAO;
import co.com.inversiones_xyz.ss.dto.Producto;
import co.com.inversiones_xyz.ss.excepcion.DaoException;
import co.com.inversiones_xyz.ss.excepcion.ServiceException;

@Transactional
public class ProductoService {
	private ProductoDAO productoDAO;
	
	/**
	 * Consultar Productos
	 * Permite obtener todos los productos de la base de datos con el fin 
	 * de desplegarlos en pantalla al momento de crear una nueva solicitud
	 * @return
	 * @throws DaoException
	 * @throws ServiceException
	 */
	public List<Producto> consultarProductos() throws DaoException, ServiceException{
		List<Producto> productos = productoDAO.obtener();
		return productos;
	}

	public ProductoDAO getProductoDAO() {
		return productoDAO;
	}

	public void setProductoDAO(ProductoDAO productoDAO) {
		this.productoDAO = productoDAO;
	}
	
}
