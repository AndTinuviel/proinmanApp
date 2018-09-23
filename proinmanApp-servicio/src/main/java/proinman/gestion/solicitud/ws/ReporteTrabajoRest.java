package proinman.gestion.solicitud.ws;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.web.bind.annotation.RequestBody;

import proinman.gestion.solicitud.entity.ReporteTrabajo;
import proinman.gestion.solicitud.servicio.ReporteTrabajoService;
import proinman.gestion.solicitud.util.exception.EntidadNoGuardadaException;

@Path("/reporteTrabajo")
public class ReporteTrabajoRest {
	
	@EJB
	private ReporteTrabajoService reporteTrabajoService;
	
	@POST
	@Path("/guardar")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response guardarReporte(@RequestBody ReporteTrabajo reporte){
		try {
			System.out.println("************* getObservacion "+reporte.getObservacion());
			System.out.println("************* getFechaHoraDesde "+reporte.getFechaHoraDesde());
			System.out.println("************* getFechaHoraHasta "+reporte.getFechaHoraHasta());
			reporteTrabajoService.guardarReporteTrabajo(reporte);
			return  Response.status(200).entity("Guardado").build();
		} catch (EntidadNoGuardadaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return  Response.status(201).entity("NO Guardado").build();
		}
	}

}
