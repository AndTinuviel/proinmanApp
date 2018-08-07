package proinman.gestion.solicitud.dao;

import java.io.Serializable;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import proinman.gestion.solicitud.entity.Cotizacion;

@Stateless
@LocalBean
public class CotizacionDao extends BaseDaoGenerico<Cotizacion, Serializable>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7325449453514109787L;
	
	public CotizacionDao() {
		super(Cotizacion.class);
	}
	
	public void flush(){
		em.flush();
	}

}
