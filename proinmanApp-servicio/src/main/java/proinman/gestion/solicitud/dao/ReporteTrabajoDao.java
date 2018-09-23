package proinman.gestion.solicitud.dao;

import java.io.Serializable;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import proinman.gestion.solicitud.entity.ReporteTrabajo;

@Stateless
@LocalBean
public class ReporteTrabajoDao extends BaseDaoGenerico<ReporteTrabajo, Serializable>{
	private static final long serialVersionUID = 5983461251198594025L;

	public ReporteTrabajoDao() {
		super(ReporteTrabajo.class);
	}

}
