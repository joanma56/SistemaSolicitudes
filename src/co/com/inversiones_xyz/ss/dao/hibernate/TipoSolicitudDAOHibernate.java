package co.com.inversiones_xyz.ss.dao.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import co.com.inversiones_xyz.ss.dao.TipoSolicitudDAO;
import co.com.inversiones_xyz.ss.dto.TipoSolicitud;
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
public class TipoSolicitudDAOHibernate extends HibernateDaoSupport implements TipoSolicitudDAO{

	/**
	 * Permite obtener un tipoSolicitud dado el codigo
	 */
	@Override
	public TipoSolicitud obtener(int codigo) throws DaoException {
		Session session = null;
		TipoSolicitud tipoSolicitud = null;
		try{
			session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			tipoSolicitud = (TipoSolicitud)session.get(TipoSolicitud.class, codigo);
		}catch(HibernateException ex){
			throw new DaoException(ex);
		}
		return tipoSolicitud;
	}
	
	/**
	 * Permite obtener todos los tipoSolicituds del sistema
	 */
	@Override
	public List<TipoSolicitud> obtener() throws DaoException {
		List<TipoSolicitud> tipoSolicitudes = null;
		Session session = null;
		Criteria criteria = null;
		try {
			session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			criteria = session.createCriteria(TipoSolicitud.class);
			tipoSolicitudes = criteria.list();
		} catch (HibernateException ex) {
			throw new DaoException(ex);
		}
		return tipoSolicitudes;
	}
}
