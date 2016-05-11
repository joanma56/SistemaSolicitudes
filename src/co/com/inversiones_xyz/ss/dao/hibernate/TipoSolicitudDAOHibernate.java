package co.com.inversiones_xyz.ss.dao.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import co.com.inversiones_xyz.ss.dao.TipoSolicitudDAO;
import co.com.inversiones_xyz.ss.dto.TipoSolicitud;
import co.com.inversiones_xyz.ss.excepcion.DaoException;

public class TipoSolicitudDAOHibernate extends HibernateDaoSupport implements TipoSolicitudDAO{

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
}
