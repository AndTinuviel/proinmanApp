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
@Table(name = "pss_motor_actividad", schema = "proinman_movil")
@NamedQuery(name = "MotorActividad.findAll", query = "SELECT m FROM MotorActividad m")
public class MotorActividad implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo_actividad")
	private Integer codigoActividad;
	
	@Column(name = "direccion_pagina")
	private String direccionPagina;
	
	private String estado;
	
	private String nombre;
	
	@Column(name = "numero_dias_por_vencer")
	private Integer numeroDiasPorVencer;
	
	@Column(name = "numero_dias_vencimiento")
	private Integer numeroDiasVencimiento;
	

	public Integer getCodigoActividad() {
		return this.codigoActividad;
	}

	public void setCodigoActividad(Integer codigoActividad) {
		this.codigoActividad = codigoActividad;
	}

	public String getDireccionPagina() {
		return this.direccionPagina;
	}

	public void setDireccionPagina(String direccionPagina) {
		this.direccionPagina = direccionPagina;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getNumeroDiasPorVencer() {
		return this.numeroDiasPorVencer;
	}

	public void setNumeroDiasPorVencer(Integer numeroDiasPorVencer) {
		this.numeroDiasPorVencer = numeroDiasPorVencer;
	}

	public Integer getNumeroDiasVencimiento() {
		return this.numeroDiasVencimiento;
	}

	public void setNumeroDiasVencimiento(Integer numeroDiasVencimiento) {
		this.numeroDiasVencimiento = numeroDiasVencimiento;
	}

}
