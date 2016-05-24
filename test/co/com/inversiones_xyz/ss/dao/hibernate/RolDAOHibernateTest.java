package co.com.inversiones_xyz.ss.dao.hibernate;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import co.com.inversiones_xyz.ss.dao.RolDAO;
import co.com.inversiones_xyz.ss.dto.Rol;
import co.com.inversiones_xyz.ss.excepcion.DaoException;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration("classpath:configuracion.xml")
public class RolDAOHibernateTest {
	@Autowired
	RolDAO rolDao;

	@Test
	public void testObtener() throws DaoException {
		Rol rol = null;
		try {
			rol = rolDao.obtener("AB102");
			if (null != rol)
				System.out.println(rol.getNombre());
		} catch (DaoException ex) {
			fail(ex.getMessage());
		}
	}
}
