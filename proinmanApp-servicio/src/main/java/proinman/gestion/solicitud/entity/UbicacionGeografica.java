package proinman.gestion.solicitud.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="pss_ubicacion_geografica", schema="proinman_movil")
@NamedQuery(name="UbicacionGeografica.findAll", query="SELECT u FROM UbicacionGeografica u")
public class UbicacionGeografica
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @Column(name="codigo_ubicacion_geografica")
  private Integer codigoUbicacionGeografica;
  @Column(name="descripcion")
  private String descripcion;
  @Column(name="estado")
  private String estado;
  @Column(name="nivel")
  private Integer nivel;
  @ManyToOne(optional=true)
  @JoinColumn(name="codigo_padre", referencedColumnName="codigo_ubicacion_geografica")
  private UbicacionGeografica ubicacionGeograficaPadre;
  
  public Integer getCodigoUbicacionGeografica()
  {
    return this.codigoUbicacionGeografica;
  }
  
  public void setCodigoUbicacionGeografica(Integer codigoUbicacionGeografica)
  {
    this.codigoUbicacionGeografica = codigoUbicacionGeografica;
  }
  
  public String getDescripcion()
  {
    return this.descripcion;
  }
  
  public void setDescripcion(String descripcion)
  {
    this.descripcion = descripcion;
  }
  
  public String getEstado()
  {
    return this.estado;
  }
  
  public UbicacionGeografica getUbicacionGeograficaPadre()
  {
    return this.ubicacionGeograficaPadre;
  }
  
  public void setUbicacionGeograficaPadre(UbicacionGeografica ubicacionGeograficaPadre)
  {
    this.ubicacionGeograficaPadre = ubicacionGeograficaPadre;
  }
  
  public void setEstado(String estado)
  {
    this.estado = estado;
  }
  
  public Integer getNivel()
  {
    return this.nivel;
  }
  
  public void setNivel(Integer nivel)
  {
    this.nivel = nivel;
  }
}
