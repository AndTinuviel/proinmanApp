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
@Table(name="pss_solicitud", schema="proinman_movil")
@NamedQuery(name="Solicitud.findAll", query="SELECT s FROM Solicitud s")
public class Solicitud
  implements Serializable
{
  private static final long serialVersionUID = -4963674792768308809L;
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @Column(name="codigo_solicitud")
  private Integer codigoSolicitud;
  private String descipcion;
  private String direccion;
  @Temporal(TemporalType.DATE)
  @Column(name="fecha_recepcion")
  private Date fechaRecepcion;
  @Temporal(TemporalType.DATE)
  @Column(name="fecha_registro")
  private Date fechaRegistro;
  @OneToMany(mappedBy="pssSolicitud")
  private List<Cotizacion> pssCotizacions;
  @ManyToOne(fetch=FetchType.LAZY)
  @JoinColumn(name="codigo_cliente")
  private Cliente pssCliente;
  @ManyToOne(fetch=FetchType.LAZY)
  @JoinColumn(name="codigo_ubicacion_geografica")
  private UbicacionGeografica pssUbicacionGeografica;
  @ManyToOne(fetch=FetchType.LAZY)
  @JoinColumn(name="codigo_usuario")
  private Usuario pssUsuario;
  
  public Integer getCodigoSolicitud()
  {
    return this.codigoSolicitud;
  }
  
  public void setCodigoSolicitud(Integer codigoSolicitud)
  {
    this.codigoSolicitud = codigoSolicitud;
  }
  
  public String getDescipcion()
  {
    return this.descipcion;
  }
  
  public void setDescipcion(String descipcion)
  {
    this.descipcion = descipcion;
  }
  
  public String getDireccion()
  {
    return this.direccion;
  }
  
  public void setDireccion(String direccion)
  {
    this.direccion = direccion;
  }
  
  public Date getFechaRecepcion()
  {
    return this.fechaRecepcion;
  }
  
  public void setFechaRecepcion(Date fechaRecepcion)
  {
    this.fechaRecepcion = fechaRecepcion;
  }
  
  public Date getFechaRegistro()
  {
    return this.fechaRegistro;
  }
  
  public void setFechaRegistro(Date fechaRegistro)
  {
    this.fechaRegistro = fechaRegistro;
  }
  
  public List<Cotizacion> getPssCotizacions()
  {
    return this.pssCotizacions;
  }
  
  public void setPssCotizacions(List<Cotizacion> pssCotizacions)
  {
    this.pssCotizacions = pssCotizacions;
  }
  
  public Cotizacion addPssCotizacion(Cotizacion pssCotizacion)
  {
    getPssCotizacions().add(pssCotizacion);
    pssCotizacion.setPssSolicitud(this);
    
    return pssCotizacion;
  }
  
  public Cotizacion removePssCotizacion(Cotizacion pssCotizacion)
  {
    getPssCotizacions().remove(pssCotizacion);
    pssCotizacion.setPssSolicitud(null);
    
    return pssCotizacion;
  }
  
  public Cliente getPssCliente()
  {
    return this.pssCliente;
  }
  
  public void setPssCliente(Cliente pssCliente)
  {
    this.pssCliente = pssCliente;
  }
  
  public UbicacionGeografica getPssUbicacionGeografica()
  {
    return this.pssUbicacionGeografica;
  }
  
  public void setPssUbicacionGeografica(UbicacionGeografica pssUbicacionGeografica)
  {
    this.pssUbicacionGeografica = pssUbicacionGeografica;
  }
  
  public Usuario getPssUsuario()
  {
    return this.pssUsuario;
  }
  
  public void setPssUsuario(Usuario pssUsuario)
  {
    this.pssUsuario = pssUsuario;
  }
}
