package com.app.covid.controller;

import com.app.covid.constants.ResourceMapping;
import com.app.covid.domain.Laboratorio;
import com.app.covid.service.ILaboratorioService;
import com.app.covid.util.ErrorMessage;
import com.app.covid.util.ErrorMessage2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(ResourceMapping.LABORATORIO)
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST,
		RequestMethod.OPTIONS }, allowedHeaders = "*")
public class LaboratorioController {

	@Autowired
	private ILaboratorioService laboratorioService;

	// servicio que trae el listado de Laboratorios
	@GetMapping("/getLaboratorios")
	public ResponseEntity<ErrorMessage<List<Laboratorio>>> getLaboratorio() {
		List<Laboratorio> listado = laboratorioService.getLaboratorios();
		ErrorMessage<List<Laboratorio>> error = listado.isEmpty() ?
				new ErrorMessage<>(1, "No se ha encontrado información",null) :
				new ErrorMessage<>(0, "Lista de Laboratorios", listado);
		return new ResponseEntity<>(error, HttpStatus.OK);
	}


}
