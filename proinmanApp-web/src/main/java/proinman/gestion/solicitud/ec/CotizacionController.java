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
import proinman.gestion.solicitud.entity.CotizacionItem;
import proinman.gestion.solicitud.entity.Solicitud;
import proinman.gestion.solicitud.filtros.ControladorBase;
import proinman.gestion.solicitud.servicio.CotizacionService;
import proinman.gestion.solicitud.servicio.SolicitudService;
import proinman.gestion.solicitud.utilitarios.TipoItemEnum;

@ManagedBean
@ViewScoped
public class CotizacionController extends ControladorBase{

	private List<CotizacionItem> listaCotizacionItem;
	private Solicitud solicitud;
	private List<CatalogoItem> listaCatalogoItems;
	private TipoItemEnum tipoItemEnum;
	private Integer codigoTipoItem; 
	
	@EJB
	private SolicitudService solicitudService;
	@EJB
	private CotizacionService cotizacionService;
	
	
	 
    @PostConstruct
    public void inicializar() {
    	solicitud = solicitudService.consultarSolicitud(2);
    	listaCotizacionItem = new ArrayList<>();
    	listaCatalogoItems = new ArrayList<>();
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
        // Add one new car to the table:
        CotizacionItem cotizacionItem = new CotizacionItem();
        cotizacionItem.setCosto(new BigDecimal("0.15"));
        cotizacionItem.setCantidad(new BigDecimal("5"));
        cotizacionItem.setPrecio(new BigDecimal("0.10"));
        CatalogoItem catalogoItem = new CatalogoItem();
        catalogoItem.setDescripcion("cemento");
        cotizacionItem.setCatalogoItem(catalogoItem);
     	listaCotizacionItem.add(cotizacionItem);
        FacesMessage msg = new FacesMessage("New cotizacionItem added", cotizacionItem.getCantidad().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
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


	public List<CatalogoItem> getListaCatalogoItems() {
		return listaCatalogoItems;
	}


	public void setListaCatalogoItems(List<CatalogoItem> listaCatalogoItems) {
		this.listaCatalogoItems = listaCatalogoItems;
	}


	public Integer getCodigoTipoItem() {
		return codigoTipoItem;
	}


	public void setCodigoTipoItem(Integer codigoTipoItem) {
		this.codigoTipoItem = codigoTipoItem;
	}
}
