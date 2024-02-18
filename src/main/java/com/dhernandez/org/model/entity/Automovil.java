package com.dhernandez.org.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="automoviles")

public class Automovil implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "linea")
	private String linea;
	
	@Column(name = "modelo")
	private String modelo;

	@Column(name = "caja")
	private String caja;
	
	@Column(name = "transmision")
	private String transmision;
	
	@Column(name = "tipo_combustible")
	private String tipoConmbustible;
	
	@Column(name = "cc")
	private String cc;
	
	@Column(name = "anio")
	private Integer anio;
	
	@Column(name = "color")
	private String color;
	
	@Column(name = "kms")
	private String kms;
	
	@Column(name = "placa")
	private String placa;
	
	@Column(name = "ciudad")
	private String ciudad;
	
	@Column(name = "precio")
	private BigDecimal precio;
	
	@Column(name = "fecha_registro")
	private LocalDateTime fechaRegistro;
	
	@PrePersist()
	private void prePersist() {
		this.fechaRegistro = LocalDateTime.now();
	}
}
