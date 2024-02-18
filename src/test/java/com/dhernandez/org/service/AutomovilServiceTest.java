package com.dhernandez.org.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import com.dhernandez.org.model.dao.AutomovilDao;
import com.dhernandez.org.model.dto.AutomovilDto;
import com.dhernandez.org.model.entity.Automovil;

@ExtendWith(MockitoExtension.class)
public class AutomovilServiceTest {
	
	@Mock
	private AutomovilDao automovilDao;
	
	@InjectMocks
	private AutomovilServiceImpl automovilService;
	
	private Automovil automovil;
	
	private AutomovilDto automovilDto;
	
	@BeforeEach
	void setup() {
		automovil =  Automovil.builder()
				.id(1L)
				.linea("Chevrolet")
				.placa("djfk34")
				.caja("AUT")
				.tipoConmbustible("GSL")
				.ciudad("Monteria")
				.cc("13000cc")
				.kms("220000")
				.color("BLANCO")
				.anio(2019)
				.modelo("Tracker Lt")
				.transmision("4X2")
				.precio(new BigDecimal(49000000))
				.build();
		
		automovilDto =  AutomovilDto.builder()
				.id(1L)
				.linea("Chevrolet")
				.placa("djfk34")
				.caja("AUT")
				.tipoConmbustible("GSL")
				.transmision("4X2")
				.ciudad("Monteria")
				.cc("13000cc")
				.kms("220000")
				.color("BLANCO")
				.anio(2019)
				.fechaRegistro(LocalDateTime.now())
				.modelo("Tracker Lt")
				.precio(new BigDecimal(49000000))
				.build();
	}
	
	@DisplayName("Test para guardar automovil")
	@Test 
	void saveAutomovil() {
		//given
		
		given(automovilDao.save(automovil)).willReturn(automovil);
		//when 
		
		Automovil automovilSave = automovilService.save(automovilDto);
		//then
		assertThat(automovilSave).isNotNull();
	}
	
	@DisplayName("Test para listar automovil")
	@Test
	void listAll() {
		
		//given
		Automovil automovil2 =  Automovil.builder()
				.id(1L)
				.linea("Chevrolet")
				.placa("djfk34")
				.caja("AUT")
				.tipoConmbustible("GSL")
				.ciudad("Monteria")
				.cc("13000cc")
				.kms("220000")
				.color("BLANCO")
				.anio(2019)
				.modelo("Tracker Lt")
				.transmision("4X2")
				.precio(new BigDecimal(49000000))
				.build();
		given(automovilDao.findAll()).willReturn(Arrays.asList(automovil,automovil2));
		//when
		List<Automovil > automoviles = automovilService.listAll();
		
		//then
		assertThat(automoviles).isNotEmpty();
		assertThat(automoviles.size()).isEqualTo(2);
	}
	
	@DisplayName("Test para retornar una lista vacia")
	@Test
	void testListEmpty() {
		
		//given
		
		given(automovilDao.findAll()).willReturn(Collections.emptyList());
		//when
		List<Automovil > automoviles = automovilService.listAll();
		
		//then
		assertThat(automoviles).isEmpty();
		assertThat(automoviles.size()).isEqualTo(0);
	}
	
	
	@DisplayName("Test obtener automovil por id")
	@Test
	void testObtenerAutomovilById() {
		//given
		given(automovilDao.findById(1L)).willReturn(Optional.of(automovil));
		//when
		Automovil automovilSave = automovilService.findById(automovil.getId());
		//then
		assertThat(automovilSave).isNotNull();
	}
	
	@DisplayName("Test para actualizar un automovil por id")
	@Test
	void testActualizarAutomovil() {
		//given
		given(automovilDao.save(automovil)).willReturn(automovil);
		automovil.setCaja("kf");
		automovil.setCiudad("Lorica");
		//when
		Automovil automovilUpdate = automovilService.save(automovilDto);
		//then
		assertThat(automovilUpdate).isEqualTo("kf");
		assertThat(automovilUpdate).isEqualTo("Lorica");
	}
	
	@DisplayName("Test para eliminar un automovil por id")
	@Test
	void testEliminarAutomovil() {
		//given
		willDoNothing().given(automovilDao).delete(automovil);
		
		//when
		automovilService.delete(automovil);
		
		verify(automovilDao,times(1)).delete(automovil);
	
	}
}
