package co.com.inversiones_xyz.ss.excepcion;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class Log4jException {
	public static void main(String[] args) {
		BasicConfigurator.configure();
		Logger log = Logger.getLogger("Adminitrador de logger");
		log.info("un info");
	}
}
