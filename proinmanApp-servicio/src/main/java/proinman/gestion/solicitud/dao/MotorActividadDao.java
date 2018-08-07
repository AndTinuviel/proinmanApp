package proinman.gestion.solicitud.dao;

import java.io.Serializable;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import proinman.gestion.solicitud.entity.MotorActividad;

@Stateless
@LocalBean
public class MotorActividadDao  extends BaseDaoGenerico<MotorActividad, Serializable>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1156326482843714140L;

	public MotorActividadDao() {
		super(MotorActividad.class);
	}
}
