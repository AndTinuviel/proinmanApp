package proinman.gestion.solicitud.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import proinman.gestion.solicitud.entity.UsuarioRol;

@Stateless
@LocalBean
public class UsuarioRolDao extends BaseDaoGenerico<UsuarioRol, Serializable> {
	private static final long serialVersionUID = 5983461251198594025L;

	public UsuarioRolDao() {
		super(UsuarioRol.class);
	}
	
	public List<UsuarioRol> consultarUsuarioRolPorCodigoUsuario(Integer codigoUsuario){
		String consulta = "select ur from UsuarioRol ur INNER JOIN ur.usuario u  "
				+ " where ur.usuario.codigoUsuario = :codigoUsuario ";

		List<UsuarioRol> listaUsuarioRol = this.em.createQuery(consulta, UsuarioRol.class)
				.setParameter("codigoUsuario", codigoUsuario).getResultList();
		return listaUsuarioRol;
	}
}
