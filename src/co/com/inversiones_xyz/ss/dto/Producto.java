package co.com.inversiones_xyz.ss.dto;

/**
 * Clase dto que nos permite acceder a la información de los productos
 * presentes en una sucursal y que han sido asociados a una solicitud.
 * 
 * @author 
 * 		Juan Carlos Estrada
 * 		Rafael Luna Pérez
 * 		Joan Manuel Rodríguez
 * @version 1.0.0
 * 			08/05/2016
 * @param codigo: Identificador del producto
 * @param nombre: Nombre del producto
 * @param descripcion: Información que describe al producto
 */
public class Producto {
	
	private Integer codigo;	
	private String nombre;	
	private String descripcion;		
	
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
