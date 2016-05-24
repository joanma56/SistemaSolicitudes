package co.com.inversiones_xyz.ss.dao.hibernate;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import co.com.inversiones_xyz.ss.dao.SucursalDAO;
import co.com.inversiones_xyz.ss.dto.Sucursal;
import co.com.inversiones_xyz.ss.excepcion.DaoException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:configuracion.xml")
@Transactional
public class SucursalDAOHibernateTest {
	@Autowired
	SucursalDAO sucursalDao;

	@Test
	public void testObtener() throws DaoException {
		Sucursal sucursal = null;
		try {
			sucursal = sucursalDao.obtener("A101");
			if (null != sucursal)
				System.out.println(sucursal.getNombre() + sucursal.getDireccion() + sucursal.getCiudad());
		} catch (DaoException ex) {
			fail(ex.getMessage());
		}
	}
}
