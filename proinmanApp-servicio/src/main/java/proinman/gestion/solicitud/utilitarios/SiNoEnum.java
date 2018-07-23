package proinman.gestion.solicitud.utilitarios;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*
 * 
 * <p>
 * Enumeracion en la que se definen los dos posibles valores de una afirmacion(Si, No)
 * </p>      
 */
public enum SiNoEnum implements Serializable {
	/**
	 * <p>
	 * Campo con valor SI : SI
	 * </p>
	 */
	SI(Boolean.TRUE),

	/**
	 * <p>
	 * Campo con valor NO : NO
	 * </p>
	 */
	NO(Boolean.FALSE);

	/**
     *  
     */
	public Boolean VALOR;

	/**
	 * SiNo constructor
	 */
	private SiNoEnum(Boolean VALOR) {
		setVALOR(VALOR);
	}

	/**
	 * Get the VALOR property
	 * 
	 * @return Boolean
	 */
	public Boolean getVALOR() {
		return VALOR;
	}

	/**
	 * Set the VALOR property.
	 * 
	 * @param value
	 *            the new value
	 */
	public void setVALOR(Boolean value) {
		this.VALOR = value;
	}

	/**
	 * Retorna los valores de la enumeracion como una lista
	 * 
	 * @return List literal values
	 */
	public static List literals() {
		final List<String> literals = new ArrayList<String>(values().length);
		for (int i = 0; i < values().length; i++) {
			literals.add(values()[i].name());
		}
		return literals;
	}
}
