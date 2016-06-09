package co.com.inversiones_xyz.ss.dto;

import java.util.Date;

import javax.persistence.JoinColumn;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Clase dto que guarda la información correspondiente al historial y seguimiento de una
 * solicitud; así como del responsable encargado de atenderla. Un seguimiento se corresponde
 * a una única solicitud.
 * 
 * @author 
 * 		Juan Carlos Estrada
 * 		Rafael Luna Pérez
 * 		Joan Manuel Rodríguez
 * @version 1.0.0
 * 			08/05/2016				   
 */

@XmlRootElement
public class Seguimiento {
	
	private Integer id; //Identificación única del seguimiento
	private Date fechaCreacion; //Fecha en la que se crea la solicitud 	
	private Date fechaReasignada; //Fecha en la que sea reasignada una solicitud
	private Date fechaRespondida; //Fecha en que sea respondida la solicitud
	private byte estado; //Condicion de la solicitud, 1 en estado atendida, 0 en estado pendiente
	private String satisfaccion;/* Cadena de caracteres que incluye las respuestas de un cliente a la encuesta que 
								se le envió al correo. Las respuestas consisten en una secuencia de Si,No*/
	private Usuario responsable;/*Clave foranea a la tabla usuario
								Usuario del sistema encargado de atender la solicitud*/
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Date getFechaReasignada() {
		return fechaReasignada;
	}
	public void setFechaReasignada(Date fechaReasignada) {
		this.fechaReasignada = fechaReasignada;
	}
	public Date getFechaRespondida() {
		return fechaRespondida;
	}
	public void setFechaRespondida(Date fechaRespondida) {
		this.fechaRespondida = fechaRespondida;
	}
	public byte getEstado() {
		return estado;
	}
	public void setEstado(byte estado) {
		this.estado = estado;
	}
	public String getSatisfaccion() {
		return satisfaccion;
	}
	public void setSatisfaccion(String satisfaccion) {
		this.satisfaccion = satisfaccion;
	}
	public Usuario getResponsable() {
		return responsable;
	}
	public void setResponsable(Usuario responsable) {
		this.responsable = responsable;
	}
}
