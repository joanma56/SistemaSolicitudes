/**
 * 
 */
package com.inversionesxyz.sistemasolicitudes.dto;

/**
 * Clase dto que guarda el tipo de solicitud que puede ser generada por un usuario
 * a trav�s del sistema.
 * Los principales tipos de solicitudes son:
 * 	- Petici�n
 * 	- Queja
 * 	- Reclamo
 * 	- Sugerencia  
 * 
 * @author 
 * 		Juan Carlos Estrada
 * 		Rafael Luna P�rez
 * 		Joan Manuel Rodr�guez
 *
 */
public class TipoSolicitud {
	
	private Integer codigo;
	private String nombre;	//Nombre del tipo de solicitud (petici�n, queja, reclamo, etc.)
	
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
