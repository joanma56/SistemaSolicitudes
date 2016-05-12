package co.com.inversiones_xyz.ss.service;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import co.com.inversiones_xyz.ss.excepcion.DaoException;
import co.com.inversiones_xyz.ss.excepcion.ServiceException;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(locations = "classpath:configuracion.xml")
public class SolicitudServiceTest {

	@Autowired
	SolicitudService solicitudService;
	
	@Test
	public void testGenerarSolicitud() {
		try{
			solicitudService.generarSolicitud(123456, "Rafael", "Luna Perez", "ralp2089@gmail.com", "4427896", "3126171619", "Descripcion de la solicitud", "A101", 1002, 12001, 13001, "alopez");
		}catch(DaoException e){
			e.printStackTrace();
			fail(e.getMessage());
		}catch(ServiceException e){
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

}
