package co.com.inversiones_xyz.ss.service;

import static org.junit.Assert.fail;

import java.util.List;

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
@ContextConfiguration(locations = "classpath:configuracion.xml")
public class SeguimientoServiceTest {
	@Autowired
	SeguimientoService seguimientoService;

	
	@Test
	public void testResponderSolicitud() throws DaoException, ServiceException{
		try{
			Seguimiento seguimiento = seguimientoService.responderSolicitud(1234, "aperez", "AB102");
			System.out.println(seguimiento.getId()+" "+seguimiento.getFechaCreacion());
		}catch(DaoException ex){
			fail(ex.getMessage());
		}catch(ServiceException ex){
			fail(ex.getMessage());
		}		
	}

	@Test
	public void testReasignarSolicitud() throws DaoException, ServiceException{
		try{
			Usuario usuario = seguimientoService.reasignarSolicitud(1234, "aperez", "rluna", "AB102");
			System.out.println(usuario.getNombres()+usuario.getApellidos());
		}catch(DaoException ex){
			fail(ex.getMessage());
		}catch(ServiceException ex){
			fail(ex.getMessage());
		}
	}

	@Test
	public void testConsultarResultadoEncuestas() throws DaoException, ServiceException{
		try{
			String resultado = seguimientoService.consultarResultadoEncuentas(123456, "aperez", "AB102");
			System.out.println("Resultado: "+resultado);
		}catch(DaoException ex){
			fail(ex.getMessage());
		}catch(ServiceException ex){
			fail(ex.getMessage());
		}
	}

	@Test
	public void testConsultarResultadosEncuestas() throws DaoException, ServiceException{
		List<String> resultados = null;
		try{
			resultados = seguimientoService.consultarResultadosEncuentas("aperez", "AB102");
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
