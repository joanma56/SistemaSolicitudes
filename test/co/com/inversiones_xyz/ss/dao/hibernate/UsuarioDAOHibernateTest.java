package co.com.inversiones_xyz.ss.dao.hibernate;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import co.com.inversiones_xyz.ss.dao.UsuarioDAO;
import co.com.inversiones_xyz.ss.dto.Usuario;
import co.com.inversiones_xyz.ss.excepcion.DaoException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:configuracion.xml")
@Transactional
public class UsuarioDAOHibernateTest {
	@Autowired
	UsuarioDAO usuarioDao;

	@Test
	public void testObtener() throws DaoException {
		Usuario usuario = null;
		try {
			usuario = usuarioDao.obtener("aperez");
			if (null != usuario)
				System.out.println(usuario.getNombres() + usuario.getApellidos() + usuario.getCorreo()
						+ usuario.getRol().getNombre());
		} catch (DaoException ex) {
			fail(ex.getMessage());
		}
	}
	
	@Test
	public void testObtenerTodos()throws DaoException{
		List<Usuario> usuarios = null;
		try{
			usuarios = usuarioDao.obtener();
			for(Usuario usuario: usuarios){
				System.out.println(usuario.getNombreUsuario()+usuario.getNombres()+usuario.getApellidos());
			}
		}catch(DaoException ex){
			fail(ex.getMessage());
		}
	}
}
