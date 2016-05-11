/**
 * 
 */
package com.inversionesxyz.sistemasolicitudes.dto;

/**
 * Clase dto que guarda el tipo de solicitud que puede ser generada por un usuario
 * a través del sistema.
 * Los principales tipos de solicitudes son:
 * 	- Petición
 * 	- Queja
 * 	- Reclamo
 * 	- Sugerencia  
 * 
 * @author 
 * 		Juan Carlos Estrada
 * 		Rafael Luna Pérez
 * 		Joan Manuel Rodríguez
 *
 */
public class TipoSolicitud {
	
	private Integer codigo;
	private String nombre;	//Nombre del tipo de solicitud (petición, queja, reclamo, etc.)
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
