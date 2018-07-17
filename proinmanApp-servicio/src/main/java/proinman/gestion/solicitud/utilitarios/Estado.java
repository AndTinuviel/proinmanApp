package proinman.gestion.solicitud.utilitarios;

import java.util.ArrayList;
import java.util.List;


public enum Estado {
	
	/**
	 * <p>
	 * Si el registro puede ser usado por el aplicativo
	 * </p>
	 */
	ACT("ACTIVO"),

	/**
	 * <p>
	 * Si el registro es desactivo para evitar ser usado por el aplicativo
	 * </p>
	 */
	INA("INACTIVO");

	/**
     *  
     */
	public String DESCRIPCION;

	/**
	 * EstadoRegistro constructor
	 */
	private Estado(String DESCRIPCION) {
		setDESCRIPCION(DESCRIPCION);
	}

	/**
	 * Get the DESCRIPCION property
	 * 
	 * @return String
	 */
	public String getDESCRIPCION() {
		return DESCRIPCION;
	}

	/**
	 * Set the DESCRIPCION property.
	 * 
	 * @param value
	 *            the new value
	 */
	public void setDESCRIPCION(String value) {
		this.DESCRIPCION = value;
	}

	/**
	 * 
	 * @param descripcion
	 * @return
	 */
	public static Estado getEstadoPorDescripcion(String descripcion) {
		for (Estado estado : Estado.values()) {
			if (estado.getDESCRIPCION().equalsIgnoreCase(descripcion)) {
				return estado;
			}
		}
		return null;
	}

	/**
	 * Return a Collection of all literal values for this enumeration
	 * 
	 * @return Collection literal values
	 */
	public static List literals() {
		final List<String> literals = new ArrayList<String>(values().length);
		for (int i = 0; i < values().length; i++) {
			literals.add(values()[i].name());
		}
		return literals;
	}

}
