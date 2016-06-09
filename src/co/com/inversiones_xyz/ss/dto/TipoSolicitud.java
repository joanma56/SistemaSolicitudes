package co.com.inversiones_xyz.ss.dto;

import javax.xml.bind.annotation.XmlRootElement;

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
 * @version 1.0.0
 * 			08/05/2016
 * 
 */
@XmlRootElement
public class TipoSolicitud {
	
	private Integer codigo; //Identificador univoco de un tipo de solicitud
	private String nombre; //Nombre del tipo de solicitud (petición, queja, reclamo, etc.)
	
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
