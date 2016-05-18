package co.com.inversiones_xyz.ss.service;

import static org.junit.Assert.fail;
import java.util.List;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import co.com.inversiones_xyz.ss.dto.Solicitud;
import co.com.inversiones_xyz.ss.excepcion.DaoException;
import co.com.inversiones_xyz.ss.excepcion.ServiceException;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(locations = "classpath:configuracion.xml")
public class SolicitudServiceTest {
	private static Logger logger = Logger.getLogger(SolicitudServiceTest.class);
	@Autowired
	SolicitudService solicitudService;
	
	@Test
	public void testGenerarSolicitud() {
		try{
			solicitudService.generarSolicitud(123456, "Rafael", "Luna Perez", "ralp2089@gmail.com", "4427896", "3126171619", "Descripcion de la solicitud", "A101", 1002, 12001, 13001, "alopez");
		}catch(DaoException ex){
			logger.error(ex);
			logger.info(ex);
			fail(ex.getMessage());
		}catch(ServiceException ex){
			logger.error(ex);
			logger.info(ex);
			fail(ex.getMessage());
		}
	}

	@Test
	public void testObtener(){
		List<Solicitud> solicitudes = null;
		try{
			solicitudes = solicitudService.obtener();
			for(Solicitud solicitud : solicitudes){
				System.out.println("Solicitud con radicado: "+solicitud.getRadicado());
			}
		}catch(DaoException ex){
			logger.error(ex);
			logger.info(ex);
			fail(ex.getMessage());
		}
	}
	
	@Test
	public void testResponderSolicitud(){
		try{
			solicitudService.responderSolicitud(13001);
		}catch(DaoException ex){
			logger.error(ex);
			logger.info(ex);
			fail(ex.getMessage());
		}catch(ServiceException ex){
			logger.error(ex);
			logger.info(ex);
			fail(ex.getMessage());
		}
	}	
	
	@Test
	public void testReasignarSolicitud(){
		try{
			solicitudService.reasignarSolicitud(13001,"alopez");
		}catch(DaoException ex){
			logger.error(ex);
			logger.info(ex);
			fail(ex.getMessage());
		}catch(ServiceException ex){
			logger.error(ex);
			logger.info(ex);
			fail(ex.getMessage());
		}
	}	
}
