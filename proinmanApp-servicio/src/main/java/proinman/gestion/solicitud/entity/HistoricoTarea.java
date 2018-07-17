package proinman.gestion.solicitud.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="pss_historico_tarea", schema="proinman_movil")
@NamedQuery(name="HistoricoTarea.findAll", query="SELECT h FROM HistoricoTarea h")
public class HistoricoTarea
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @Column(name="codigo_historico_tarea")
  private Integer codigoHistoricoTarea;
  
  public Integer getCodigoHistoricoTarea()
  {
    return this.codigoHistoricoTarea;
  }
  
  public void setCodigoHistoricoTarea(Integer codigoHistoricoTarea)
  {
    this.codigoHistoricoTarea = codigoHistoricoTarea;
  }
}
