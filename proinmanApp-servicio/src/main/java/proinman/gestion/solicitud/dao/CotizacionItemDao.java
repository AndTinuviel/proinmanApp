package proinman.gestion.solicitud.dao;

import java.io.Serializable;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import proinman.gestion.solicitud.entity.CotizacionItem;

@Stateless
@LocalBean
public class CotizacionItemDao extends BaseDaoGenerico<CotizacionItem, Serializable>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1466816522295968636L;
	
	public CotizacionItemDao() {
		super(CotizacionItem.class);
	}

}
