package com.app.covid.service.implementation;

import com.app.covid.domain.CentroSalud;
import com.app.covid.domain.Laboratorio;
import com.app.covid.repository.ICentroSalud;
import com.app.covid.repository.ILaboratorioRepository;
import com.app.covid.service.ICentroSaludService;
import com.app.covid.service.ILaboratorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CentroSaludService implements ICentroSaludService {

	@Autowired
	private ICentroSalud centroDao;

	@Override
	public List<CentroSalud> getCentros() {
		return centroDao.findAll();
	}

	@Override
	public CentroSalud updateCentro(CentroSalud centro) {
		return centroDao.save(centro);
	}

	@Override
	public CentroSalud findByC(Long id) {
		return centroDao.findByC(id);
	}

	@Override
	public CentroSalud findByCentro(String centro) {
		return centroDao.findByCentro(centro);
	}

}
