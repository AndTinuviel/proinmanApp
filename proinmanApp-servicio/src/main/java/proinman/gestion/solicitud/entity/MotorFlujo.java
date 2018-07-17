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
@Table(name="pss_motor_flujo", schema="proinman_movil")
@NamedQuery(name="MotorFlujo.findAll", query="SELECT m FROM MotorFlujo m")
public class MotorFlujo
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @Column(name="codigo_flujo")
  private Integer codigoFlujo;
  @Column(name="valor_parametro")
  private String valorParametro;
  @ManyToOne(fetch=FetchType.LAZY)
  @JoinColumn(name="codigo_actividad_origen")
  private MotorActividad pssMotorActividad1;
  @ManyToOne(fetch=FetchType.LAZY)
  @JoinColumn(name="codigo_actividad_destino")
  private MotorActividad pssMotorActividad2;
  
  public Integer getCodigoFlujo()
  {
    return this.codigoFlujo;
  }
  
  public void setCodigoFlujo(Integer codigoFlujo)
  {
    this.codigoFlujo = codigoFlujo;
  }
  
  public String getValorParametro()
  {
    return this.valorParametro;
  }
  
  public void setValorParametro(String valorParametro)
  {
    this.valorParametro = valorParametro;
  }
  
  public MotorActividad getPssMotorActividad1()
  {
    return this.pssMotorActividad1;
  }
  
  public void setPssMotorActividad1(MotorActividad pssMotorActividad1)
  {
    this.pssMotorActividad1 = pssMotorActividad1;
  }
  
  public MotorActividad getPssMotorActividad2()
  {
    return this.pssMotorActividad2;
  }
  
  public void setPssMotorActividad2(MotorActividad pssMotorActividad2)
  {
    this.pssMotorActividad2 = pssMotorActividad2;
  }
}
