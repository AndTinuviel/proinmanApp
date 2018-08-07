package proinman.gestion.solicitud.dao;

import java.io.Serializable;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import proinman.gestion.solicitud.entity.MotorTarea;

@Stateless
@LocalBean
public class MotorTareaDao  extends BaseDaoGenerico<MotorTarea, Serializable>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6546355900659020959L;

	public MotorTareaDao() {
		super(MotorTarea.class);
	}
	
}
