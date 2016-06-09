package co.com.inversiones_xyz.ss.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import co.com.inversiones_xyz.ss.dao.SolicitudDAO;
import co.com.inversiones_xyz.ss.dto.Seguimiento;
import co.com.inversiones_xyz.ss.dto.Solicitud;
import co.com.inversiones_xyz.ss.dto.Usuario;
import co.com.inversiones_xyz.ss.excepcion.DaoException;

public class SolicitudDAOHibernate extends HibernateDaoSupport implements SolicitudDAO {

	/**
	 * Permite guardar una nueva solicitud en el sistema
	 */
	@Override
	public Solicitud insertar(Solicitud solicitud) throws DaoException {
		Session session = null;
		try {
			session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			session.save(solicitud);
		} catch (HibernateException ex) {
			throw new DaoException(ex);
		}
		return solicitud;
	}

	/**
	 * Permite obtener una solicitud dado el radicado
	 */
	@Override
	public Solicitud obtener(Integer radicado) throws DaoException {
		Solicitud solicitud = null;
		Session session = null;
		try {
			session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			solicitud = (Solicitud) session.get(Solicitud.class, radicado);
		} catch (HibernateException ex) {
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
		try {
			session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			session.update(solicitud);
		} catch (HibernateException ex) {
			throw new DaoException(ex);
		}
		return solicitud;
	}
	
	/**
	 * Permite obtener una solicitud, dado el id de su seguimiento
	 */
	@Override
	public Solicitud obtenerPorSeguimiento(Seguimiento seguimiento)throws DaoException{
		List<Solicitud> solicitudes;
		Session session = null;
		Criteria criteria=null;
		System.out.println(seguimiento.getId());
		try {
			session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			criteria = session.createCriteria(Solicitud.class)
					.add(Restrictions.eq("seguimiento", seguimiento));
			solicitudes=criteria.list();
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return solicitudes.get(0);
	}

	
	/**
	 * Permite obtener todas las solicitudes en el sistema
	 */
	@Override
	public List<Solicitud> obtener() throws DaoException {
		List<Solicitud> solicitudes = null;
		Session session = null;
		Criteria criteria = null;
		try {
			session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			criteria = session.createCriteria(Solicitud.class);
			solicitudes = criteria.list();
		} catch (HibernateException ex) {
			throw new DaoException(ex);
		}
		return solicitudes;
	}

}
