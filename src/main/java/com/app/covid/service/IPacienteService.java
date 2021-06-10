package com.app.covid.service;

import java.util.List;

import com.app.covid.domain.Paciente;

public interface IPacienteService {

	List<Paciente> getPacientes();

	Paciente findBy(Long id);

	List<Paciente> findByCentro(Long id_centro);

}
