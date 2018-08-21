package proinman.gestion.solicitud.ws;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import proinman.gestion.solicitud.entity.Cotizacion;
import proinman.gestion.solicitud.entity.CotizacionItem;
import proinman.gestion.solicitud.entity.Solicitud;
import proinman.gestion.solicitud.servicio.SolicitudService;

@Path("/gestionSolicitud")
public class SolicitudRest {
	@EJB
	private SolicitudService solicitudService;

	@GET
	@Path("/consultarSolicitudPorCodigo")
	@Produces({ "application/json" })
	public Solicitud consultarSolicitudPorCodigo(@QueryParam("codigoSolicitud") Integer codigoSolicitud) {
		Solicitud solicitud = solicitudService.consultarSolicitud(codigoSolicitud);
		for (Cotizacion cotizacion : solicitud.getListaCotizaciones()) {
			cotizacion.setSolicitud(null);
			cotizacion.setListaSecuencialSolicituds(null);
			for (CotizacionItem cotizacionItem : cotizacion.getListaCotizacionItems()) {
				cotizacionItem.setCotizacion(null);
			}
		}
		solicitud.getUsuario().setListaUsuarioRol(null);
		return solicitud;
	}

}
