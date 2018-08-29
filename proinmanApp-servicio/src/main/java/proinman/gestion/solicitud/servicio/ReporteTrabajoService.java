package proinman.gestion.solicitud.servicio;

import javax.ejb.EJB;

import proinman.gestion.solicitud.dao.ReporteTrabajoDao;
import proinman.gestion.solicitud.entity.ReporteTrabajo;
import proinman.gestion.solicitud.util.exception.EntidadNoGuardadaException;

public class ReporteTrabajoService {
	
	@EJB
	private ReporteTrabajoDao reporteTrabajoDao;
	
	public void guardarReporteTrabajo(ReporteTrabajo reporteTrabajo) throws EntidadNoGuardadaException{
		reporteTrabajoDao.guardar(reporteTrabajo);
	}
	
	

}
