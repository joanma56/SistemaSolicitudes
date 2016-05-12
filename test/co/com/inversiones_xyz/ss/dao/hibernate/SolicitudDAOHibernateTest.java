package co.com.inversiones_xyz.ss.dao.hibernate;

import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import co.com.inversiones_xyz.ss.dao.SolicitudDAO;
import co.com.inversiones_xyz.ss.dto.Solicitud;

public class SolicitudDAOHibernateTest {
	
	@Autowired
	SolicitudDAO solicitudDAO;
	
	@Test
	public List<Solicitud> testObtener(){
		List<Solicitud> solicitudes = null;
		
		try{
			solicitudes = solicitudDAO.obtener();
		}
		return solicitudes;
	}
	
}
