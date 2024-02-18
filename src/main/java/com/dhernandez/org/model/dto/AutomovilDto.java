package com.dhernandez.org.model.dto;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class AutomovilDto implements Serializable{
	
	private static final long serialVersionUID = 1L;


	private Long id;
	
	private String linea;
	private String modelo;
	private String caja;
	private String transmision;
	private String tipoConmbustible;
	private String cc;
	private Integer anio;
	private String color;
	private String kms;
	private String placa;
	private String ciudad;
	private BigDecimal precio;

	private LocalDateTime fechaRegistro;

}
