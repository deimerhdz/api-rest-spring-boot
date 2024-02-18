package com.dhernandez.org.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dhernandez.org.model.dao.AutomovilDao;
import com.dhernandez.org.model.dto.AutomovilDto;
import com.dhernandez.org.model.entity.Automovil;
@Service
public class AutomovilServiceImpl implements AutomovilService{

	@Autowired
	AutomovilDao automovilDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Automovil> listAll() {
		return  (List<Automovil>) automovilDao.findAll();
	}

	@Transactional
	@Override
	public Automovil save(AutomovilDto automovilDto) {
		Automovil automovil = Automovil.builder()
				.id(automovilDto.getId())
				.color(automovilDto.getColor())
				.anio(automovilDto.getAnio())
				.ciudad(automovilDto.getCiudad())
				.cc(automovilDto.getCc())
				.kms(automovilDto.getKms())
				.modelo(automovilDto.getModelo())
				.caja(automovilDto.getCaja())
				.linea(automovilDto.getLinea())
				.placa(automovilDto.getPlaca())
				.precio(automovilDto.getPrecio())
				.transmision(automovilDto.getTransmision())
				.tipoConmbustible(automovilDto.getTipoConmbustible())
				.build();
		return automovilDao.save(automovil);
	}

	@Override
	@Transactional(readOnly = true)
	public Automovil findById(Long id) {
		
		return automovilDao.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public void delete(Automovil automovil) {
		this.automovilDao.delete(automovil);
		
	}

	@Override
	public boolean existById(Long id) {
		return this.automovilDao.existsById(id);
	}

}
