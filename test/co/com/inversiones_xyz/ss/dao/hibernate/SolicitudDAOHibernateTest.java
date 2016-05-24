package co.com.inversiones_xyz.ss.dao.hibernate;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import co.com.inversiones_xyz.ss.dao.SolicitudDAO;
import co.com.inversiones_xyz.ss.dao.TipoSolicitudDAO;
import co.com.inversiones_xyz.ss.dto.Solicitud;
import co.com.inversiones_xyz.ss.excepcion.DaoException;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(locations = "classpath:Configuracion.xml")
public class SolicitudDAOHibernateTest {

	@Autowired
	SolicitudDAO solicitudDao;
	@Autowired
	TipoSolicitudDAO tipoDao;

	@Test
	public void testInsertar() throws DaoException {
		Solicitud solicitud = new Solicitud();
		solicitud.setRadicado(243546);
		solicitud.setNombres("Rafael");
		solicitud.setApellidos("Luna");
		solicitud.setCorreo("ralp2089@gmail.com");
		solicitud.setTelefono("4448790");
		solicitud.setDescripcion("Texto de la solicitud");
		solicitud.setTipoSolicitud(tipoDao.obtener(1003));
		try {

			solicitudDao.insertar(solicitud);
		} catch (DaoException ex) {
			fail(ex.getMessage());
		}
	}

	@Test
	public void testObtener() throws DaoException {
		try {
			Solicitud solicitud = solicitudDao.obtener(12345);
			if (null != solicitud)
				System.out.println(solicitud.getNombres());
		} catch (DaoException ex) {
			fail(ex.getMessage());
		}
	}

	@Test
	public void testModificar() throws DaoException {
		Solicitud solicitud = new Solicitud();
		solicitud.setRadicado(243546);
		solicitud.setNombres("Alonso");
		solicitud.setApellidos("Perez");
		solicitud.setCorreo("ralid0120@hotmail.com");
		solicitud.setTelefono("4448790");
		solicitud.setDescripcion("Texto de la solicitud");
		solicitud.setTipoSolicitud(tipoDao.obtener(1004));
		try {
			solicitudDao.modificar(solicitud);
		} catch (DaoException ex) {
			fail(ex.getMessage());
		}
	}

	@Test
	public void testObtenerPorUsuario() {
		List<Solicitud> solicitudes = null;
		try {
			solicitudes = solicitudDao.obtenerPorUsuario("aperez");
			for (Solicitud solicitud : solicitudes) {
				if (null != solicitud) {
					System.out.println(solicitud.getRadicado() + solicitud.getNombres() + solicitud.getApellidos()
							+ solicitud.getCorreo() + solicitud.getTelefono() + solicitud.getDescripcion()
							+ solicitud.getTipoSolicitud().getNombre());
				}
			}
		} catch (DaoException ex) {
			fail(ex.getMessage());
		}
	}

	@Test
	public void testObtenerTodasSolicitudes() {
		List<Solicitud> solicitudes = null;
		try {
			solicitudes = solicitudDao.obtener();
			for (Solicitud solicitud : solicitudes) {
				System.out.println(solicitud.getRadicado() + solicitud.getNombres() + solicitud.getApellidos()
						+ solicitud.getCorreo() + solicitud.getTelefono() + solicitud.getDescripcion()
						+ solicitud.getTipoSolicitud().getNombre());
			}
		} catch (DaoException ex) {
			fail(ex.getMessage());
		}
	}
}
