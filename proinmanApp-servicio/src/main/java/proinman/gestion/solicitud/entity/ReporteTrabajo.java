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
@Table(name="pss_reporte_trabajo", schema="proinman_movil")
@NamedQuery(name="ReporteTrabajo.findAll", query="SELECT r FROM ReporteTrabajo r")
public class ReporteTrabajo
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @Column(name="codigo_reporte_trabajo")
  private Integer codigoReporteTrabajo;
  @Column(name="calificacion_trabajo")
  private String calificacionTrabajo;
  private String comentario;
  private String correo;
  @Temporal(TemporalType.DATE)
  @Column(name="fecha_hora_desde")
  private Date fechaHoraDesde;
  @Temporal(TemporalType.DATE)
  @Column(name="fecha_hora_hasta")
  private Date fechaHoraHasta;
  private byte[] firma;
  private String identificacion;
  @Column(name="nombre_recibe_trabajo")
  private String nombreRecibeTrabajo;
  private String observacion;
  @OneToMany(mappedBy="pssReporteTrabajo")
  private List<ReporteFotografia> pssReporteFotografias;
  @ManyToOne(fetch=FetchType.LAZY)
  @JoinColumn(name="codigo_solicitud")
  private Solicitud pssSolicitud;
  
  public Integer getCodigoReporteTrabajo()
  {
    return this.codigoReporteTrabajo;
  }
  
  public void setCodigoReporteTrabajo(Integer codigoReporteTrabajo)
  {
    this.codigoReporteTrabajo = codigoReporteTrabajo;
  }
  
  public String getCalificacionTrabajo()
  {
    return this.calificacionTrabajo;
  }
  
  public void setCalificacionTrabajo(String calificacionTrabajo)
  {
    this.calificacionTrabajo = calificacionTrabajo;
  }
  
  public String getComentario()
  {
    return this.comentario;
  }
  
  public void setComentario(String comentario)
  {
    this.comentario = comentario;
  }
  
  public String getCorreo()
  {
    return this.correo;
  }
  
  public void setCorreo(String correo)
  {
    this.correo = correo;
  }
  
  public Date getFechaHoraDesde()
  {
    return this.fechaHoraDesde;
  }
  
  public void setFechaHoraDesde(Date fechaHoraDesde)
  {
    this.fechaHoraDesde = fechaHoraDesde;
  }
  
  public Date getFechaHoraHasta()
  {
    return this.fechaHoraHasta;
  }
  
  public void setFechaHoraHasta(Date fechaHoraHasta)
  {
    this.fechaHoraHasta = fechaHoraHasta;
  }
  
  public byte[] getFirma()
  {
    return this.firma;
  }
  
  public void setFirma(byte[] firma)
  {
    this.firma = firma;
  }
  
  public String getIdentificacion()
  {
    return this.identificacion;
  }
  
  public void setIdentificacion(String identificacion)
  {
    this.identificacion = identificacion;
  }
  
  public String getNombreRecibeTrabajo()
  {
    return this.nombreRecibeTrabajo;
  }
  
  public void setNombreRecibeTrabajo(String nombreRecibeTrabajo)
  {
    this.nombreRecibeTrabajo = nombreRecibeTrabajo;
  }
  
  public String getObservacion()
  {
    return this.observacion;
  }
  
  public void setObservacion(String observacion)
  {
    this.observacion = observacion;
  }
  
  public List<ReporteFotografia> getPssReporteFotografias()
  {
    return this.pssReporteFotografias;
  }
  
  public void setPssReporteFotografias(List<ReporteFotografia> pssReporteFotografias)
  {
    this.pssReporteFotografias = pssReporteFotografias;
  }
  
  public ReporteFotografia addPssReporteFotografia(ReporteFotografia pssReporteFotografia)
  {
    getPssReporteFotografias().add(pssReporteFotografia);
    pssReporteFotografia.setPssReporteTrabajo(this);
    
    return pssReporteFotografia;
  }
  
  public ReporteFotografia removePssReporteFotografia(ReporteFotografia pssReporteFotografia)
  {
    getPssReporteFotografias().remove(pssReporteFotografia);
    pssReporteFotografia.setPssReporteTrabajo(null);
    
    return pssReporteFotografia;
  }
  
  public Solicitud getPssSolicitud()
  {
    return this.pssSolicitud;
  }
  
  public void setPssSolicitud(Solicitud pssSolicitud)
  {
    this.pssSolicitud = pssSolicitud;
  }
}
