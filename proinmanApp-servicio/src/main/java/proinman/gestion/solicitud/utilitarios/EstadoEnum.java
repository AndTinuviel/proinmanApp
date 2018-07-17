package proinman.gestion.solicitud.utilitarios;

import java.util.ArrayList;
import java.util.List;


public enum EstadoEnum {
	
	/**
	 * <p>
	 * Si el registro puede ser usado por el aplicativo
	 * </p>
	 */
	ACT("ACT"),

	/**
	 * <p>
	 * Si el registro es desactivo para evitar ser usado por el aplicativo
	 * </p>
	 */
	INA("INA");

	/**
     *  
     */
	public String DESCRIPCION;

	/**
	 * EstadoRegistro constructor
	 */
	private EstadoEnum(String DESCRIPCION) {
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
	public static EstadoEnum getEstadoPorDescripcion(String descripcion) {
		for (EstadoEnum estado : EstadoEnum.values()) {
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
