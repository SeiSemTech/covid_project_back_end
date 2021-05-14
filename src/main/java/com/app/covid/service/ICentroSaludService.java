package com.app.covid.service;

import java.util.List;

import com.app.covid.domain.CentroSalud;

public interface ICentroSaludService {

	List<CentroSalud> getCentros();

	CentroSalud updateCentro(CentroSalud centro);

	CentroSalud findByC(Long centro);

	CentroSalud findByCentro(String centro);
}
