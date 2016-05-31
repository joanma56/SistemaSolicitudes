package co.com.inversiones_xyz.ss.dao.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import co.com.inversiones_xyz.ss.dao.UsuarioDAO;
import co.com.inversiones_xyz.ss.dto.Usuario;
import co.com.inversiones_xyz.ss.excepcion.DaoException;
/**
 * 
 * @author 
 * 		Juan Carlos Estrada
 * 		Rafael Luna Pérez
 * 		Joan Manuel Rodríguez
 * @version 1.0.0
 * 			12/05/2016
 *
 */
public class UsuarioDAOHibernate extends HibernateDaoSupport implements UsuarioDAO{

	/**
	 * Permite obtener un usuario dado su userName
	 */
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

	/**
	 * Permite obtener todos los usuarios del sistema
	 */
	@Override
	public List<Usuario> obtener() throws DaoException {
		List<Usuario> usuarios = null;
		Session session = null;
		Criteria criteria = null;
		try{
			session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			criteria = session.createCriteria(Usuario.class);
			usuarios = criteria.list();
		}catch(HibernateException ex){
			throw new DaoException(ex);
		}
		return usuarios;	
	}
}
