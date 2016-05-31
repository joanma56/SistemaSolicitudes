package co.com.inversiones_xyz.ss.service;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import co.com.inversiones_xyz.ss.dto.TipoSolicitud;
import co.com.inversiones_xyz.ss.excepcion.DaoException;
import co.com.inversiones_xyz.ss.excepcion.ServiceException;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(locations = "classpath:configuracion.xml")
public class TipoSolicitudServiceTest {
	@Autowired
	TipoSolicitudService tipoService;
	
	@Test
	public void testConsultartipos() throws ServiceException{
		try{
			for(TipoSolicitud tipo : tipoService.consultarTipos()){
				System.out.println(tipo.getCodigo()+tipo.getNombre());
			}
		}catch(DaoException ex){
			fail(ex.getMessage());
		}catch(ServiceException ex){
			fail(ex.getMessage());
		}
	}

}
