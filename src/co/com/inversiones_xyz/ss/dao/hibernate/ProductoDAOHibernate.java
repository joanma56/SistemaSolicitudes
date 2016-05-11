package co.com.inversiones_xyz.ss.dao.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import co.com.inversiones_xyz.ss.dao.ProductoDAO;
import co.com.inversiones_xyz.ss.dto.Producto;
import co.com.inversiones_xyz.ss.excepcion.DaoException;

public class ProductoDAOHibernate extends HibernateDaoSupport implements ProductoDAO{

	@Override
	public Producto obtener(int codigo) throws DaoException {
		Session session = null;
		Producto producto = null;
		try{
			session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			producto = (Producto)session.get(Producto.class, codigo);
		}catch(HibernateException ex){
			throw new DaoException(ex);
		}
		return producto;
	}

}
