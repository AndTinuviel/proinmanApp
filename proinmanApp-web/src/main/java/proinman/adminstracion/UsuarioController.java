package proinman.adminstracion;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import proinman.gestion.solicitud.entity.Usuario;
import proinman.gestion.solicitud.filtros.ControladorBase;
import proinman.gestion.solicitud.servicio.UsuarioService;
import proinman.gestion.solicitud.util.exception.EntidadNoGuardadaException;

@ManagedBean
@ViewScoped
public class UsuarioController extends ControladorBase{
	
	private Usuario usuario;
	@EJB
	private UsuarioService usuarioService;
	
	
	@PostConstruct
	public void inicializar(){
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public void guardar(){
		try {
			System.out.println("***************** guardar");
			System.out.println("***************** usuarioService "+usuarioService);
			usuarioService.crearUsuario(usuario);
		} catch (EntidadNoGuardadaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
