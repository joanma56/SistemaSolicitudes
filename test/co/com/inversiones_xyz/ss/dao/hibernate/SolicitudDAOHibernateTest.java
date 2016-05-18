package co.com.inversiones_xyz.ss.dao.hibernate;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import co.com.inversiones_xyz.ss.dao.SolicitudDAO;
import co.com.inversiones_xyz.ss.dto.Solicitud;
import co.com.inversiones_xyz.ss.excepcion.DaoException;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(locations = "classpath:Configuracion.xml")
public class SolicitudDAOHibernateTest {
	
	@Autowired
	SolicitudDAO solicitudDao;

	@Test
	public void testObtener() {
		
		List<Solicitud> solicitudes = null;
		
		try{
			solicitudes = solicitudDao.obtener();
			
			for(Solicitud solicitud : solicitudes){
				System.out.println("Solicitud con radicado : " + solicitud.getRadicado());
			}
			
			assertTrue(true);
		}catch(DaoException ex){
			fail(ex.getMessage());
		}
	}

}
