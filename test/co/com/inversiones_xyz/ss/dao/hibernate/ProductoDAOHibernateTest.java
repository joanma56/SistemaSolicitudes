package co.com.inversiones_xyz.ss.dao.hibernate;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import co.com.inversiones_xyz.ss.dao.ProductoDAO;
import co.com.inversiones_xyz.ss.dto.Producto;
import co.com.inversiones_xyz.ss.excepcion.DaoException;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration("classpath:configuracion.xml")
public class ProductoDAOHibernateTest {
	@Autowired
	ProductoDAO productoDao;
	
	@Test
	public void testObtener()throws DaoException{
		Producto producto = null;
		try{
			producto = productoDao.obtener(12001);
			if(null!=producto)
				System.out.println(producto.getNombre()+producto.getDescripcion());
		}catch(DaoException ex){
			fail(ex.getMessage());
		}
	}
	
	@Test
	public void testObtenerTodos()throws DaoException{
		List<Producto> productos = null;
		try{
			productos = productoDao.obtener();
			for(Producto producto: productos){
				System.out.println(producto.getCodigo()+producto.getNombre()+producto.getDescripcion());
			}
		}catch(DaoException ex){
			fail(ex.getMessage());
		}
	}
}
