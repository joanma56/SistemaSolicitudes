package co.com.inversiones_xyz.ss.dao.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import co.com.inversiones_xyz.ss.dao.UsuarioDAO;
import co.com.inversiones_xyz.ss.dto.Usuario;
import co.com.inversiones_xyz.ss.excepcion.DaoException;

public class UsuarioDAOHibernate extends HibernateDaoSupport implements UsuarioDAO{

	@Override
	public Usuario obtener(String nombreUsuario) throws DaoException {
		Session session = null;
		Usuario usuario = null;
		try{
			session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			usuario = (Usuario)session.get(Usuario.class, nombreUsuario);
		}catch(HibernateException ex){
			throw new DaoException(ex);
		}
		return usuario;
	}

}
