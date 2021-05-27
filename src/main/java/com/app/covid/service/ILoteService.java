package com.app.covid.service;

import com.app.covid.domain.Lote;
import com.app.covid.domain.Usuario;

import java.util.List;
import java.util.Optional;

public interface ILoteService {

	List<Lote> getLotes();

	void createLote(Lote user);

	Lote updateLote(Lote user);

	void deleteLote(Long id);

	Optional<Lote> findByIdLote(Long id);

	Lote findByLote(Long lote);

	Lote findBy(Long lot);

}
