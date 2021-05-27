package com.app.covid.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.covid.domain.Etapa;
import com.app.covid.domain.Ocupacion;

public interface IOcupacion extends JpaRepository<Ocupacion, Integer> {

}
