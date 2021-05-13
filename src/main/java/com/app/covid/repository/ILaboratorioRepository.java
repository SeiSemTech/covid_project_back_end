package com.app.covid.repository;

import com.app.covid.domain.Laboratorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ILaboratorioRepository extends JpaRepository<Laboratorio, Integer> {

	
}
