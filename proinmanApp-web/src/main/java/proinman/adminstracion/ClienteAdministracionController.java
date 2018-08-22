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
import proinman.gestion.solicitud.entity.UbicacionGeografica;
import proinman.gestion.solicitud.filtros.ControladorBase;
import proinman.gestion.solicitud.servicio.ClienteService;
import proinman.gestion.solicitud.servicio.UbicacionGeograficaService;
import proinman.gestion.solicitud.util.exception.EntidadNoGuardadaException;

@ManagedBean
@ViewScoped
public class ClienteAdministracionController extends ControladorBase {

	@EJB
	private ClienteService clienteService;
	@EJB 
	private UbicacionGeograficaService ubicacionGeograficaService;
	
	private List<Cliente> listaClientes;
	private Cliente clienteSeleccionado;
	private Integer codigoProvincia;
	private List<UbicacionGeografica> listaProvincias;
	private List<UbicacionGeografica> listaCiudades;

	@PostConstruct
	public void inicializar() {
		listaClientes = new ArrayList<>();
		clienteSeleccionado = new Cliente();
		listaProvincias = new ArrayList<>();
		listaCiudades = new ArrayList<>();
		consultar();
	}

	private void consultar() {
		listaClientes = clienteService.consultarClientes();
		listaProvincias.addAll(ubicacionGeograficaService.consultarProvinciasPorRegion(1));
		listaProvincias.addAll(ubicacionGeograficaService.consultarProvinciasPorRegion(2));
		listaProvincias.addAll(ubicacionGeograficaService.consultarProvinciasPorRegion(3));
		listaProvincias.addAll(ubicacionGeograficaService.consultarProvinciasPorRegion(4));
	}
	
	public void consultarCiudades(){
		listaCiudades.addAll(ubicacionGeograficaService.consultarCantonesPorProvincia(codigoProvincia));
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

	public String actualizarCliente(Cliente clienteActualizar) {
		clienteSeleccionado = clienteActualizar;
		return "/pages/administracion/administracionClienteNuevo.jsf?jsf-redirect=true";
		
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

	public Integer getCodigoProvincia() {
		return codigoProvincia;
	}

	public void setCodigoProvincia(Integer codigoProvincia) {
		this.codigoProvincia = codigoProvincia;
	}

	public List<UbicacionGeografica> getListaProvincias() {
		return listaProvincias;
	}

	public void setListaProvincias(List<UbicacionGeografica> listaProvincias) {
		this.listaProvincias = listaProvincias;
	}

	public List<UbicacionGeografica> getListaCiudades() {
		return listaCiudades;
	}

	public void setListaCiudades(List<UbicacionGeografica> listaCiudades) {
		this.listaCiudades = listaCiudades;
	}

}
