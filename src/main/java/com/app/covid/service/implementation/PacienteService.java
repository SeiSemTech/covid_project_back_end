package com.app.covid.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.covid.domain.Paciente;
import com.app.covid.repository.IPacienteRepository;
import com.app.covid.service.IPacienteService;

@Service
public class PacienteService implements IPacienteService {

	@Autowired
	private IPacienteRepository pacienteDao;

	@Override
	public List<Paciente> getPacientes() {
		return (List<Paciente>) pacienteDao.findAll();
	}

	@Override
	public Paciente findBy(Long id) {
		return pacienteDao.findBy(id);
	}

	@Override
	public List<Paciente> findByCentro(Long id_centro) {
		return (List<Paciente>) pacienteDao.findByCentro(id_centro);
	}

}
