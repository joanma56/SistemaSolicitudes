package co.com.inversiones_xyz.ss.dao.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import co.com.inversiones_xyz.ss.dao.SolicitudDAO;
import co.com.inversiones_xyz.ss.dto.Solicitud;
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
class SolicitudDAOHibernate extends HibernateDaoSupport implements SolicitudDAO{

	/**
	 * Permite insertar una nueva solicitud en el sistema
	 */
	@Override
	public Solicitud insertar(Solicitud solicitud) throws DaoException {
		Session session = null;
		try{
			session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			session.save(solicitud);
		}catch(HibernateException ex){
			throw new DaoException(ex);
		}
		return solicitud;
	}

	/**
	 * Permite obtener las solicitudes activas en el sistema
	 */
	@Override
	public List<Solicitud> obtener() throws DaoException {
		List<Solicitud> solicitudes = null;
		Session session = null;
		Criteria criteria = null;
		try{
			session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			criteria = session.createCriteria(Solicitud.class);
			solicitudes = criteria.list();
		}catch(HibernateException ex){
			throw new DaoException(ex);
		}
		return solicitudes;
	}

	/**
	 * Permite obtener una solicitud dado su numero de radicado
	 */
	@Override
	public Solicitud obtener(Integer radicado) throws DaoException {
		Session session = null;
		Solicitud solicitud = null;
		try{
			session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			solicitud = (Solicitud)session.get(Solicitud.class, radicado);
		}catch(HibernateException ex){
			throw new DaoException(ex);
		}
		return solicitud;
	}

	/**
	 * Permite modificar una solicitud en el sistema
	 */
	@Override
	public Solicitud modificar(Solicitud solicitud) throws DaoException {
		Session session = null;
		try{
			session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			session.update(solicitud);
		}catch(HibernateException ex){
			throw new DaoException(ex);
		}
		return solicitud;
	}

}
