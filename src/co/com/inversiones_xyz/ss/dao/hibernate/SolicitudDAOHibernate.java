package co.com.inversiones_xyz.ss.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
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
	 * Permite obtener las solicitudes asociadas a un usuario dado su nombre de usuario
	 */
	@Override
	public List<Solicitud> obtenerPorUsuario(String userName) throws DaoException {
		List<Solicitud> solicitudes = new ArrayList<Solicitud>();
		Solicitud solicitud = null;
		Session session = null;
		Usuario user = null;
		List<Seguimiento> seguimientos = null;
		Criteria criteria = null;
		try {
			session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			user = (Usuario) session.get(Usuario.class, userName);
			if (null != user) {
				criteria = session.createCriteria(Seguimiento.class, user.getNombreUsuario());
				seguimientos = criteria.list();
				for (Seguimiento seguimiento : seguimientos) {
					criteria = session.createCriteria(Solicitud.class, seguimiento.getId().toString());
					solicitudes = criteria.list();
				}
			}
		} catch (HibernateException ex) {
			throw new DaoException(ex);
		}
		return solicitudes;
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
