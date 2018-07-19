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
@Table(name = "pss_usuario_rol", schema="proinman_movil")
@NamedQuery(name = "UsuarioRol.findAll", query = "SELECT u FROM UsuarioRol u")
public class UsuarioRol implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "codigo_usuario_rol")
	private Integer codigoUsuarioRol;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "codigo_rol")
	private Rol rol;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "codigo_usuario")
	private Usuario usuario;
	
	private String estado;

	public Rol getRol() {
		return this.rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Integer getCodigoUsuarioRol() {
		return this.codigoUsuarioRol;
	}

	public void setCodigoUsuarioRol(Integer codigoUsuarioRol) {
		this.codigoUsuarioRol = codigoUsuarioRol;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
