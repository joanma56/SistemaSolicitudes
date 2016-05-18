package co.com.inversiones_xyz.ss.excepcion;

/**
 * Clase que nos permite manejar la captura de errores (asociados a la comunicacion con la BD) generadas en tiempo 
 * de ejecución del sistema.
 *  * @author 
 * 		Juan Carlos Estrada
 * 		Rafael Luna Pérez
 * 		Joan Manuel Rodríguez
 * @version 1.0.0
 * 			08/05/2016
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
