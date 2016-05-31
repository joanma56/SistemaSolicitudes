package co.com.inversiones_xyz.ss.dao.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.classic.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import co.com.inversiones_xyz.ss.dao.SucursalDAO;
import co.com.inversiones_xyz.ss.dto.Sucursal;
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
public class SucursalDAOHibernate extends HibernateDaoSupport implements SucursalDAO{

	/**
	 * Permite obtener una sucursal dado el codigo
	 */
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
	
	/**
	 * Permite obtener todos las sucursales del sistema
	 */
	@Override
	public List<Sucursal> obtener() throws DaoException {
		List<Sucursal> sucursales = null;
		Session session = null;
		Criteria criteria = null;
		try {
			session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			criteria = session.createCriteria(Sucursal.class);
			sucursales = criteria.list();
		} catch (HibernateException ex) {
			throw new DaoException(ex);
		}
		return sucursales;
	}

}
