package proinman.gestion.solicitud.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pss_cliente", schema = "proinman_movil")
@NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo_cliente")
	private Integer codigoCliente;
	
	@Column(name = "celular")
	private String celular;
	
	@Column(name = "ciudad")
	private String ciudad;
	
	@Column(name = "giro_negocio")
	private String giroNegocio;
	
	@Column(name = "identificacion")
	private String identificacion;
	
	@Column(name = "mail")
	private String mail;
	
	@Column(name = "nombre_razon_social")
	private String nombreRazonSocial;
	
	@Column(name = "telefono")
	private String telefono;
	
	@OneToMany(mappedBy = "pssCliente",fetch=FetchType.EAGER)
	private List<ClienteSecuencial> listaClienteSecuencial;
	

	public Integer getCodigoCliente() {
		return this.codigoCliente;
	}

	public void setCodigoCliente(Integer codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getCelular() {
		return this.celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getGiroNegocio() {
		return this.giroNegocio;
	}

	public void setGiroNegocio(String giroNegocio) {
		this.giroNegocio = giroNegocio;
	}

	public String getIdentificacion() {
		return this.identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getNombreRazonSocial() {
		return this.nombreRazonSocial;
	}

	public void setNombreRazonSocial(String nombreRazonSocial) {
		this.nombreRazonSocial = nombreRazonSocial;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<ClienteSecuencial> getListaClienteSecuencial() {
		return listaClienteSecuencial;
	}

	public void setListaClienteSecuencial(List<ClienteSecuencial> listaClienteSecuencial) {
		this.listaClienteSecuencial = listaClienteSecuencial;
	}

}
