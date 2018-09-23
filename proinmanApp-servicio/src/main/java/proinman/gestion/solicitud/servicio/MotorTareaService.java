package proinman.gestion.solicitud.servicio;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import proinman.gestion.solicitud.dao.MotorActividadDao;
import proinman.gestion.solicitud.dao.MotorTareaDao;
import proinman.gestion.solicitud.entity.MotorTarea;
import proinman.gestion.solicitud.entity.Solicitud;
import proinman.gestion.solicitud.entity.Usuario;
import proinman.gestion.solicitud.util.exception.EntidadNoGuardadaException;
import proinman.gestion.solicitud.utilitarios.Constantes;

@Stateless
@LocalBean
public class MotorTareaService {

	@EJB
	private MotorTareaDao motorTareaDao;
	@EJB
	private MotorActividadDao motorActividadDao;

	public MotorTarea crearTareaCotizarSolicitud(Solicitud solicitud, Usuario usuarioQueCotiza) throws EntidadNoGuardadaException {
		MotorTarea tarea = new MotorTarea();
		tarea.setMotorActividad(motorActividadDao.obtenerPorCodigo(Constantes.CONST_REALIZAR_COTIZACION));
		crearTareaSolicitudBasica(solicitud, usuarioQueCotiza, tarea);
		return tarea;
	}
	
	public MotorTarea crearTareaAprobarCotizacion(Solicitud solicitud, Usuario usuarioQueCotiza) throws EntidadNoGuardadaException {
		MotorTarea tarea = new MotorTarea();
		tarea.setMotorActividad(motorActividadDao.obtenerPorCodigo(Constantes.CONST_APROBAR_COTIZACION));
		crearTareaSolicitudBasica(solicitud, usuarioQueCotiza, tarea);
		return tarea;
	}
	
	public MotorTarea crearTareaGenerarOrdenTrabajo(Solicitud solicitud, Usuario usuario) throws EntidadNoGuardadaException {
		MotorTarea tarea = new MotorTarea();
		tarea.setMotorActividad(motorActividadDao.obtenerPorCodigo(Constantes.CONST_GENERAR_OT));
		crearTareaSolicitudBasica(solicitud, usuario, tarea);
		return tarea;
	}

	private void crearTareaSolicitudBasica(Solicitud solicitud, Usuario usuarioQueCotiza, MotorTarea tarea)
			throws EntidadNoGuardadaException {
		tarea.setEstado("ACT");
		tarea.setFechaAsignacion(new Date());
		tarea.setSolicitud(solicitud);
		tarea.setUsuario(usuarioQueCotiza);
		Date today = new Date();
		Date fechaFinalizacion = new Date(today.getTime() + (tarea.getMotorActividad().getNumeroDiasVencimiento()*(1000 * 60 * 60 * 24)));
		Date fechaVencimiento = new Date(today.getTime() + (tarea.getMotorActividad().getNumeroDiasPorVencer()*(1000 * 60 * 60 * 24)));
		tarea.setFechaFinalizacion(fechaFinalizacion);
		tarea.setFechaVencimiento(fechaVencimiento);
		motorTareaDao.guardar(tarea);
	}
	
	//TODO: Tipo acceso se refiere a si es Web o móvil
	public List<MotorTarea> consultarTareasPorUsuarioYTipoAceso(String username, String tipoAcceso) {
		List<MotorTarea> listaTareas = motorTareaDao.consultarTareasPorUsuario(username, tipoAcceso);
		for (MotorTarea motorTarea : listaTareas) {
			motorTarea.getUsuario().getListaUsuarioRol().size();
		}
		return listaTareas;
	}
	
	public void finalizarTarea(Integer codigoTarea) throws EntidadNoGuardadaException{
		motorTareaDao.finalizarTarea(codigoTarea);
	}

}