package com.app.covid.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.data.repository.query.Param;

import com.app.covid.domain.Paciente;

public interface IPacienteRepository extends CrudRepository<Paciente, Long> {

	@Query("Select p FROM Paciente p WHERE p.id = :pac")
	Paciente findBy(@Param("pac") Long pac);

	@Query("Select p FROM Paciente p WHERE p.centroSalud.id = :pac")
	Iterable<Paciente> findByCentro(@Param("pac") Long pac);

}
