package co.com.inversiones_xyz.ss.service;

import static org.junit.Assert.fail;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import co.com.inversiones_xyz.ss.dto.Seguimiento;
import co.com.inversiones_xyz.ss.dto.Usuario;
import co.com.inversiones_xyz.ss.excepcion.DaoException;
import co.com.inversiones_xyz.ss.excepcion.ServiceException;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(locations = "classpath:Configuracion.xml")
public class SeguimientoServiceTest {
	@Autowired
	SeguimientoService seguimientoService;

	
	@Test
	public void testResponderSolicitud() 
			throws DaoException, ServiceException{
		try{
			Seguimiento seguimiento = seguimientoService.responderSolicitud(4, "rafagol","no importa");
			System.out.println(seguimiento.getId()+" "+seguimiento.getFechaCreacion()+seguimiento.getRespuesta());
		}catch(DaoException ex){
			fail(ex.getMessage());
		}catch(ServiceException ex){
			fail(ex.getMessage());
		}		
	}

	//@Test
	public void testReasignarSolicitud() throws DaoException, ServiceException{
		try{
			Usuario usuario = seguimientoService.reasignarSolicitud(2, "joanma", "rafagol");
			System.out.println(usuario.getNombres()+usuario.getApellidos());
		}catch(DaoException ex){
			fail(ex.getMessage());
		}catch(ServiceException ex){
			fail(ex.getMessage());
		}
	}

	//@Test
	public void testConsultarResultadoEncuestas() throws DaoException, ServiceException{
		try{
			String resultado = seguimientoService.consultarResultadoEncuenta(123456, "aperez");
			System.out.println("Resultado: "+resultado);
		}catch(DaoException ex){
			fail(ex.getMessage());
		}catch(ServiceException ex){
			fail(ex.getMessage());
		}
	}

	//@Test
	public void testConsultarResultadosEncuestas() throws DaoException, ServiceException{
		List<String> resultados = null;
		try{
			resultados = seguimientoService.consultarResultadosEncuentas("aperez");
			for(String resultado :resultados){
				System.out.println("Resultado: "+resultado);
			}
		}catch(DaoException ex){
			fail(ex.getMessage());
		}catch(ServiceException ex){
			fail(ex.getMessage());
		}
	}
	
}
