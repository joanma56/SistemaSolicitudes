package co.com.inversiones_xyz.ss.excepcion;

/**
 * Clase que nos permite manejar la captura de errores (asociados a servicios) generadas en tiempo 
 * de ejecuci�n del sistema.
 * 
 * @author 
 * 		Juan Carlos Estrada
 * 		Rafael Luna P�rez
 * 		Joan Manuel Rodr�guez
 * @version 1.0.0
 * 			08/05/2016
 */
public class ServiceException extends Exception{

	public ServiceException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ServiceException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public ServiceException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public ServiceException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

}
