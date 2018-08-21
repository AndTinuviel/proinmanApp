package proinman.adminstracion;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import proinman.gestion.solicitud.entity.Usuario;
import proinman.gestion.solicitud.filtros.ControladorBase;
import proinman.gestion.solicitud.servicio.UsuarioService;
import proinman.gestion.solicitud.util.exception.EntidadNoGuardadaException;

@ManagedBean
@ViewScoped
public class UsuarioAdministracionController extends ControladorBase {

	@EJB
	private UsuarioService usuarioService;
	private List<Usuario> listaUsuarios;
	private Usuario usuarioSeleccionado;

	@PostConstruct
	public void inicializar() {
		listaUsuarios = new ArrayList<>();
		usuarioSeleccionado = new Usuario();
		consultar();
	}

	private void consultar() {
		listaUsuarios = usuarioService.consultarUsuarios();
	}

	public void eliminarUsuario(Usuario usuarioAEliminar) {
		FacesMessage msg = null;
		try {
			usuarioService.desactivarUsuario(usuarioAEliminar);
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro elimnado correctamente", null);
		} catch (EntidadNoGuardadaException e) {
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al elimnar el registro", null);
		} finally {
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public String actualizarUsuario(Usuario usuarioActualizar) {
		usuarioSeleccionado = usuarioActualizar;
		return "/pages/administracion/administracionUsuarioNuevo.jsf?jsf-redirect=true";
		
//		FacesMessage msg = null;
//		try {
//			usuarioService.actualizarUsuario(usuarioActualizar);
//			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro actualizado correctamente", null);
//		} catch (EntidadNoGuardadaException e) {
//			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al actualizar el registro", null);
//		} finally {
//			FacesContext.getCurrentInstance().addMessage(null, msg);
//		}
	}
	
	public void guardar(){
		try {
			usuarioService.crearUsuario(usuarioSeleccionado);
		} catch (EntidadNoGuardadaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public Usuario getUsuarioSeleccionado() {
		return usuarioSeleccionado;
	}

	public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
		this.usuarioSeleccionado = usuarioSeleccionado;
	}

}
