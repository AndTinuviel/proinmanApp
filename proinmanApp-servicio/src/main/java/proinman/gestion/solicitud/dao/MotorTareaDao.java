package proinman.gestion.solicitud.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.QueryTimeoutException;
import javax.persistence.TransactionRequiredException;

import proinman.gestion.solicitud.util.exception.EntidadNoGuardadaException;
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
		String consulta = "select t from MotorTarea t  where t.usuario.username = :username and t.estado = 'ACT' ";
		List<MotorTarea> listaTareas = this.em.createQuery(consulta, MotorTarea.class)
				.setParameter("username", username).getResultList();
		for (MotorTarea motorTarea : listaTareas) {
			motorTarea.getUsuario().getListaUsuarioRol().size();
		}
		return listaTareas;
	}

	public void finalizarTarea(Integer codigoTarea) throws EntidadNoGuardadaException {
		try {
			String consulta = "update MotorTarea m set estado = 'INA' where m.codigoTarea = :codigoTarea ";
			Query query = em.createQuery(consulta);
			query.setParameter("codigoTarea", codigoTarea);
			query.executeUpdate();
		} catch (TransactionRequiredException e) {
			throw new EntidadNoGuardadaException(e);
		} catch (QueryTimeoutException e) {
			throw new EntidadNoGuardadaException(e);
		} catch (PersistenceException e) {
			throw new EntidadNoGuardadaException(e);
		}
	}

}
