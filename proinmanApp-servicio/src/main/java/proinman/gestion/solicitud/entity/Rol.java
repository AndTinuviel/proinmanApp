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

import proinman.gestion.solicitud.utilitarios.EstadoEnum;

@Entity
@Table(name = "pss_rol", schema = "proinman_movil")
@NamedQuery(name = "Rol.findAll", query = "SELECT r FROM Rol r")
public class Rol implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "codigo_rol")
	private Integer codigoRol;

	@Column(name = "estado")
	private EstadoEnum estado;

	@Column(name = "nombre")
	private String nombre;

	@OneToMany(mappedBy = "rol", fetch = FetchType.EAGER)
	private List<UsuarioRol> listaUsuarioRol;

	public Integer getCodigoRol() {
		return this.codigoRol;
	}

	public void setCodigoRol(Integer codigoRol) {
		this.codigoRol = codigoRol;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<UsuarioRol> getListaUsuarioRol() {
		return listaUsuarioRol;
	}

	public void setListaUsuarioRol(List<UsuarioRol> listaUsuarioRol) {
		this.listaUsuarioRol = listaUsuarioRol;
	}

	public EstadoEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadoEnum estado) {
		this.estado = estado;
	}
}
