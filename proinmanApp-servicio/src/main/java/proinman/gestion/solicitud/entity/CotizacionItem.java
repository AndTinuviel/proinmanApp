package proinman.gestion.solicitud.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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

@Entity
@Table(name = "pss_cotizacion_item", schema = "proinman_movil")
@NamedQuery(name = "CotizacionItem.findAll", query = "SELECT c FROM CotizacionItem c")
public class CotizacionItem implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo_item")
	private Integer codigoItem;
	
	@Column(name = "cantidad")
	private BigDecimal cantidad;
	
	@Column(name = "costo")
	private BigDecimal costo;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "codigo_catalogo_item")
	private CatalogoItem catalogoItem;
	
	@Column(name = "precio")
	private BigDecimal precio;
	
	@Column(name = "total_costo_item")
	private BigDecimal totalCostoItem;
	
	@Column(name = "total_precio_item")
	private BigDecimal totalPrecioItem;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "codigo_cotizacion")
	private Cotizacion cotizacion;
	
	public Integer getCodigoItem() {
		return this.codigoItem;
	}

	public void setCodigoItem(Integer codigoItem) {
		this.codigoItem = codigoItem;
	}

	public BigDecimal getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getCosto() {
		return this.costo;
	}

	public void setCosto(BigDecimal costo) {
		this.costo = costo;
	}

	public BigDecimal getPrecio() {
		return this.precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public BigDecimal getTotalCostoItem() {
		return this.totalCostoItem;
	}

	public void setTotalCostoItem(BigDecimal totalCostoItem) {
		this.totalCostoItem = totalCostoItem;
	}

	public BigDecimal getTotalPrecioItem() {
		return this.totalPrecioItem;
	}

	public void setTotalPrecioItem(BigDecimal totalPrecioItem) {
		this.totalPrecioItem = totalPrecioItem;
	}

	public Cotizacion getCotizacion() {
		return cotizacion;
	}

	public void setCotizacion(Cotizacion cotizacion) {
		this.cotizacion = cotizacion;
	}

	public CatalogoItem getCatalogoItem() {
		return catalogoItem;
	}

	public void setCatalogoItem(CatalogoItem catalogoItem) {
		this.catalogoItem = catalogoItem;
	}
}
