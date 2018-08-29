package proinman.gestion.solicitud.servicio;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import proinman.gestion.solicitud.dao.CotizacionDao;
import proinman.gestion.solicitud.dao.CotizacionItemDao;
import proinman.gestion.solicitud.entity.Cotizacion;
import proinman.gestion.solicitud.entity.CotizacionItem;
import proinman.gestion.solicitud.util.exception.EntidadNoGuardadaException;

@Stateless
@LocalBean
public class CotizacionService {

	@EJB
	private CotizacionDao cotizacionDao;
	@EJB
	private CotizacionItemDao cotizacionItemDao;
	@EJB
	private MotorTareaService motorTareaService;
	
	public void guardar(Cotizacion cotizacion) throws EntidadNoGuardadaException {
		cotizacionDao.guardar(cotizacion);
	}

	public Cotizacion guardarCotizacionCompleta(Cotizacion cotizacion, Integer codigoTarea) throws EntidadNoGuardadaException{
		guardarCotizacion(cotizacion);
		motorTareaService.finalizarTarea(codigoTarea);
		motorTareaService.crearTareaAprobarCotizacion(cotizacion.getSolicitud(),cotizacion.getSolicitud().getUsuario());
		return cotizacion;
	}

	private void guardarCotizacion(Cotizacion cotizacion) throws EntidadNoGuardadaException {
		cotizacionDao.guardar(cotizacion);
		for (CotizacionItem cotizacionItem : cotizacion.getListaCotizacionItems()) {
			cotizacionItem.setCotizacion(cotizacion);
			cotizacionItemDao.guardar(cotizacionItem);
		}
	}
	
	public Cotizacion consultarCotizacionPorCodigoSolicitud(Integer codigoSolicitud) {
		return cotizacionDao.consultarCotizacionPorCodigoSolicitud(codigoSolicitud);
	}
	
}
