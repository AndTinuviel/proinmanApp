package proinman.gestion.solicitud.servicio;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import proinman.gestion.solicitud.dao.RolDao;
import proinman.gestion.solicitud.dao.UsuarioDao;
import proinman.gestion.solicitud.dao.UsuarioRolDao;
import proinman.gestion.solicitud.entity.Rol;
import proinman.gestion.solicitud.entity.Usuario;
import proinman.gestion.solicitud.entity.UsuarioRol;
import proinman.gestion.solicitud.util.exception.EntidadNoGuardadaException;

@Stateless
public class UsuarioRolService {
	@EJB
	private UsuarioRolDao usuarioRolDao;
	@EJB
	private UsuarioDao usuarioDao;
	@EJB
	private RolDao rolDao;

	public UsuarioRol asignarRolAUsuario(Integer codigoRol, Integer codigoUsuario) {
		Usuario usuario = (Usuario) this.usuarioDao.obtenerPorCodigo(codigoUsuario);
		Rol rol = (Rol) this.rolDao.obtenerPorCodigo(codigoRol);

		UsuarioRol nuevaAsignacionRolAUsuario = new UsuarioRol();
		nuevaAsignacionRolAUsuario.setRol(rol);
		nuevaAsignacionRolAUsuario.setUsuario(usuario);
		nuevaAsignacionRolAUsuario.setEstado("ACT");
		try {
			this.usuarioRolDao.guardar(nuevaAsignacionRolAUsuario);
			return nuevaAsignacionRolAUsuario;
		} catch (EntidadNoGuardadaException e) {
		}
		return new UsuarioRol();
	}

	public List<Rol> consultarRoles() {
		return rolDao.obtenerTodos();
	}

	public List<Rol> consultarRolPorCodigoUsuario(Integer codigoUsuario) {
		List<Rol> rolesUsuario = new ArrayList<>();
		List<UsuarioRol> listaUsuarioRol = usuarioRolDao.consultarUsuarioRolPorCodigoUsuario(codigoUsuario);
		for (UsuarioRol usuarioRol : listaUsuarioRol) {
			rolesUsuario.add(usuarioRol.getRol());
		}
		return rolesUsuario;
	}

}
