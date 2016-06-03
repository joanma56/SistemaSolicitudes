package co.com.inversiones_xyz.ss.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import co.com.inversiones_xyz.ss.dao.TipoSolicitudDAO;
import co.com.inversiones_xyz.ss.dto.TipoSolicitud;
import co.com.inversiones_xyz.ss.excepcion.DaoException;
import co.com.inversiones_xyz.ss.excepcion.ServiceException;

@Transactional
public class TipoSolicitudService {
	private TipoSolicitudDAO tipoDAO;
	
	/**
	 * 
	 * @return 
	 * @throws DaoException
	 * @throws ServiceException
	 */
	public List<TipoSolicitud> consultarTipos() throws DaoException, ServiceException{
		List<TipoSolicitud> tipos = tipoDAO.obtener();
		return tipos;
	}

	public TipoSolicitudDAO getTipoDAO() {
		return tipoDAO;
	}

	public void setTipoDAO(TipoSolicitudDAO tipoDAO) {
		this.tipoDAO = tipoDAO;
	}

	
}
