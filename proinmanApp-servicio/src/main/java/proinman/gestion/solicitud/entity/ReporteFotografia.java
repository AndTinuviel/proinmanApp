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
@Table(name = "pss_reporte_fotografia", schema = "proinman_movil")
@NamedQuery(name = "ReporteFotografia.findAll", query = "SELECT r FROM ReporteFotografia r")
public class ReporteFotografia implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo_reporete_fotografia")
	private Integer codigoReporeteFotografia;
	
	private byte[] fotografia;
	
	private String nombre;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_reporte_trabajo")
	private ReporteTrabajo reporteTrabajo;

	public Integer getCodigoReporeteFotografia() {
		return this.codigoReporeteFotografia;
	}

	public void setCodigoReporeteFotografia(Integer codigoReporeteFotografia) {
		this.codigoReporeteFotografia = codigoReporeteFotografia;
	}

	public byte[] getFotografia() {
		return this.fotografia;
	}

	public void setFotografia(byte[] fotografia) {
		this.fotografia = fotografia;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ReporteTrabajo getReporteTrabajo() {
		return reporteTrabajo;
	}

	public void setReporteTrabajo(ReporteTrabajo reporteTrabajo) {
		this.reporteTrabajo = reporteTrabajo;
	}

}
