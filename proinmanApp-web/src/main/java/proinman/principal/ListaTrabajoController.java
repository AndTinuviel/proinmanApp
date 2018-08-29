package proinman.principal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import proinman.gestion.solicitud.entity.MotorTarea;
import proinman.gestion.solicitud.filtros.ControladorBase;
import proinman.gestion.solicitud.servicio.MotorTareaService;


@ManagedBean
@ViewScoped
public class ListaTrabajoController extends ControladorBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1126574371398347626L;
	
	private List<MotorTarea> listaTareas;
	private MotorTarea tareaSeleccionada;	
	
	@EJB
	private MotorTareaService motorTareaService;
	
	@PostConstruct
	public void inicializar(){
		listaTareas = new ArrayList<>();
		consultarTareas();
	}
	
	private void consultarTareas(){
		listaTareas = motorTareaService.consultarTareasPorUsuario("rcruz");//getUsuarioConectado()));
	}

	public String seleccionarTarea(MotorTarea tarea){
		this.tareaSeleccionada = tarea;
		return tarea.getMotorActividad().getDireccionPagina() + "?faces-redirect=true";
	}
	
	public List<MotorTarea> getListaTareas() {
		return listaTareas;
	}

	public void setListaTareas(List<MotorTarea> listaTareas) {
		this.listaTareas = listaTareas;
	}

	public MotorTarea getTareaSeleccionada() {
		return tareaSeleccionada;
	}

	public void setTareaSeleccionada(MotorTarea tareaSeleccionada) {
		this.tareaSeleccionada = tareaSeleccionada;
	}

}
