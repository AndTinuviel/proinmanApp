package proinman.gestion.solicitud.dao;

import java.io.Serializable;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import proinman.gestion.solicitud.entity.Usuario;

@Stateless
@LocalBean
public class UsuarioDao extends BaseDaoGenerico<Usuario, Serializable> {
	private static final long serialVersionUID = 5983461251198594025L;

	public UsuarioDao() {
		super(Usuario.class);
	}

	public Usuario consultarUsuarioPorUsername(String username) {
		String consulta = "select u from Usuario u where u.username = :username ";
		Usuario usuario =

				(Usuario) this.em.createQuery(consulta, Usuario.class).setParameter("username", username)
						.getSingleResult();
		return usuario;
	}

	
}
