package co.com.inversiones_xyz.ss.dto;

/**
 * Clase dto que guarda la información de una solicitud así como del cliente que la generó
 * desde el sistema.
 * 
 * @author 
 * 		Juan Carlos Estrada
 * 		Rafael Luna Pérez
 * 		Joan Manuel Rodríguez
 * @version 1.0.0
 * 			08/05/2016
 * @param radicado: Identificador único de la solicitud
 * @param nombres: Nombres del cliente que generó la solicitud	
 * @param apellidos: Apellidos del cliente que generó la solicitud
 * @param correo: Correo del cliente que generó la solicitud
 * @param telefono: Teléfono del cliente que generó la solicitud
 * @param celular: Celular del cliente que generó la solicitud
 * @param descripcion: Texto con la descripción y asunto de la solicitud
 * @param sucursal: Sucursal asociada a la solicitud o que puede ser objeto de la misma
 * @param tipoSolicitud: Clave foranea a tipo de solicitud
 * @param producto: Producto asociado a una solicitud bien sea porque fue adquirido en la empresa
 * 					o del que se desea pedir información, dar una queja, etc.
 */

public class Solicitud {
	
	private Integer radicado;				
	private String nombres;
	private String apellidos;				
	private String correo;					
	private String telefono;				
	private String celular;					
	private String descripcion;				
	private Sucursal sucursal;				
	private TipoSolicitud tipoSolicitud;
	private Producto producto;				
							
	
	private Seguimiento seguimiento;		//Seguimiento acerca del estado y respuesta a una solicitud
	public Integer getRadicado() {
		return radicado;
	}
	public void setRadicado(Integer radicado) {
		this.radicado = radicado;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Sucursal getSucursal() {
		return sucursal;
	}
	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}
	public TipoSolicitud getTipoSolicitud() {
		return tipoSolicitud;
	}
	public void setTipoSolicitud(TipoSolicitud tipoSolicitud) {
		this.tipoSolicitud = tipoSolicitud;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public Seguimiento getSeguimiento() {
		return seguimiento;
	}
	public void setSeguimiento(Seguimiento seguimiento) {
		this.seguimiento = seguimiento;
	}
	
	
}
