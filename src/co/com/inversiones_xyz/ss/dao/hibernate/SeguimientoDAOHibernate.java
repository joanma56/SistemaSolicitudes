package co.com.inversiones_xyz.ss.dao.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import co.com.inversiones_xyz.ss.dao.SeguimientoDAO;
import co.com.inversiones_xyz.ss.dto.Seguimiento;
import co.com.inversiones_xyz.ss.dto.Usuario;
import co.com.inversiones_xyz.ss.excepcion.DaoException;
/**
 * 
 * @author 
 * 		Juan Carlos Estrada
 * 		Rafael Luna P�rez
 * 		Joan Manuel Rodr�guez
 * @version 1.0.0
 * 			12/05/2016
 *
 */
public class SeguimientoDAOHibernate extends HibernateDaoSupport implements SeguimientoDAO{

	/**
	 * Permite modificar un seguimiento en el sistema
	 */
	@Override
	public Seguimiento modificarSeguimiento(Seguimiento seguimiento) throws DaoException {
		Session session = null;
		try{
			session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			session.update(seguimiento);
		}catch(HibernateException ex){
			throw new DaoException(ex);
		}
		return seguimiento;
	}

	/**
	 * Permite insertar un nuevo seguimiento en el sistema
	 */
	@Override
	public Seguimiento insertar(Seguimiento seguimiento) throws DaoException {
		Session session = null;
		try{
			session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			session.save(seguimiento);
		}catch(HibernateException ex){
			throw new DaoException(ex);
		}
		return seguimiento;
	}

	/**
	 * Permite obtener un seguimiento dado el id
	 */
	@Override
	public Seguimiento obtener(int id) throws DaoException {
		Session session = null;
		Seguimiento seguimiento = null;
		try{
			session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			seguimiento = (Seguimiento)session.get(Seguimiento.class, id);
		}catch(HibernateException ex){
			throw new DaoException(ex);
		}
		return seguimiento;
	}
	
	/**
	 * Permite obtener todos los seguimientos asociados a un usuario
	 */
	@Override
	public List<Seguimiento> obtenerPorUsuario(Usuario user)throws DaoException{
		List<Seguimiento> seguimientos = null;
		Session session = null;
		Criteria criteria = null;
		try{
			session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			criteria = session.createCriteria(Seguimiento.class)
					.add(Restrictions.eq("responsable", user));
			seguimientos = criteria.list();
		}catch(HibernateException ex){
			throw new DaoException(ex);
		}
		return seguimientos;
	}
		
}
