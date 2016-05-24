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

import co.com.inversiones_xyz.ss.dao.SolicitudDAO;
import co.com.inversiones_xyz.ss.dto.Solicitud;
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
			solicitudService.generarSolicitud(123457, "Rafael", "Luna Perez", "ralp2089@gmail.com", "4427896", "3126171619", "Descripcion de la solicitud", "A101", 1002, 12001, 13001, "alopez");
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
			solicitudes = solicitudService.consultarSolicitudes("aperez");
			for(Solicitud solicitud: solicitudes){
				if(null!=solicitud)
					System.out.println(solicitud.getNombres()+solicitud.getApellidos());
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
			solicitud = solicitudService.consultarSolicitud(123456, "aperez", "AB102");
			if(null!=solicitud)
				System.out.println(solicitud.getNombres()+solicitud.getApellidos());
		}catch(DaoException ex){
			fail(ex.getMessage());
		}catch(ServiceException ex){
			fail(ex.getMessage());
		}
	}
	
	@Test
	public void testResponderSolicitud() throws DaoException, ServiceException{
		try{
			solicitudService.responderSolicitud(1234, "aperez", "AB102");
		}catch(DaoException ex){
			fail(ex.getMessage());
		}catch(ServiceException ex){
			fail(ex.getMessage());
		}		
	}

	@Test
	public void testReasignarSolicitud() throws DaoException, ServiceException{
		try{
			solicitudService.reasignarSolicitud(1234, "aperez", "rluna", "AB102");
		}catch(DaoException ex){
			fail(ex.getMessage());
		}catch(ServiceException ex){
			fail(ex.getMessage());
		}
	}

	@Test
	public void testConsultarResultadosEncuestas() throws DaoException, ServiceException{
		try{
			String resultado = solicitudService.consultarResultadoEncuentas(1234, "aperez", "AB102");
			System.out.println(resultado);
		}catch(DaoException ex){
			fail(ex.getMessage());
		}catch(ServiceException ex){
			fail(ex.getMessage());
		}
	}

	@Test
	public void testSeguirSolicitudes() throws DaoException, ServiceException{
		try{
			solicitudService.seguirSolicitudes("aperez", "AB102");
		}catch(DaoException ex){
			fail(ex.getMessage());
		}catch(ServiceException ex){
			fail(ex.getMessage());
		}
	}
}
