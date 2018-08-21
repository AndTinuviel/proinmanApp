package proinman.gestion.solicitud.servicio;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import proinman.gestion.solicitud.dao.UsuarioDao;
import proinman.gestion.solicitud.entity.Usuario;
import proinman.gestion.solicitud.util.exception.EntidadNoGuardadaException;

@Stateless
@LocalBean
public class UsuarioService {

	@EJB
	private UsuarioDao usuarioDao;

	public boolean loguearUsuario(String nombreUsuario, String password) {
		Usuario usuario = this.usuarioDao.consultarUsuarioPorUsername(nombreUsuario);

		String passwordIngresadoMD5 = convertirMD5(password.toUpperCase().trim());
		System.out.println("MD5:" + passwordIngresadoMD5);
		if (passwordIngresadoMD5.equals(usuario.getPassword())) {
			return true;
		}
		return false;
	}

	public Usuario crearUsuario(Usuario usuario) throws EntidadNoGuardadaException {
		usuario.setPassword(convertirMD5(usuario.getCedula()));
		usuario.setEstado("ACT");
		usuarioDao.guardar(usuario);
		return usuario;
	}

	public List<Usuario> consultarUsuarios() {
		return usuarioDao.obtenerTodos();
	}

	public Usuario consultarUsuarioPorId(Integer codigoUsuario) {
		return usuarioDao.obtenerPorCodigo(codigoUsuario);
	}

	public Usuario consultarUsuarioPorUsername(String username) {
		return usuarioDao.consultarUsuarioPorUsername(username);
	}

	public void desactivarUsuario(Usuario usuarioaEliminar) throws EntidadNoGuardadaException {
		usuarioaEliminar.setEstado("INA");
		actualizarUsuario(usuarioaEliminar);
	}

	public void actualizarUsuario(Usuario usuarioActualizar) throws EntidadNoGuardadaException {
		usuarioDao.guardarOActualizar(usuarioActualizar);
	}

	public Usuario bloquearUsuario() {
		return null;
	}

	private int contarIntentosFallidosLogueo() {
		return 0;
	}

	private String convertirMD5(String password) {
		try {
			char[] HEXADECIMALES = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

			MessageDigest msgdgt = MessageDigest.getInstance("MD5");
			byte[] bytes = msgdgt.digest(password.getBytes());
			StringBuilder strCryptMD5 = new StringBuilder(2 * bytes.length);
			for (int i = 0; i < bytes.length; i++) {
				int low = bytes[i] & 0xF;
				int high = (bytes[i] & 0xF0) >> 4;
				strCryptMD5.append(HEXADECIMALES[high]);
				strCryptMD5.append(HEXADECIMALES[low]);
			}
			return strCryptMD5.toString();
		} catch (NoSuchAlgorithmException e) {
		}
		return null;
	}

	public List<Usuario> consultarUsuariosQuePuedenCotizar() {
		List<Usuario> listaUsuariosQuePuedenCotizar= usuarioDao.consultarUsuariosPorCodigoRol(1);
		listaUsuariosQuePuedenCotizar.addAll(usuarioDao.consultarUsuariosPorCodigoRol(2));
		return listaUsuariosQuePuedenCotizar;
	}
}
