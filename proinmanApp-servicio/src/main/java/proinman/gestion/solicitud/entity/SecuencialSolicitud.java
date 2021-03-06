package proinman.gestion.solicitud.entity;

import java.io.Serializable;
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
@Table(name = "pss_secuencial_solicitud", schema = "proinman_movil")
@NamedQuery(name = "SecuencialSolicitud.findAll", query = "SELECT s FROM SecuencialSolicitud s")
public class SecuencialSolicitud implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo_secuencial_solicitud")
	private Integer codigoSecuencialSolicitud;
	
	private String valor;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_secuencial")
	private ClienteSecuencial clienteSecuencial;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_cotizacion")
	private Cotizacion cotizacion;

	public Integer getCodigoSecuencialSolicitud() {
		return this.codigoSecuencialSolicitud;
	}

	public void setCodigoSecuencialSolicitud(Integer codigoSecuencialSolicitud) {
		this.codigoSecuencialSolicitud = codigoSecuencialSolicitud;
	}

	public String getValor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public ClienteSecuencial getClienteSecuencial() {
		return clienteSecuencial;
	}

	public void setClienteSecuencial(ClienteSecuencial clienteSecuencial) {
		this.clienteSecuencial = clienteSecuencial;
	}

	public Cotizacion getCotizacion() {
		return cotizacion;
	}

	public void setCotizacion(Cotizacion cotizacion) {
		this.cotizacion = cotizacion;
	}
}
