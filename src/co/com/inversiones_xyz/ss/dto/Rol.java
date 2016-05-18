package co.com.inversiones_xyz.ss.dto;

/**
 * Clase dto que indica el rol en el sistema que puede desempeñar determinado
 * usuario. Los dos roles principales en el sistema son:
 * 		-El gerente de cuentas corporativas
 * 		-Encargado responsable
 * 
 * @author 
 * 		Juan Carlos Estrada
 * 		Rafael Luna Pérez
 * 		Joan Manuel Rodríguez
 * @version 1.0.0
 * 			08/05/2016
 *
 */

public class Rol {
	
	private String codigo; //Identificador del rol
	private String nombre; //Nombre del rol en el sistema
	
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
	
}
