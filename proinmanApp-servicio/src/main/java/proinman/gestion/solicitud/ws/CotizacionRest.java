package proinman.gestion.solicitud.ws;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import proinman.gestion.solicitud.entity.Cotizacion;
import proinman.gestion.solicitud.entity.CotizacionItem;
import proinman.gestion.solicitud.servicio.CotizacionService;

@Path("/gestionCotizacion")
public class CotizacionRest {
	@EJB
	private CotizacionService cotizacionService;

	@GET
	@Path("/consultarCotizacionPorCodigoSolicitud")
	@Produces({ "application/json" })
	public Cotizacion consultarCotizacionPorCodigoSolicitud(@QueryParam("codigoSolicitud") Integer codigoSolicitud) {
		Cotizacion cotizacion = cotizacionService.consultarCotizacionPorCodigoSolicitud(codigoSolicitud);
		cotizacion.getSolicitud().setListaCotizaciones(null);
		cotizacion.getSolicitud().getUsuario().setListaUsuarioRol(null);
		cotizacion.setListaSecuencialSolicituds(null);
		for (CotizacionItem item : cotizacion.getListaCotizacionItems()) {
			item.setCotizacion(null);
		}
		return cotizacion;
	}

}
