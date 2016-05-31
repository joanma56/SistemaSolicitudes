package co.com.inversiones_xyz.ss.dao.hibernate;

import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import co.com.inversiones_xyz.ss.dao.SeguimientoDAO;
import co.com.inversiones_xyz.ss.dto.Seguimiento;
import co.com.inversiones_xyz.ss.excepcion.DaoException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:configuracion.xml")
@Transactional
public class SeguimientoDAOHibernateTest {
	@Autowired
	SeguimientoDAO seguimientoDao;

	@Test
	public void testInsertar() throws DaoException {
		Seguimiento seguimiento = new Seguimiento();
		seguimiento.setId(12345);
		seguimiento.setFechaCreacion(new Date());
		seguimiento.setEstado((byte) 0);
		try {
			seguimiento = seguimientoDao.insertar(seguimiento);
			System.out.println(seguimiento.getId());
		} catch (DaoException ex) {
			fail(ex.getMessage());
		}
	}

	@Test
	public void testObtener() throws DaoException {
		try {
			Seguimiento seguimiento = seguimientoDao.obtener(125);
			if (null != seguimiento)
				System.out.println(seguimiento.getFechaCreacion() + "" + seguimiento.getEstado());
		} catch (DaoException ex) {
			fail(ex.getMessage());
		}
	}

	@Test
	public void testModificar() throws DaoException {
		try {
			Seguimiento seguimiento = seguimientoDao.obtener(12345);
			if (null != seguimiento) {
				seguimiento.setEstado((byte) 1);
				seguimiento.setFechaRespondida(new Date());
				seguimientoDao.modificarSeguimiento(seguimiento);
			}
		} catch (DaoException ex) {
			fail(ex.getMessage());
		}
	}
}
