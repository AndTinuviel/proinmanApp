package proinman.gestion.solicitud.servicio;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import proinman.gestion.solicitud.dao.SolicitudDao;
import proinman.gestion.solicitud.entity.Solicitud;
import proinman.gestion.solicitud.util.exception.EntidadNoGuardadaException;

@Stateless
public class SolicitudService {
	@EJB
	private SolicitudDao solicitudDao;

	public double crearSolicitudTestRest() throws EntidadNoGuardadaException {
		Solicitud nuevaSolicitud = new Solicitud();
		this.solicitudDao.guardar(nuevaSolicitud);
		return 1.0D;
	}

	public Solicitud consultarSolicitud(int codigoSolicitud) {
		return this.solicitudDao.obtenerPorCodigo(Integer.valueOf(codigoSolicitud));
	}
	
	public Solicitud crearSolicitud(Solicitud nuevaSolicitud) throws EntidadNoGuardadaException {
		solicitudDao.guardar(nuevaSolicitud);
		return nuevaSolicitud;
	}
	
}