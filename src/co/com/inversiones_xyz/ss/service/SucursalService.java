package co.com.inversiones_xyz.ss.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import co.com.inversiones_xyz.ss.dao.SucursalDAO;
import co.com.inversiones_xyz.ss.dto.Sucursal;
import co.com.inversiones_xyz.ss.excepcion.DaoException;
import co.com.inversiones_xyz.ss.excepcion.ServiceException;

@Transactional
public class SucursalService {
	private SucursalDAO sucursalDAO;
	
	/**
	 * 
	 * @return
	 * @throws DaoException
	 * @throws ServiceException
	 */
	public List<Sucursal> consultarSucursales() throws DaoException, ServiceException{
		List<Sucursal> sucursales = sucursalDAO.obtener();
		return sucursales;
	}

	public SucursalDAO getSucursalDAO() {
		return sucursalDAO;
	}

	public void setSucursalDAO(SucursalDAO sucursalDAO) {
		this.sucursalDAO = sucursalDAO;
	}
	
	
}
