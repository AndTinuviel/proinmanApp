package proinman.gestion.solicitud.ec;

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
import proinman.gestion.solicitud.servicio.SolicitudService;
import proinman.gestion.solicitud.utilitarios.TipoItemEnum;

@ManagedBean
@ViewScoped
public class CotizacionController extends ControladorBase{

	private List<CotizacionItem> listaCotizacionItem;
	private Solicitud solicitud;
	private List<CatalogoItem> listaCatalogoMateriales;
	private List<CatalogoItem> listaCatalogoManoDeObra;
	private TipoItemEnum tipoItemEnum;
	private Integer codigoTipoItem; 
	private Cotizacion cotizacionNueva;
	private CotizacionItem itemNuevo;
	
	@EJB
	private SolicitudService solicitudService;
	@EJB
	private CotizacionService cotizacionService;
	@EJB
	private CatalogoItemService catalogoItemService;
	
	
	 
    @PostConstruct
    public void inicializar() {
    	solicitud = solicitudService.consultarSolicitud(2);
    	cotizacionNueva = new Cotizacion();
    	itemNuevo = new CotizacionItem();
    	cotizacionNueva.setSolicitud(solicitud);
    	listaCotizacionItem = new ArrayList<>();
    	listaCatalogoMateriales = catalogoItemService.buscarMateriales();
    	listaCatalogoManoDeObra = catalogoItemService.buscarManoDeObra();
    }
 
    public void eliminarItem(CotizacionItem itemAEliminar){
    	listaCotizacionItem.remove(itemAEliminar);
    }
    
    public void modificarItem(CotizacionItem itemAmodificar){
    	itemNuevo = itemAmodificar;
    }
 
    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Car Edited", ((CotizacionItem) event.getObject()).getCodigoItem().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
 
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((CotizacionItem) event.getObject()).getCodigoItem().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
 
    public void onAddNew() {
    	CotizacionItem itemAAgregar = new CotizacionItem();
    	itemAAgregar.setCantidad(itemNuevo.getCantidad());
    	itemAAgregar.setCosto(itemNuevo.getCosto());
    	itemAAgregar.setPrecio(itemNuevo.getPrecio());
    	itemAAgregar.setTotalCostoItem(itemNuevo.getTotalCostoItem());
    	itemAAgregar.setTotalPrecioItem(itemNuevo.getTotalPrecioItem());
     	listaCotizacionItem.add(itemAAgregar);
        FacesMessage msg = new FacesMessage("Nuevo item agregado", "nombre material o mano de obra");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void calcularTotales(){
    	System.out.println("***************** itemNuevo.getCantidad()"+itemNuevo.getCantidad());
    	System.out.println("***************** itemNuevo.getCosto()"+itemNuevo.getCosto());
    	System.out.println("***************** itemNuevo.getPrecio()"+itemNuevo.getPrecio());
    	itemNuevo.setTotalCostoItem(itemNuevo.getCantidad().multiply(itemNuevo.getCosto()));
    	itemNuevo.setTotalPrecioItem(itemNuevo.getCantidad().multiply(itemNuevo.getPrecio()));
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
}
