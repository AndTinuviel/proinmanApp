package proinman.gestion.solicitud.util.exception;

import org.apache.log4j.Logger;

public class ErrorAplicacionException extends ControladaException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6544306332589409911L;
	private static final Logger log = Logger
			.getLogger(ErrorAplicacionException.class);
	
	/**
	 * @param message
	 * @param cause
	 */
	public ErrorAplicacionException(String message, Throwable cause) {
		super(message, cause);
		log.error(message,cause);	
	}
	
	public ErrorAplicacionException(String message, String mensajeSistema) {
		super(message);
		log.error(message + " SISTEMA:" + mensajeSistema);	
	}

	/**
	 * @param message
	 */
	public ErrorAplicacionException(String message) {
		super(message);
		log.error(message);		
	}
	
	public ErrorAplicacionException(Throwable cause) {
		super(cause);
		log.error(cause);	
	}

		
}
