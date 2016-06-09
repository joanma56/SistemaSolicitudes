package co.com.inversiones_xyz.ss.dto;

import javax.xml.bind.annotation.XmlRootElement;

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
 * 					   
 */

@XmlRootElement
public class Solicitud {
	
	private Integer radicado; //Identificador único de la solicitud			
	private String nombres; //Nombres del cliente que generó la solicitud
	private String apellidos; //Apellidos del cliente que generó la solicitud			
	private String correo;	//Correo del cliente que generó la solicitud				
	private String telefono; //Teléfono del cliente que generó la solicitud			
	private String celular;	//Celular del cliente que generó la solicitud			
	private String descripcion;	//Texto con la descripción y asunto de la solicitud		
	private Sucursal sucursal; //Sucursal asociada a la solicitud o que puede ser objeto de la misma
	private TipoSolicitud tipoSolicitud; //Clave foranea a tipo de solicitud
	private Producto producto;	/*Producto asociado a una solicitud bien sea porque fue adquirido en la empresa
	  							o del que se desea pedir información, dar una queja, etc. */		
	private Seguimiento seguimiento; /*clave foranea a la tabla seguimiento
									 Seguimiento acerca del estado y respuesta a una solicitud*/

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
