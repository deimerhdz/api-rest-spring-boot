package com.dhernandez.org.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dhernandez.org.model.dto.AutomovilDto;
import com.dhernandez.org.model.entity.Automovil;
import com.dhernandez.org.payload.MensajeResponse;
import com.dhernandez.org.service.AutomovilService;

@RestController
@RequestMapping("/api/v1")
public class AutomovilControlller {
	
	@Autowired
	private AutomovilService automovilService;
	
	@GetMapping("/automoviles")

	public ResponseEntity<?> findAll(){
	  List<Automovil> getList = 	automovilService.listAll();
	  
	  if(getList.isEmpty()) {
		return  new ResponseEntity<>(MensajeResponse.builder()
					 .mensaje("No hay registros")
					 .object(null)
					 .build()
			    ,HttpStatus.OK);
	  }
	  return  new ResponseEntity<>(MensajeResponse.builder()
				 .mensaje("")
				 .object(getList)
				 .build()
		    ,HttpStatus.OK);
	}
	
	@GetMapping("/automovil/{id}")
	public ResponseEntity<?> findById(@PathVariable("id")Long id) {
		Automovil automovil = automovilService.findById(id);
		if(automovil ==null) {
			 return new ResponseEntity<>(MensajeResponse.builder()
					 .mensaje("El registro que intenta buscar no existe!!")
					 .object(null)
					 .build()
			    ,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(MensajeResponse.builder()
				.mensaje("")
				.object(AutomovilDto.builder()
						.id(automovil.getId())
						.linea(automovil.getLinea())
						.placa(automovil.getPlaca())
						.caja(automovil.getCaja())
						.tipoConmbustible(automovil.getTipoConmbustible())
						.ciudad(automovil.getCiudad())
						.cc(automovil.getCc())
						.transmision(automovil.getTransmision())
						.kms(automovil.getKms())
						.color(automovil.getColor())
						.anio(automovil.getAnio())
						.fechaRegistro(automovil.getFechaRegistro())
						.modelo(automovil.getModelo())
						.precio(automovil.getPrecio())
						.build()),HttpStatus.OK);
	}
	
	
	@PostMapping(path="/automovil",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> save(@RequestBody AutomovilDto automovilDto) {
		Automovil automovil = null;
		try {
			
			automovil = automovilService.save(automovilDto);
			
			return new ResponseEntity<>(MensajeResponse.builder()
					.mensaje("Guardado Correctamente")
					.object(AutomovilDto.builder()
							.id(automovil.getId())
							.linea(automovil.getLinea())
							.placa(automovil.getPlaca())
							.caja(automovil.getCaja())
							.tipoConmbustible(automovil.getTipoConmbustible())
							.ciudad(automovil.getCiudad())
							.cc(automovil.getCc())
							.kms(automovil.getKms())
							.color(automovil.getColor())
							.anio(automovil.getAnio())
							.transmision(automovil.getTransmision())
							.fechaRegistro(automovil.getFechaRegistro())
							.modelo(automovil.getModelo())
							.precio(automovil.getPrecio())
							.build()),HttpStatus.CREATED);
			
		} catch (DataAccessException ex) {
			 return new ResponseEntity<>(MensajeResponse.builder()
					 .mensaje(ex.getMessage())
					 .object(null)
					 .build()
			    ,HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	
	@PutMapping(path="/automovil/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody AutomovilDto automovilDto, @PathVariable Long id) {
		Automovil automovilUpdate =null;
		
		try {
			
			if(automovilService.existById(id)) {
				automovilDto.setId(id);
				automovilUpdate = automovilService.save(automovilDto);				
				return new ResponseEntity<>(MensajeResponse.builder()
						.mensaje("Guardado Correctamente")
						.object(AutomovilDto.builder()
								.id(automovilUpdate.getId())
								.linea(automovilUpdate.getLinea())
								.placa(automovilUpdate.getPlaca())
								.caja(automovilUpdate.getCaja())
								.tipoConmbustible(automovilUpdate.getTipoConmbustible())
								.ciudad(automovilUpdate.getCiudad())
								.cc(automovilUpdate.getCc())
								.kms(automovilUpdate.getKms())
								.color(automovilUpdate.getColor())
								.anio(automovilUpdate.getAnio())
								.transmision(automovilUpdate.getTransmision())
								.fechaRegistro(automovilUpdate.getFechaRegistro())
								.modelo(automovilUpdate.getModelo())
								.precio(automovilUpdate.getPrecio())
								.build()),HttpStatus.CREATED);
			}else {
				 return new ResponseEntity<>(MensajeResponse.builder()
						 .mensaje("El registro que intenta actualizar no se encuentra en la base de datos")
						 .object(null)
						 .build()
				    ,HttpStatus.NOT_FOUND);
			}
			
			
		} catch (DataAccessException ex) {
			 return new ResponseEntity<>(MensajeResponse.builder()
					 .mensaje(ex.getMessage())
					 .object(null)
					 .build()
			    ,HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@DeleteMapping("/automovil/{id}")
	public ResponseEntity<?> delete(@PathVariable("id")Long id) {
		 try {
			 Automovil automovilDelete = automovilService.findById(id);
			 automovilService.delete(automovilDelete);
			 return new ResponseEntity<>(automovilDelete,HttpStatus.NO_CONTENT);
		 }catch(DataAccessException ex) {
			 return new ResponseEntity<>(MensajeResponse.builder()
							 .mensaje(ex.getMessage())
							 .object(null)
							 .build()
					    ,HttpStatus.INTERNAL_SERVER_ERROR);
		 }
	}
	

}
