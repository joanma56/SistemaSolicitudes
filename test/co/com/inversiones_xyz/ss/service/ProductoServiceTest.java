package co.com.inversiones_xyz.ss.service;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import co.com.inversiones_xyz.ss.dto.Producto;
import co.com.inversiones_xyz.ss.excepcion.DaoException;
import co.com.inversiones_xyz.ss.excepcion.ServiceException;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(locations = "classpath:configuracion.xml")
public class ProductoServiceTest {
	@Autowired
	ProductoService productoService;
	
	@Test
	public void testConsultarProductos() throws ServiceException{
		try{
			for(Producto producto : productoService.consultarProductos()){
				System.out.println(producto.getCodigo()+producto.getDescripcion());
			}
		}catch(DaoException ex){
			fail(ex.getMessage());
		}catch(ServiceException ex){
			fail(ex.getMessage());
		}
	}
}
