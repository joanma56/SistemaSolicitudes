package co.com.inversiones_xyz.ss.service;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import co.com.inversiones_xyz.ss.dto.Sucursal;
import co.com.inversiones_xyz.ss.excepcion.DaoException;
import co.com.inversiones_xyz.ss.excepcion.ServiceException;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(locations = "classpath:configuracion.xml")
public class SucursalServiceTest {
	@Autowired
	SucursalService sucursalService;
	
	@Test
	public void testConsultarsucursals() throws ServiceException{
		try{
			for(Sucursal sucursal : sucursalService.consultarSucursales()){
				System.out.println(sucursal.getCodigo()+sucursal.getNombre()+sucursal.getDireccion()+sucursal.getCiudad());
			}
		}catch(DaoException ex){
			fail(ex.getMessage());
		}catch(ServiceException ex){
			fail(ex.getMessage());
		}
	}
}
