package proinman.gestion.solicitud.entity;

import java.io.Serializable;
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
@Table(name = "pss_cliente_secuencial", schema = "proinman_movil")
@NamedQuery(name = "ClienteSecuencial.findAll", query = "SELECT c FROM ClienteSecuencial c")
public class ClienteSecuencial implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo_secuencial")
	private Integer codigoSecuencial;
	
	private String descripcion;
	
	private String secuencial;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_cliente")
	private Cliente pssCliente;
	
	@OneToMany(mappedBy = "clienteSecuencial")
	private List<SecuencialSolicitud> listaSecuencialSolicitud;

	public Integer getCodigoSecuencial() {
		return this.codigoSecuencial;
	}

	public void setCodigoSecuencial(Integer codigoSecuencial) {
		this.codigoSecuencial = codigoSecuencial;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getSecuencial() {
		return this.secuencial;
	}

	public void setSecuencial(String secuencial) {
		this.secuencial = secuencial;
	}

	public Cliente getPssCliente() {
		return this.pssCliente;
	}

	public void setPssCliente(Cliente pssCliente) {
		this.pssCliente = pssCliente;
	}

	public List<SecuencialSolicitud> getListaSecuencialSolicitud() {
		return listaSecuencialSolicitud;
	}

	public void setListaSecuencialSolicitud(List<SecuencialSolicitud> listaSecuencialSolicitud) {
		this.listaSecuencialSolicitud = listaSecuencialSolicitud;
	}

}
