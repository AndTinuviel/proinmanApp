package proinman.adminstracion;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.DualListModel;

import proinman.gestion.solicitud.entity.Rol;
import proinman.gestion.solicitud.entity.Usuario;
import proinman.gestion.solicitud.filtros.ControladorBase;
import proinman.gestion.solicitud.servicio.UsuarioRolService;
import proinman.gestion.solicitud.servicio.UsuarioService;

@ManagedBean
@ViewScoped
public class UsuarioRolController  extends ControladorBase{
	
	@EJB
	private UsuarioRolService usuarioRolService;
	@EJB
	private UsuarioService usuarioService;
	
	private DualListModel<Rol> listaRoles;
	
	private Integer codigoUsuario;
	
	private List<Usuario> listaDeUsuarios;
	
	private Usuario usuarioSeleccionado;

	@PostConstruct
	public void inicializar(){
		listaRoles= new DualListModel<>();
		listaDeUsuarios = new ArrayList<>();
		consultarRoles();
		consultarUsuarios();
	}
	
	private void consultarUsuarios(){
		listaDeUsuarios = usuarioService.consultarUsuarios();
	}
	
	private void consultarRoles(){
		usuarioRolService.consultarRoles();
		listaRoles.setSource(usuarioRolService.consultarRoles());
		listaRoles.setTarget(new ArrayList<Rol>() );
	}
	
	public void seleccionarUsuario(){
		listaRoles.setTarget(usuarioRolService.consultarRolPorCodigoUsuario(codigoUsuario));
		System.out.println("***************** listaRoles.getTarget().size() "+listaRoles.getTarget().size());
	}
	
	public DualListModel<Rol> getListaRoles() {
		return listaRoles;
	}

	public void setListaRoles(DualListModel<Rol> listaRoles) {
		this.listaRoles = listaRoles;
	}

	public List<Usuario> getListaDeUsuarios() {
		return listaDeUsuarios;
	}

	public void setListaDeUsuarios(List<Usuario> listaDeUsuarios) {
		this.listaDeUsuarios = listaDeUsuarios;
	}

	public Integer getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(Integer codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}
	
	

}
