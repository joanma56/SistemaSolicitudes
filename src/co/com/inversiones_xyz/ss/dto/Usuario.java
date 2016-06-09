package co.com.inversiones_xyz.ss.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Clase dto que guarda información necesaria de los usuarios para que puedan
 * iniciar sesión en el sistema. Sólamente serán usuarios del sistema el gerente de
 * cuentas y toda persona que él asigne para atender una solicitud.
 * 
 * @author 
 * 		Juan Carlos Estrada
 * 		Rafael Luna Pérez
 * 		Joan Manuel Rodríguez
 * @version 1.0.0
 * 			08/05/2016
 */
@XmlRootElement
public class Usuario {
	
	private String nombres;	//Nombres del usuario	
	private String apellidos; //Apellidos del usuario	
	private String nombreUsuario; //Nombre de usuario o login para iniciar sesión en el sistema
	private String password;  //Contraseña para iniciar sesión en el sistema	
	private String correo;	//Correo electrónico del usuario		
	private Rol rol; //Rol que desempeña el usuario en el sistema	
	
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public Rol getRol() {
		return rol;
	}
	public void setRol(Rol rol) {
		this.rol = rol;
	}
}
