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
@Table(name = "pss_trazabilidad_solicitud", schema = "proinman_movil")
@NamedQuery(name = "TrazabilidadSolicitud.findAll", query = "SELECT t FROM TrazabilidadSolicitud t")
public class TrazabilidadSolicitud implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo_trazabilidad")
	private Integer codigoTrazabilidad;
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_cambio_estado")
	private Date fechaCambioEstado;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_usuario")
	private Usuario pssUsuario;

	public Integer getCodigoTrazabilidad() {
		return this.codigoTrazabilidad;
	}

	public void setCodigoTrazabilidad(Integer codigoTrazabilidad) {
		this.codigoTrazabilidad = codigoTrazabilidad;
	}

	public Date getFechaCambioEstado() {
		return this.fechaCambioEstado;
	}

	public void setFechaCambioEstado(Date fechaCambioEstado) {
		this.fechaCambioEstado = fechaCambioEstado;
	}

	public Usuario getPssUsuario() {
		return this.pssUsuario;
	}

	public void setPssUsuario(Usuario pssUsuario) {
		this.pssUsuario = pssUsuario;
	}
}
