/**
 * 
 */
package com.inversionesxyz.sistemasolicitudes.dto;

/**
 * Clase dto que nos permite acceder a la información de los productos
 * presentes en una sucursal y que han sido asociados a una solicitud.
 * 
 * @author 
 * 		Juan Carlos Estrada
 * 		Rafael Luna Pérez
 * 		Joan Manuel Rodríguez
 *
 */
public class Producto {
	
	private Integer codigo;			//codigo del producto
	private String nombre;			//nombre del producto
	private String descripcion;		//Puede incluir información como precio, categoría, características, entre otros.
	
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
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
