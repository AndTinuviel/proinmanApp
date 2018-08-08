package proinman.gestion.solicitud.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import proinman.gestion.solicitud.entity.Usuario;
import proinman.gestion.solicitud.entity.UsuarioRol;

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

	public List<Usuario> consultarUsuariosPorCodigoRol(Integer codigoRol) {
		List<Usuario> listaUsuarios = obtenerTodos();
		List<Usuario> listaUsuariosPorCodigo = new ArrayList<>();

		for (Usuario usuario : listaUsuarios) {
			for (UsuarioRol usuarioRol : usuario.getListaUsuarioRol()) {
				if (usuarioRol.getRol().getCodigoRol().equals(codigoRol)) {
					listaUsuariosPorCodigo.add(usuario);
				}
			}
		}
		return listaUsuariosPorCodigo;
	}

}
