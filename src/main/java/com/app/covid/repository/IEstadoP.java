package com.app.covid.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.covid.domain.EstadoPaciente;
import com.app.covid.domain.Etapa;

public interface IEstadoP extends JpaRepository<EstadoPaciente, Integer> {

}
