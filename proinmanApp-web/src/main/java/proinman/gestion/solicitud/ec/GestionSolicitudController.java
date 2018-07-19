package proinman.gestion.solicitud.ec;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


import proinman.gestion.solicitud.entity.Cliente;
import proinman.gestion.solicitud.entity.UbicacionGeografica;
import proinman.gestion.solicitud.filtros.ControladorBase;
import proinman.gestion.solicitud.servicio.ClienteService;
import proinman.gestion.solicitud.servicio.UbicacionGeograficaService;

@ManagedBean
@ViewScoped
public class GestionSolicitudController extends ControladorBase{

	private List<Cliente> listaClientes;
	private List<UbicacionGeografica> listaProvincias;
	private List<UbicacionGeografica> listaCiudades;
	private Integer codigoCliente;
	private Integer codigoProvincia;
	private Integer codigoCiudad;
	@EJB
	private ClienteService clienteService;
	@EJB 
	private UbicacionGeograficaService ubicacionGeograficaService;

	
	
	@PostConstruct
	public void inicializar(){
		listaClientes = new ArrayList<>();
		listaProvincias = new ArrayList<>();
		listaCiudades = new ArrayList<>();
		carcarDatosIniciales();
	}


	private void carcarDatosIniciales() {
		listaClientes = clienteService.consultarClientes();
		listaProvincias.addAll(ubicacionGeograficaService.consultarProvinciasPorRegion(1));
		listaProvincias.addAll(ubicacionGeograficaService.consultarProvinciasPorRegion(2));
		listaProvincias.addAll(ubicacionGeograficaService.consultarProvinciasPorRegion(3));
		listaProvincias.addAll(ubicacionGeograficaService.consultarProvinciasPorRegion(4));
	}

	public void consultarCiudades(){
		listaCiudades.addAll(ubicacionGeograficaService.consultarCantonesPorProvincia(codigoProvincia));
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}


	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}


	public Integer getCodigoCliente() {
		return codigoCliente;
	}


	public void setCodigoCliente(Integer codigoCliente) {
		this.codigoCliente = codigoCliente;
	}


	public UbicacionGeograficaService getUbicacionGeograficaService() {
		return ubicacionGeograficaService;
	}


	public void setUbicacionGeograficaService(UbicacionGeograficaService ubicacionGeograficaService) {
		this.ubicacionGeograficaService = ubicacionGeograficaService;
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


	public Integer getCodigoCiudad() {
		return codigoCiudad;
	}


	public void setCodigoCiudad(Integer codigoCiudad) {
		this.codigoCiudad = codigoCiudad;
	}
	
	
	
}
