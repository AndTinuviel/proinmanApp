package proinman.gestion.solicitud.ws;

import javax.ejb.EJB;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import proinman.gestion.solicitud.entity.ReporteTrabajo;
import proinman.gestion.solicitud.servicio.ReporteTrabajoService;
import proinman.gestion.solicitud.util.exception.EntidadNoGuardadaException;

@Path("/reporteTrabajo")
public class ReporteTrabajoRest {
	
	@EJB
	private ReporteTrabajoService reporteTrabajoService;
	
	@POST
	@Path("/guardar")
	@Produces({ "application/json" })
	public void guardarReporte(){//@QueryParam("reporte") ReporteTrabajo reporte){
		try {
			ReporteTrabajo reporte = new ReporteTrabajo();
			reporteTrabajoService.guardarReporteTrabajo(reporte);
		} catch (EntidadNoGuardadaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
