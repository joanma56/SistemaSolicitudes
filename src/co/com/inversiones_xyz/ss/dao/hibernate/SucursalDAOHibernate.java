package co.com.inversiones_xyz.ss.dao.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.classic.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import co.com.inversiones_xyz.ss.dao.SucursalDAO;
import co.com.inversiones_xyz.ss.dto.Sucursal;
import co.com.inversiones_xyz.ss.excepcion.DaoException;

public class SucursalDAOHibernate extends HibernateDaoSupport implements SucursalDAO{

	@Override
	public Sucursal obtener(String codigo) throws DaoException {
		Session session = null;
		Sucursal sucursal = null;
		try{
			session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			sucursal = (Sucursal)session.get(Sucursal.class, codigo);
		}catch(HibernateException ex){
			throw new DaoException(ex);
		}
		return sucursal;
	}

}
