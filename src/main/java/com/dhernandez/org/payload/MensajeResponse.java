package com.dhernandez.org.payload;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class MensajeResponse  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private String mensaje;
	private Object object;
}
