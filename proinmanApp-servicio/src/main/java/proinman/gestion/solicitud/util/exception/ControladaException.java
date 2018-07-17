package proinman.gestion.solicitud.util.exception;

public class ControladaException extends Exception  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6933150456422042905L;

	public ControladaException(){
		//TODO
	}
	
	public ControladaException(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * @param message
	 */
	public ControladaException(String message) {
		super(message);	
	}

	/**
	 * @param cause
	 */
	public ControladaException(Throwable cause) {
		super(cause);
	}


}
