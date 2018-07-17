package proinman.gestion.solicitud.dao;

import java.io.Serializable;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import proinman.gestion.solicitud.entity.Solicitud;

@Stateless
@LocalBean
public class SolicitudDao extends BaseDaoGenerico<Solicitud, Serializable> {
	private static final long serialVersionUID = -577107758000044062L;

	public SolicitudDao() {
		super(Solicitud.class);
	}
}
