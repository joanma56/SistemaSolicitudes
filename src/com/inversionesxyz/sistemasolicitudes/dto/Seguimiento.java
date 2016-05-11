package com.inversionesxyz.sistemasolicitudes.dto;
import java.util.Date;

/**
 * Clase dto que guarda la información correspondiente al historial y seguimiento de una
 * solicitud; así como del responsable encargado de atenderla. Un seguimiento se corresponde
 * a una única solicitud.
 * 
 * @author 
 * 		Juan Carlos Estrada
 * 		Rafael Luna Pérez
 * 		Joan Manuel Rodríguez
 *
 */

public class Seguimiento {
	
	private Integer id;				//identificación única del seguimiento
	private Date fechaCreacion;		//fecha de creación de la solicitud 
	
	private Date fechaReasignada;	/**Fecha en la que fue reasignada una solicitud. 
									* Este campo será nulo en caso de
	 								* que la solicitud aún esté a cargo del gerente de 
	 								* cuentas corporativas (aún no ha sido reasignada).
									*/
	
	private Date fechaRespondida;	//Fecha en que fue respondida la solicitud
	private byte estado;			//Guarda 1 para solicitud respondida, 0 de lo contrario
	
	private String satisfaccion;	/** Cadena de caracteres que incluye las respuestas de 
									* un cliente a la encuesta que se le envió al correo.
									* Las respuestas consisten en una secuencia de Si,No.
									*/
	
	private Usuario responsable;	/**Usuario del sistema encargado de atender la solicitud 
									* correspondiente a este seguimiento
									*/
	
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
