package proinman.gestion.solicitud.dao;

import java.io.Serializable;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import proinman.gestion.solicitud.entity.Rol;

@Stateless
@LocalBean
public class RolDao extends BaseDaoGenerico<Rol, Serializable> {
	private static final long serialVersionUID = 5983461251198594025L;

	public RolDao() {
		super(Rol.class);
	}
}
