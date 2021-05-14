package com.app.covid.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.app.covid.constants.ResourceMapping;
import com.app.covid.domain.CentroSalud;
import com.app.covid.service.ICentroSaludService;
import com.app.covid.util.ErrorMessage;
import com.app.covid.util.ErrorMessage2;

@RestController
@RequestMapping(ResourceMapping.VACUNA)
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST,
		RequestMethod.OPTIONS }, allowedHeaders = "*")
public class EntregaController {

	@Autowired
	private ICentroSaludService centroService;

	// servicio que trae el listado de centros
	@RequestMapping(value = "/getCentros", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<ErrorMessage<List<CentroSalud>>> getce() {
		List<CentroSalud> listado = centroService.getCentros();
		ErrorMessage<List<CentroSalud>> error = listado.isEmpty()
				? new ErrorMessage<>(1, "No se ha encontrado información", null)
				: new ErrorMessage<>(0, "Lista de centros", listado);
		return new ResponseEntity<>(error, HttpStatus.OK);
	}

	@RequestMapping(value = "/buscarCentros", method = RequestMethod.GET, headers = "Accept=application/json")
	private List<CentroSalud> getCentros() {
		String uri = "https://www.datos.gov.co/resource/u82n-j82m.json";
		RestTemplate restTemplate = new RestTemplate();
		CentroSalud[] result = restTemplate.getForObject(uri, CentroSalud[].class);
		CentroSalud centroG = null;
		List<CentroSalud> centro = new ArrayList<>();

		for (int i = 0; i < result.length; i++) {
			centro.add(result[i]);
			for (int i1 = 0; i1 < centro.size(); i1++) {
				centroG = centro.get(i);
			}
			CentroSalud cen = centroService.findByCentro(centroG.getDireccion());
			if (cen == null) {
				centroService.updateCentro(centroG);
				System.out.println("No existe");
			} else {
				System.out.println("ya existe");
			}
		}
		return centro;
	}

	// servicio para actualizar centro
	@RequestMapping(value = "/enviarVacuna", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<?> deleteUsuario(@RequestBody CentroSalud centro) {
		CentroSalud cen = centroService.findByC(centro.getId());
		if (cen == null) {
			return new ResponseEntity(new ErrorMessage2(1, "No sea encontrado el centro de salud"), HttpStatus.OK);
		}
		cen.setCantidad(centro.getCantidad());
		centroService.updateCentro(cen);
		return new ResponseEntity(new ErrorMessage2(0, "centro de salud actualizado con exito!"), HttpStatus.OK);
	}

}
