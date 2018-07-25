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
public enum TipoItemEnum implements Serializable {
	/**
	 * <p>
	 * Campo con valor SI : SI
	 * </p>
	 */
	MATERIAL("MATERIAL"),

	/**
	 * <p>
	 * Campo con valor NO : NO
	 * </p>
	 */
	MANO_DE_OBRA("MANO_DE_OBRA");

	/**
     *  
     */
	public String VALOR;

	/**
	 * SiNo constructor
	 */
	private TipoItemEnum(String VALOR) {
		setVALOR(VALOR);
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


	public String getVALOR() {
		return VALOR;
	}


	public void setVALOR(String vALOR) {
		VALOR = vALOR;
	}
}
