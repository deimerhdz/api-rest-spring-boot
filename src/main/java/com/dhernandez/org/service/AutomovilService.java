package com.dhernandez.org.service;

import java.util.List;

import com.dhernandez.org.model.dto.AutomovilDto;
import com.dhernandez.org.model.entity.Automovil;


public interface AutomovilService {
	
	boolean existById(Long id);
	
	List<Automovil> listAll();
	
	Automovil save(AutomovilDto automovil);
	
	Automovil findById(Long id);
	
	void delete(Automovil automovil);

}
