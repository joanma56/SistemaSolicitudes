package co.com.inversiones_xyz.ss.service;

import static org.junit.Assert.fail;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import co.com.inversiones_xyz.ss.dto.Solicitud;
import co.com.inversiones_xyz.ss.excepcion.DaoException;
import co.com.inversiones_xyz.ss.excepcion.ServiceException;

@RunWith(SpringJUnit4ClassRunner.class)
//@TransactionConfiguration(defaultRollback=false)
@Transactional
@ContextConfiguration(locations = "classpath:configuracion.xml")
public class SolicitudServiceTest {
	@Autowired
	SolicitudService solicitudService;
	
	//@Test
	@Rollback(value=false)
	public void testGenerarSolicitud() {
		try{
			Solicitud solicitud = solicitudService.generarSolicitud(
					"Andrés", "Tabares Rodríguez", "andres@gmail.com", "5968234", 
					"3136547890", "Producto defectuoso", "L01", 3, 3);
			System.out.println(solicitud.getRadicado()+""+solicitud.getDescripcion()
			+solicitud.getSeguimiento().getResponsable().getRol().getNombre());
		}catch(DaoException ex){
			ex.printStackTrace();
			fail(ex.getMessage());
		}catch(ServiceException ex){
			ex.printStackTrace();
			fail(ex.getMessage());
		}
	}

	@Test
	public void testConsultarSolicitudesPorUsuario() throws DaoException, ServiceException{
		List<Solicitud> solicitudes = null;
		try{
			solicitudes = solicitudService.obtenerSolicitudes("joanma");
			for(Solicitud solicitud: solicitudes){
				System.out.println("MIS SOLICITUDES SON:"+solicitud.getDescripcion());
			}
		}catch(DaoException ex){
			fail(ex.getMessage());
		}catch(ServiceException ex){
			fail(ex.getMessage());
		}
	}	
	
	@Test
	public void testConsularSolicitud()throws DaoException, ServiceException{
		Solicitud solicitud = null;
		try{
			solicitud = solicitudService.consultarSolicitud(3, "joanma");
			if(null!=solicitud)
				System.out.println(solicitud.getNombres()+solicitud.getApellidos());
		}catch(DaoException ex){
			fail(ex.getMessage());
		}catch(ServiceException ex){
			fail(ex.getMessage());
		}
	}

	@Test
	public void testSeguirSolicitudes() throws DaoException, ServiceException{
		try{
			List<Solicitud> solicitudes = solicitudService.seguirSolicitudes("joanma");
			for(Solicitud solicitud : solicitudes){
				System.out.println(solicitud.getRadicado()+solicitud.getNombres());
			}
		}catch(DaoException ex){
			fail(ex.getMessage());
		}catch(ServiceException ex){
			fail(ex.getMessage());
		}
	}
}
