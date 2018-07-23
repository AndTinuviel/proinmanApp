package proinman.gestion.solicitud.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "pss_solicitud", schema = "proinman_movil")
@NamedQuery(name = "Solicitud.findAll", query = "SELECT s FROM Solicitud s")
public class Solicitud implements Serializable {
	private static final long serialVersionUID = -4963674792768308809L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo_solicitud")
	private Integer codigoSolicitud;
	
	@Column(name = "descipcion")
	private String descipcion;
	
	@Column(name = "direccion")
	private String direccion;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_recepcion")
	private Date fechaRecepcion;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_registro")
	private Date fechaRegistro;
	
	@OneToMany(mappedBy = "pssSolicitud")
	private List<Cotizacion> listaCotizaciones;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "codigo_cliente")
	private Cliente cliente;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "codigo_ubicacion_geografica")
	private UbicacionGeografica ciudad;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_usuario")
	private Usuario usuario;
	

	public Integer getCodigoSolicitud() {
		return this.codigoSolicitud;
	}

	public void setCodigoSolicitud(Integer codigoSolicitud) {
		this.codigoSolicitud = codigoSolicitud;
	}

	public String getDescipcion() {
		return this.descipcion;
	}

	public void setDescipcion(String descipcion) {
		this.descipcion = descipcion;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Date getFechaRecepcion() {
		return this.fechaRecepcion;
	}

	public void setFechaRecepcion(Date fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}

	public Date getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public List<Cotizacion> getListaCotizaciones() {
		return listaCotizaciones;
	}

	public void setListaCotizaciones(List<Cotizacion> listaCotizaciones) {
		this.listaCotizaciones = listaCotizaciones;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public UbicacionGeografica getCiudad() {
		return ciudad;
	}

	public void setCiudad(UbicacionGeografica ciudad) {
		this.ciudad = ciudad;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
