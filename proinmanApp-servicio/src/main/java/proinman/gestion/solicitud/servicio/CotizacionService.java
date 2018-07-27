package proinman.gestion.solicitud.servicio;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import proinman.gestion.solicitud.dao.CotizacionDao;
import proinman.gestion.solicitud.entity.Cotizacion;
import proinman.gestion.solicitud.util.exception.EntidadNoGuardadaException;

@Stateless
@LocalBean
public class CotizacionService {

	@EJB
	private CotizacionDao cotizacionDao;
	
	public void guardar(Cotizacion cotizacion) throws EntidadNoGuardadaException {
		cotizacionDao.guardar(cotizacion);
	}

	
	
	
}
