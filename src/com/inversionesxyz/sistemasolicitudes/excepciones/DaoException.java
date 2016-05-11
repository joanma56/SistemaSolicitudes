package com.inversionesxyz.sistemasolicitudes.excepciones;

/**
 * Clase que nos permite manejar la captura de errores generadas en tiempo 
 * de ejecución del sistema.
 * 
 * @author 
 * 		Juan Carlos Estrada
 * 		Rafael Luna Pérez
 * 		Joan Manuel Rodríguez
 *
 */


public class DaoException extends Exception{
	
	public DaoException() {
		// TODO Auto-generated constructor stub
	}

	public DaoException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public DaoException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public DaoException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}
}
