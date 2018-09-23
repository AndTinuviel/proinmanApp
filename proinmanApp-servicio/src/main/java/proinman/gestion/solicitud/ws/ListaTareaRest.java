package proinman.gestion.solicitud.ws;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import proinman.gestion.solicitud.entity.MotorTarea;
import proinman.gestion.solicitud.servicio.MotorTareaService;
import proinman.gestion.solicitud.utilitarios.Constantes;

@Path("/tareas")
public class ListaTareaRest {
	
	@EJB
	private MotorTareaService motorTareaService;
	
	@GET
	@Path("/consultarTareasPorUsuario")
	@Produces({ "application/json" })
	public List<MotorTarea> consultarTareasPorUsuario(@QueryParam("username") String username) {
		List<MotorTarea> listaTareas = motorTareaService.consultarTareasPorUsuarioYTipoAceso(username, Constantes.MOVIL);
		for (MotorTarea motorTarea : listaTareas) {
			motorTarea.getSolicitud().getUsuario().setListaUsuarioRol(null);
			motorTarea.getUsuario().setListaUsuarioRol(null);
			motorTarea.getSolicitud().setListaCotizaciones(null);
		}
		return listaTareas;
	}

}
