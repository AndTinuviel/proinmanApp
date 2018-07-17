package proinman.gestion.solicitud.util.exception;

public class EntidadNoGuardadaException extends ErrorAplicacionException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EntidadNoGuardadaException(String message, Throwable cause) {
		super(message, cause);
	}

	public EntidadNoGuardadaException(String message) {
		super(message);
	}
	
	public EntidadNoGuardadaException(Throwable cause) {
		super(cause);
	}

}
