package proinman.gestion.solicitud.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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

@Entity
@Table(name = "pss_cotizacion", schema = "proinman_movil")
@NamedQuery(name = "Cotizacion.findAll", query = "SELECT c FROM Cotizacion c")
public class Cotizacion implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo_cotizacion")
	private Integer codigoCotizacion;
	
	@Column(name = "costo_total")
	private BigDecimal costoTotal;
	
	@Column(name = "precio_total")
	private BigDecimal precioTotal;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_solicitud")
	private Solicitud pssSolicitud;
	
	@OneToMany(mappedBy = "cotizacion")
	private List<CotizacionItem> listaCotizacionItems;
	
	@OneToMany(mappedBy = "cotizacion")
	private List<SecuencialSolicitud> listaSecuencialSolicituds;

	public Integer getCodigoCotizacion() {
		return this.codigoCotizacion;
	}

	public void setCodigoCotizacion(Integer codigoCotizacion) {
		this.codigoCotizacion = codigoCotizacion;
	}

	public BigDecimal getCostoTotal() {
		return this.costoTotal;
	}

	public void setCostoTotal(BigDecimal costoTotal) {
		this.costoTotal = costoTotal;
	}

	public BigDecimal getPrecioTotal() {
		return this.precioTotal;
	}

	public void setPrecioTotal(BigDecimal precioTotal) {
		this.precioTotal = precioTotal;
	}

	public Solicitud getPssSolicitud() {
		return this.pssSolicitud;
	}

	public void setPssSolicitud(Solicitud pssSolicitud) {
		this.pssSolicitud = pssSolicitud;
	}

	public List<CotizacionItem> getListaCotizacionItems() {
		return listaCotizacionItems;
	}

	public void setListaCotizacionItems(List<CotizacionItem> listaCotizacionItems) {
		this.listaCotizacionItems = listaCotizacionItems;
	}

	public List<SecuencialSolicitud> getListaSecuencialSolicituds() {
		return listaSecuencialSolicituds;
	}

	public void setListaSecuencialSolicituds(List<SecuencialSolicitud> listaSecuencialSolicituds) {
		this.listaSecuencialSolicituds = listaSecuencialSolicituds;
	}

}
