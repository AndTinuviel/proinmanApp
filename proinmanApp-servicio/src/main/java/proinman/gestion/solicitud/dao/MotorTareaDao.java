package proinman.gestion.solicitud.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import proinman.gestion.solicitud.entity.MotorTarea;

@Stateless
@LocalBean
public class MotorTareaDao extends BaseDaoGenerico<MotorTarea, Serializable> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6546355900659020959L;

	public MotorTareaDao() {
		super(MotorTarea.class);
	}

	public List<MotorTarea> consultarTareasPorUsuario(String username) {
		String consulta = "select t from MotorTarea t  where t.usuario.username = :username and estado = 'ACT' ";
		List<MotorTarea> listaTareas = this.em.createQuery(consulta, MotorTarea.class)
				.setParameter("username", username).getResultList();
		return listaTareas;
	}

}
