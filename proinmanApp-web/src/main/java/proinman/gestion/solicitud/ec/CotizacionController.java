package proinman.gestion.solicitud.ec;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import proinman.gestion.solicitud.entity.CatalogoItem;
import proinman.gestion.solicitud.entity.Cotizacion;
import proinman.gestion.solicitud.entity.CotizacionItem;
import proinman.gestion.solicitud.entity.Solicitud;
import proinman.gestion.solicitud.filtros.ControladorBase;
import proinman.gestion.solicitud.servicio.CatalogoItemService;
import proinman.gestion.solicitud.servicio.CotizacionService;
import proinman.gestion.solicitud.servicio.MotorTareaService;
import proinman.gestion.solicitud.servicio.SolicitudService;
import proinman.gestion.solicitud.util.exception.EntidadNoGuardadaException;
import proinman.gestion.solicitud.utilitarios.Constantes;
import proinman.gestion.solicitud.utilitarios.TipoItemEnum;

@ManagedBean
@ViewScoped
public class CotizacionController extends ControladorBase {

	private List<CotizacionItem> listaCotizacionItem;
	private Solicitud solicitud;
	private List<CatalogoItem> listaCatalogoMateriales;
	private List<CatalogoItem> listaCatalogoManoDeObra;
	private TipoItemEnum tipoItemEnum;
	private Integer codigoTipoItem;
	private Cotizacion cotizacionNueva;
	private CotizacionItem itemNuevo;
	private Boolean verCatalogoTipoItem;
	private Integer codigoTarea;

	@EJB
	private SolicitudService solicitudService;
	@EJB
	private CotizacionService cotizacionService;
	@EJB
	private CatalogoItemService catalogoItemService;


	@PostConstruct
	public void inicializar() {
		verCatalogoTipoItem = true;
		solicitud = new Solicitud();
		cotizacionNueva = new Cotizacion();
		itemNuevo = new CotizacionItem();
		cotizacionNueva.setSolicitud(solicitud);
		listaCotizacionItem = new ArrayList<>();
		listaCatalogoMateriales = catalogoItemService.buscarMateriales();
		listaCatalogoManoDeObra = catalogoItemService.buscarManoDeObra();
		consultarDatosIniciales();
	}

	private void consultarDatosIniciales(){
		codigoTarea = Integer.parseInt(getHttpRequest().getParameter("codigoTarea"));
		solicitud = solicitudService.consultarSolicitud(Integer.parseInt(getHttpRequest().getParameter("codigoSolicitud")));
		if (!solicitud.getListaCotizaciones().isEmpty()){
			cotizacionNueva = solicitud.getListaCotizaciones().get(0);
			listaCotizacionItem.addAll(solicitud.getListaCotizaciones().get(0).getListaCotizacionItems());
			cotizacionNueva.setIva(cotizacionNueva.getPrecioTotal().multiply(BigDecimal.valueOf(Constantes.CONST_IVA)));
			cotizacionNueva.setPrecioTotalIva(cotizacionNueva.getPrecioTotal().multiply(BigDecimal.valueOf(Constantes.CONST_IVA_TOTAL)));
		}
	}
	
	public void cambiarCatalogo() {
		if (tipoItemEnum.getVALOR().equals("MATERIAL"))
			verCatalogoTipoItem = true;
		else
			verCatalogoTipoItem = false;
	}

	public void eliminarItem(CotizacionItem itemAEliminar) {
		listaCotizacionItem.remove(itemAEliminar);
	}

	public void modificarItem(CotizacionItem itemAmodificar) {
		itemNuevo = itemAmodificar;
	}

	public void onRowEdit(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Car Edited",
				((CotizacionItem) event.getObject()).getCodigoItem().toString());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edit Cancelled",
				((CotizacionItem) event.getObject()).getCodigoItem().toString());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onAddNew() {
		crearNuevoitem();
		calcularTotalesCotizacion();
	}

	private void crearNuevoitem() {
		CotizacionItem itemAAgregar = new CotizacionItem();
		itemAAgregar.setCantidad(itemNuevo.getCantidad());
		itemAAgregar.setCosto(itemNuevo.getCosto());
		itemAAgregar.setPrecio(itemNuevo.getPrecio());
		itemAAgregar.setTotalCostoItem(itemNuevo.getTotalCostoItem());
		itemAAgregar.setTotalPrecioItem(itemNuevo.getTotalPrecioItem());
		CatalogoItem catalogoItem = catalogoItemService.buscarPorCodigo(codigoTipoItem);
		itemAAgregar.setCatalogoItem(catalogoItem);
		listaCotizacionItem.add(itemAAgregar);
		FacesMessage msg = new FacesMessage("Nuevo item agregado", "nombre material o mano de obra");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void calcularTotales() {
		if (itemNuevo.getCantidad() == null || itemNuevo.getCosto() == null || itemNuevo.getPrecio() == null) {
			System.out.println("No se calcula con valores null");
		} else {
			itemNuevo.setTotalCostoItem(itemNuevo.getCantidad().multiply(itemNuevo.getCosto()));
			itemNuevo.setTotalPrecioItem(itemNuevo.getCantidad().multiply(itemNuevo.getPrecio()));
		}

	}

	public void guardar() {
		FacesMessage msg = null;
		try {
			setearDatosDeCotizacion();
			cotizacionService.guardarCotizacionCompleta(cotizacionNueva, codigoTarea);
			
			msg = new FacesMessage("Cotización", "Se guardó la cotización correctamente");
		} catch (EntidadNoGuardadaException e) {
			msg = new FacesMessage("Cotización", "No se pudo guardar la cotización");
		} finally {
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	
	public void aprobarCotizacion(){
		FacesMessage msg = null;
		try {
			cotizacionNueva.setEstado("APR");
			cotizacionService.aprobarCotizacion(cotizacionNueva, codigoTarea); // actualizarEstadoCotizacion(cotizacionNueva);
			msg = new FacesMessage("Cotización", "Se aprobó la cotización correctamente");
		} catch (EntidadNoGuardadaException e) {
			msg = new FacesMessage("Cotización", "No se pudo aprobó la cotización");
		} finally {
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void noAprobarCotizacion(){
		FacesMessage msg = null;
		try {
			cotizacionNueva.setEstado("NO_APR");
			cotizacionService.actualizarEstadoCotizacion(cotizacionNueva);
			msg = new FacesMessage("Cotización", "Se finaliza el flujo del requerimiento correctamente");
		} catch (EntidadNoGuardadaException e) {
			msg = new FacesMessage("Cotización", "No se pudo finaliza el flujo del requerimiento");
		} finally {
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	

	private void setearDatosDeCotizacion() {
		cotizacionNueva.setListaCotizacionItems(listaCotizacionItem);
		cotizacionNueva.setEstado("CRE");
		cotizacionNueva.setSolicitud(solicitud);
	}

	private void calcularTotalesCotizacion() {
		Double totalPrecioCotizacion = 0.0;
		Double totalCostoCotizacion = 0.0;
		for (CotizacionItem item : listaCotizacionItem) {
			totalPrecioCotizacion = totalPrecioCotizacion + item.getTotalPrecioItem().doubleValue();
			totalCostoCotizacion = totalCostoCotizacion + item.getTotalCostoItem().doubleValue();

		}
		cotizacionNueva.setIva(BigDecimal.valueOf(totalCostoCotizacion * Constantes.CONST_IVA));
		cotizacionNueva.setPrecioTotalIva(cotizacionNueva.getIva().add(BigDecimal.valueOf(totalPrecioCotizacion)));
		cotizacionNueva.setCostoTotal(BigDecimal.valueOf(totalCostoCotizacion));
		cotizacionNueva.setPrecioTotal(BigDecimal.valueOf(totalPrecioCotizacion));
	}

	public List<CotizacionItem> getListaCotizacionItem() {
		return listaCotizacionItem;
	}

	public void setListaCotizacionItem(List<CotizacionItem> listaCotizacionItem) {
		this.listaCotizacionItem = listaCotizacionItem;
	}

	public Solicitud getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}

	public TipoItemEnum getTipoItemEnum() {
		return tipoItemEnum;
	}

	public void setTipoItemEnum(TipoItemEnum tipoItemEnum) {
		this.tipoItemEnum = tipoItemEnum;
	}

	public TipoItemEnum[] getTipoItemEnumMatMan() {
		return TipoItemEnum.values();
	}

	public Integer getCodigoTipoItem() {
		return codigoTipoItem;
	}

	public void setCodigoTipoItem(Integer codigoTipoItem) {
		this.codigoTipoItem = codigoTipoItem;
	}

	public Cotizacion getCotizacionNueva() {
		return cotizacionNueva;
	}

	public void setCotizacionNueva(Cotizacion cotizacionNueva) {
		this.cotizacionNueva = cotizacionNueva;
	}

	public List<CatalogoItem> getListaCatalogoMateriales() {
		return listaCatalogoMateriales;
	}

	public void setListaCatalogoMateriales(List<CatalogoItem> listaCatalogoMateriales) {
		this.listaCatalogoMateriales = listaCatalogoMateriales;
	}

	public List<CatalogoItem> getListaCatalogoManoDeObra() {
		return listaCatalogoManoDeObra;
	}

	public void setListaCatalogoManoDeObra(List<CatalogoItem> listaCatalogoManoDeObra) {
		this.listaCatalogoManoDeObra = listaCatalogoManoDeObra;
	}

	public CotizacionItem getItemNuevo() {
		return itemNuevo;
	}

	public void setItemNuevo(CotizacionItem itemNuevo) {
		this.itemNuevo = itemNuevo;
	}

	public Boolean getVerCatalogoTipoItem() {
		return verCatalogoTipoItem;
	}

	public void setVerCatalogoTipoItem(Boolean verCatalogoTipoItem) {
		this.verCatalogoTipoItem = verCatalogoTipoItem;
	}
}
