package co.com.inversiones_xyz.ss.service;

import java.util.Date;

import org.springframework.transaction.annotation.Transactional;

import co.com.inversiones_xyz.ss.dao.ProductoDAO;
import co.com.inversiones_xyz.ss.dao.SolicitudDAO;
import co.com.inversiones_xyz.ss.dao.SucursalDAO;
import co.com.inversiones_xyz.ss.dao.TipoSolicitudDAO;
import co.com.inversiones_xyz.ss.dao.UsuarioDAO;
import co.com.inversiones_xyz.ss.dto.Producto;
import co.com.inversiones_xyz.ss.dto.Seguimiento;
import co.com.inversiones_xyz.ss.dto.Solicitud;
import co.com.inversiones_xyz.ss.dto.Sucursal;
import co.com.inversiones_xyz.ss.dto.TipoSolicitud;
import co.com.inversiones_xyz.ss.dto.Usuario;
import co.com.inversiones_xyz.ss.excepcion.DaoException;
import co.com.inversiones_xyz.ss.excepcion.ServiceException;
import co.com.inversiones_xyz.ss.validacion.Validaciones;

@Transactional
public class SolicitudService {
	private SolicitudDAO solicitudDAO;
	private ProductoDAO productoDAO;
	private TipoSolicitudDAO tipoSolicitudDAO;
	private SucursalDAO sucursalDAO;
	private UsuarioDAO usuarioDAO;
	
	public void crearSolicitud(int radicado, String nombres, String apellidos, String correo,
			String telefono, String celular, String descripcion, String codigoSucursal, int codigoTipo,
			int codigoProducto, int codigoSeguimiento, String userName) throws DaoException, ServiceException{
		
		Solicitud solicitud = null;
		
		if(Validaciones.isTextoVacio(nombres)){
			throw new ServiceException("Los nombres del cliente no puede ser nula, ni una cadena de caracteres vacia");
		}
		if(Validaciones.isTextoVacio(apellidos)){
			throw new ServiceException("Los apellidos del cliente no puede ser nula, ni una cadena de caracteres vacia");
		}
		if(Validaciones.isTextoVacio(correo)){
			throw new ServiceException("El correo electrónico del cliente no puede ser nula, ni una cadena de caracteres vacia");
		}
		if(Validaciones.isTextoVacio(telefono)){
			throw new ServiceException("El telefono del cliente no puede ser nula, ni una cadena de caracteres vacia");
		}
		
		if(!Validaciones.isEmail(correo)){
			throw new ServiceException("El correo electrónico del cliente debe ser válido");
		}
		
		if(solicitudDAO.obtener(radicado) != null){
			throw new ServiceException("Ya existe una solicitud con numero de radicado " + radicado +  " en el sistema");
		}
		
		solicitud = new Solicitud();
		
		solicitud.setRadicado(radicado);
		solicitud.setNombres(nombres);
		solicitud.setApellidos(apellidos);
		solicitud.setCorreo(correo);
		solicitud.setTelefono(telefono);
		solicitud.setCelular(celular);
		solicitud.setDescripcion(descripcion);
		Sucursal sucursal = sucursalDAO.obtener(codigoSucursal);
		solicitud.setSucursal(sucursal);
		TipoSolicitud tipoSolicitud = tipoSolicitudDAO.obtener(codigoTipo);
		solicitud.setTipoSolicitud(tipoSolicitud);
		Producto producto = productoDAO.obtener(codigoProducto);
		solicitud.setProducto(producto);
		
		Seguimiento seguimiento = new Seguimiento(codigoSeguimiento);
		seguimiento.setFechaCreacion(new Date());
		seguimiento.setEstado((byte)0);
		Usuario usuario = usuarioDAO.obtener(userName);
		seguimiento.setResponsable(usuario);
		solicitud.setSeguimiento(seguimiento);

		solicitudDAO.insertar(solicitud);
	}

}
