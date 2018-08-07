package proinman.gestion.solicitud.servicio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import proinman.gestion.solicitud.dao.MotorActividadDao;
import proinman.gestion.solicitud.dao.MotorTareaDao;
import proinman.gestion.solicitud.entity.MotorTarea;
import proinman.gestion.solicitud.entity.Solicitud;
import proinman.gestion.solicitud.util.exception.EntidadNoGuardadaException;

@Stateless
@LocalBean
public class MotorTareaService {

	@EJB
	private MotorTareaDao motorTareaDao;
	@EJB
	private MotorActividadDao motorActividadDao;

	public static final Integer CONST_REALIZAR_COTIZACION = 1;

	public MotorTarea crearTareaCotizarSolicitud(Solicitud solicitud) throws EntidadNoGuardadaException {
		MotorTarea tarea = new MotorTarea();
		tarea.setEstado("ACT");
		tarea.setFechaAsignacion(new Date());
		tarea.setMotorActividad(motorActividadDao.obtenerPorCodigo(CONST_REALIZAR_COTIZACION));
		tarea.setSolicitud(solicitud);
		tarea.setUsuario(solicitud.getUsuario());
		Date today = new Date();
		Date fechaFinalizacion = new Date(today.getTime() + (tarea.getMotorActividad().getNumeroDiasVencimiento()*(1000 * 60 * 60 * 24)));
		Date fechaVencimiento = new Date(today.getTime() + (tarea.getMotorActividad().getNumeroDiasPorVencer()*(1000 * 60 * 60 * 24)));
		tarea.setFechaFinalizacion(fechaFinalizacion);
		tarea.setFechaVencimiento(fechaVencimiento);
		motorTareaDao.guardar(tarea);
		return tarea;
	}
}