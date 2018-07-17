package proinman.gestion.solicitud.util.exception;

public class EntidadNoEliminadaException extends ErrorAplicacionException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EntidadNoEliminadaException(String message, Throwable cause) {
		super(message, cause);

	}

	public EntidadNoEliminadaException(String message) {
		super(message);
	}
	
	public EntidadNoEliminadaException(Throwable cause) {
		super(cause);

	}

}