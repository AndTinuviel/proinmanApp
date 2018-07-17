package proinman.gestion.solicitud.util.exception;


public class EntidadNoExisteException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EntidadNoExisteException() {
		super();
	}

	public EntidadNoExisteException(String message, Throwable cause) {
		super(message, cause);
	}

	public EntidadNoExisteException(String message) {
		super(message);
	}

	public EntidadNoExisteException(Throwable cause) {
		super(cause);
	}	
	
}