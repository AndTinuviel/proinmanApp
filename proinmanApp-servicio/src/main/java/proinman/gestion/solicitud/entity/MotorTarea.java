package proinman.gestion.solicitud.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "pss_motor_tarea", schema = "proinman_movil")
@NamedQuery(name = "MotorTarea.findAll", query = "SELECT m FROM MotorTarea m")
public class MotorTarea implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo_tarea")
	private Integer codigoTarea;
	
	
	private String estado;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_asignacion")
	private Date fechaAsignacion;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_finalizacion")
	private Date fechaFinalizacion;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_vencimiento")
	private Date fechaVencimiento;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_actividad")
	private MotorActividad motorActividad;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_solicitud")
	private Solicitud solicitud;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_usuario")
	private Usuario usuario;

	public Integer getCodigoTarea() {
		return this.codigoTarea;
	}

	public void setCodigoTarea(Integer codigoTarea) {
		this.codigoTarea = codigoTarea;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaAsignacion() {
		return this.fechaAsignacion;
	}

	public void setFechaAsignacion(Date fechaAsignacion) {
		this.fechaAsignacion = fechaAsignacion;
	}

	public Date getFechaFinalizacion() {
		return this.fechaFinalizacion;
	}

	public void setFechaFinalizacion(Date fechaFinalizacion) {
		this.fechaFinalizacion = fechaFinalizacion;
	}

	public Date getFechaVencimiento() {
		return this.fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public MotorActividad getMotorActividad() {
		return motorActividad;
	}

	public void setMotorActividad(MotorActividad motorActividad) {
		this.motorActividad = motorActividad;
	}

	public Solicitud getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
