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
	
	@OneToMany(mappedBy = "pssCotizacion")
	private List<CotizacionItem> pssCotizacionItems;
	
	@OneToMany(mappedBy = "pssCotizacion")
	private List<SecuencialSolicitud> pssSecuencialSolicituds;

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

	public List<CotizacionItem> getPssCotizacionItems() {
		return this.pssCotizacionItems;
	}

	public void setPssCotizacionItems(List<CotizacionItem> pssCotizacionItems) {
		this.pssCotizacionItems = pssCotizacionItems;
	}

	public CotizacionItem addPssCotizacionItem(CotizacionItem pssCotizacionItem) {
		getPssCotizacionItems().add(pssCotizacionItem);
		pssCotizacionItem.setPssCotizacion(this);

		return pssCotizacionItem;
	}

	public CotizacionItem removePssCotizacionItem(CotizacionItem pssCotizacionItem) {
		getPssCotizacionItems().remove(pssCotizacionItem);
		pssCotizacionItem.setPssCotizacion(null);

		return pssCotizacionItem;
	}

	public List<SecuencialSolicitud> getPssSecuencialSolicituds() {
		return this.pssSecuencialSolicituds;
	}

	public void setPssSecuencialSolicituds(List<SecuencialSolicitud> pssSecuencialSolicituds) {
		this.pssSecuencialSolicituds = pssSecuencialSolicituds;
	}

	public SecuencialSolicitud addPssSecuencialSolicitud(SecuencialSolicitud pssSecuencialSolicitud) {
		getPssSecuencialSolicituds().add(pssSecuencialSolicitud);
		pssSecuencialSolicitud.setPssCotizacion(this);

		return pssSecuencialSolicitud;
	}

	public SecuencialSolicitud removePssSecuencialSolicitud(SecuencialSolicitud pssSecuencialSolicitud) {
		getPssSecuencialSolicituds().remove(pssSecuencialSolicitud);
		pssSecuencialSolicitud.setPssCotizacion(null);

		return pssSecuencialSolicitud;
	}
}
