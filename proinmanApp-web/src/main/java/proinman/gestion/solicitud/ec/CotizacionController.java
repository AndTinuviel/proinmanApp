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

import proinman.gestion.solicitud.entity.CotizacionItem;
import proinman.gestion.solicitud.entity.Solicitud;
import proinman.gestion.solicitud.filtros.ControladorBase;
import proinman.gestion.solicitud.servicio.CotizacionService;
import proinman.gestion.solicitud.servicio.SolicitudService;

@ManagedBean
@ViewScoped
public class CotizacionController extends ControladorBase{

	private List<CotizacionItem> listaCotizacionItem;
	private Solicitud solicitud;
	
	@EJB
	private SolicitudService solicitudService;
	@EJB
	private CotizacionService cotizacionService;
	
	
	 
    @PostConstruct
    public void inicializar() {
    	System.out.println("*********************  anntes de consultar cliente ");
    	solicitud = solicitudService.consultarSolicitud(2);
    	System.out.println("*********************  cliente "+solicitud);
    	listaCotizacionItem = new ArrayList<>();
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
     	listaCotizacionItem.add(cotizacionItem);
        FacesMessage msg = new FacesMessage("New cotizacionItem added", cotizacionItem.getCodigoItem().toString());
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
	
}
