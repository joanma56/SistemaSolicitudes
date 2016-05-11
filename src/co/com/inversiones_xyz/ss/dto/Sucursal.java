package co.com.inversiones_xyz.ss.dto;

/**
 * Clase dto que guarda la información correspondiente a una sucursal de la 
 * empresa en donde un cliente haya adquirido un producto y que haya asociado con una
 * petición, queja, reclamo, o sugerencia. 
 * 
 * @author 
 * 		Juan Carlos Estrada
 * 		Rafael Luna Pérez
 * 		Joan Manuel Rodríguez
 * @version 1.0.0
 * 			08/05/2016
 * @param codigo: Identificador de la sucursal
 * @param nombre: Nombre de la sucursal	
 * @param direccion: Dirección de la sucursal
 * @param ciudad: Ciudad en la que se encuentra ubicada la sucursal
 *
 */

public class Sucursal {
	private String codigo;		
	private String nombre;	
	private String direccion;	
	private String ciudad;		
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	
	
}
