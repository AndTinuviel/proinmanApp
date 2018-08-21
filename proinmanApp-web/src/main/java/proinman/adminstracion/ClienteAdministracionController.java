package proinman.adminstracion;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import proinman.gestion.solicitud.entity.Cliente;
import proinman.gestion.solicitud.filtros.ControladorBase;
import proinman.gestion.solicitud.servicio.ClienteService;
import proinman.gestion.solicitud.util.exception.EntidadNoGuardadaException;

@ManagedBean
@ViewScoped
public class ClienteAdministracionController extends ControladorBase {

	@EJB
	private ClienteService clienteService;
	private List<Cliente> listaClientes;
	private Cliente clienteSeleccionado;

	@PostConstruct
	public void inicializar() {
		listaClientes = new ArrayList<>();
		clienteSeleccionado = new Cliente();
		consultar();
	}

	private void consultar() {
		listaClientes = clienteService.consultarClientes();
	}

	public void eliminarCliente(Cliente clienteAEliminar) {
		FacesMessage msg = null;
		try {
			clienteService.desactivarCliente(clienteAEliminar);
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro elimnado correctamente", null);
		} catch (EntidadNoGuardadaException e) {
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al elimnar el registro", null);
		} finally {
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public String actualizarUsuario(Cliente clienteActualizar) {
		clienteSeleccionado = clienteActualizar;
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
			clienteService.crearCliente(clienteSeleccionado);
		} catch (EntidadNoGuardadaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public ClienteService getClienteService() {
		return clienteService;
	}

	public void setClienteService(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public Cliente getClienteSeleccionado() {
		return clienteSeleccionado;
	}

	public void setClienteSeleccionado(Cliente clienteSeleccionado) {
		this.clienteSeleccionado = clienteSeleccionado;
	}

}
