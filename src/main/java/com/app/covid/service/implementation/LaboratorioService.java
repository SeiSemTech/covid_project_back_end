package com.app.covid.service.implementation;

import com.app.covid.domain.Laboratorio;
import com.app.covid.repository.ILaboratorioRepository;
import com.app.covid.service.ILaboratorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaboratorioService implements ILaboratorioService {

	@Autowired
	private ILaboratorioRepository laboratorioDao;

	@Override
	public List<Laboratorio> getLaboratorios() {
		return laboratorioDao.findAll();
	}

}
