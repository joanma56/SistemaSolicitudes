package co.com.inversiones_xyz.ss.dao.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import co.com.inversiones_xyz.ss.dao.ProductoDAO;
import co.com.inversiones_xyz.ss.dto.Producto;
import co.com.inversiones_xyz.ss.excepcion.DaoException;

/**
 * 
 * @author Juan Carlos Estrada Rafael Luna Pérez Joan Manuel Rodríguez
 * @version 1.0.0 12/05/2016
 *
 */
public class ProductoDAOHibernate extends HibernateDaoSupport implements ProductoDAO {

	/**
	 * Permite obtener un producto dado su codigo
	 */
	@Override
	public Producto obtener(int codigo) throws DaoException {
		Session session = null;
		Producto producto = null;
		try {
			session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			producto = (Producto) session.get(Producto.class, codigo);
		} catch (HibernateException ex) {
			throw new DaoException(ex);
		}
		return producto;
	}

	/**
	 * Permite obtener todos los productos del sistema
	 */
	@Override
	public List<Producto> obtener() throws DaoException {
		List<Producto> productos = null;
		Session session = null;
		Criteria criteria = null;
		try {
			session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			criteria = session.createCriteria(Producto.class);
			productos = criteria.list();
		} catch (HibernateException ex) {
			throw new DaoException(ex);
		}
		return productos;
	}
}
