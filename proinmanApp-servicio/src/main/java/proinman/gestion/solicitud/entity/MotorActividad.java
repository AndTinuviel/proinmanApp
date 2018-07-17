package proinman.gestion.solicitud.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="pss_motor_actividad", schema="proinman_movil")
@NamedQuery(name="MotorActividad.findAll", query="SELECT m FROM MotorActividad m")
public class MotorActividad
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @Column(name="codigo_actividad")
  private Integer codigoActividad;
  @Column(name="direccion_pagina")
  private String direccionPagina;
  private String estado;
  private String nombre;
  @Column(name="numero_dias_por_vencer")
  private Integer numeroDiasPorVencer;
  @Column(name="numero_dias_vencimiento")
  private Integer numeroDiasVencimiento;
  @OneToMany(mappedBy="pssMotorActividad1")
  private List<MotorFlujo> pssMotorFlujos1;
  @OneToMany(mappedBy="pssMotorActividad2")
  private List<MotorFlujo> pssMotorFlujos2;
  @OneToMany(mappedBy="pssMotorActividad")
  private List<MotorTarea> pssMotorTareas;
  
  public Integer getCodigoActividad()
  {
    return this.codigoActividad;
  }
  
  public void setCodigoActividad(Integer codigoActividad)
  {
    this.codigoActividad = codigoActividad;
  }
  
  public String getDireccionPagina()
  {
    return this.direccionPagina;
  }
  
  public void setDireccionPagina(String direccionPagina)
  {
    this.direccionPagina = direccionPagina;
  }
  
  public String getEstado()
  {
    return this.estado;
  }
  
  public void setEstado(String estado)
  {
    this.estado = estado;
  }
  
  public String getNombre()
  {
    return this.nombre;
  }
  
  public void setNombre(String nombre)
  {
    this.nombre = nombre;
  }
  
  public Integer getNumeroDiasPorVencer()
  {
    return this.numeroDiasPorVencer;
  }
  
  public void setNumeroDiasPorVencer(Integer numeroDiasPorVencer)
  {
    this.numeroDiasPorVencer = numeroDiasPorVencer;
  }
  
  public Integer getNumeroDiasVencimiento()
  {
    return this.numeroDiasVencimiento;
  }
  
  public void setNumeroDiasVencimiento(Integer numeroDiasVencimiento)
  {
    this.numeroDiasVencimiento = numeroDiasVencimiento;
  }
  
  public List<MotorFlujo> getPssMotorFlujos1()
  {
    return this.pssMotorFlujos1;
  }
  
  public void setPssMotorFlujos1(List<MotorFlujo> pssMotorFlujos1)
  {
    this.pssMotorFlujos1 = pssMotorFlujos1;
  }
  
  public MotorFlujo addPssMotorFlujos1(MotorFlujo pssMotorFlujos1)
  {
    getPssMotorFlujos1().add(pssMotorFlujos1);
    pssMotorFlujos1.setPssMotorActividad1(this);
    
    return pssMotorFlujos1;
  }
  
  public MotorFlujo removePssMotorFlujos1(MotorFlujo pssMotorFlujos1)
  {
    getPssMotorFlujos1().remove(pssMotorFlujos1);
    pssMotorFlujos1.setPssMotorActividad1(null);
    
    return pssMotorFlujos1;
  }
  
  public List<MotorFlujo> getPssMotorFlujos2()
  {
    return this.pssMotorFlujos2;
  }
  
  public void setPssMotorFlujos2(List<MotorFlujo> pssMotorFlujos2)
  {
    this.pssMotorFlujos2 = pssMotorFlujos2;
  }
  
  public MotorFlujo addPssMotorFlujos2(MotorFlujo pssMotorFlujos2)
  {
    getPssMotorFlujos2().add(pssMotorFlujos2);
    pssMotorFlujos2.setPssMotorActividad2(this);
    
    return pssMotorFlujos2;
  }
  
  public MotorFlujo removePssMotorFlujos2(MotorFlujo pssMotorFlujos2)
  {
    getPssMotorFlujos2().remove(pssMotorFlujos2);
    pssMotorFlujos2.setPssMotorActividad2(null);
    
    return pssMotorFlujos2;
  }
  
  public List<MotorTarea> getPssMotorTareas()
  {
    return this.pssMotorTareas;
  }
  
  public void setPssMotorTareas(List<MotorTarea> pssMotorTareas)
  {
    this.pssMotorTareas = pssMotorTareas;
  }
  
  public MotorTarea addPssMotorTarea(MotorTarea pssMotorTarea)
  {
    getPssMotorTareas().add(pssMotorTarea);
    pssMotorTarea.setPssMotorActividad(this);
    
    return pssMotorTarea;
  }
  
  public MotorTarea removePssMotorTarea(MotorTarea pssMotorTarea)
  {
    getPssMotorTareas().remove(pssMotorTarea);
    pssMotorTarea.setPssMotorActividad(null);
    
    return pssMotorTarea;
  }
}
