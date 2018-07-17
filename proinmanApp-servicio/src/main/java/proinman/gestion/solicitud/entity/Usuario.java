package proinman.gestion.solicitud.entity;

import java.io.Serializable;
import java.util.ArrayList;
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

import proinman.gestion.solicitud.utilitarios.Estado;

@Entity
@Table(name = "pss_usuario", schema = "proinman_movil")
@NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "codigo_usuario")
	private Integer codigoUsuario;
	
	@Column(name = "apellido")
	private String apellido;
	
	@Column(name = "cedula")
	private String cedula;
	
	@Column(name = "estado")
	private Estado estado;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "username")
	private String username;
	
	@OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
	private List<UsuarioRol> listaUsuarioRol;

	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
		listaUsuarioRol = new ArrayList<>();
	}

	public Integer getCodigoUsuario() {
		return this.codigoUsuario;
	}

	public void setCodigoUsuario(Integer codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCedula() {
		return this.cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<UsuarioRol> getListaUsuarioRol() {
		return listaUsuarioRol;
	}

	public void setListaUsuarioRol(List<UsuarioRol> listaUsuarioRol) {
		this.listaUsuarioRol = listaUsuarioRol;
	}
}
