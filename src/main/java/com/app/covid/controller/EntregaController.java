package com.app.covid.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.covid.constants.ResourceMapping;
import com.app.covid.domain.CentroSalud;
import com.app.covid.domain.Lote;
import com.app.covid.service.ICentroSaludService;
import com.app.covid.service.ILoteService;
import com.app.covid.util.ErrorMessage;
import com.app.covid.util.ErrorMessage2;

@RestController
@RequestMapping(ResourceMapping.VACUNA)
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST,
		RequestMethod.OPTIONS }, allowedHeaders = "*")
public class EntregaController {

	@Autowired
	private ICentroSaludService centroService;

	@Autowired
	private ILoteService loteService;

	// servicio que trae el listado de centros
	@RequestMapping(value = "/getCentros", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<ErrorMessage<List<CentroSalud>>> getce() {
		List<CentroSalud> listado = centroService.getCentros();
		ErrorMessage<List<CentroSalud>> error = listado.isEmpty()
				? new ErrorMessage<>(1, "No se ha encontrado información", null)
				: new ErrorMessage<>(0, "Lista de centros", listado);
		return new ResponseEntity<>(error, HttpStatus.OK);
	}

	/*
	 * @RequestMapping(value = "/buscarCentros", method = RequestMethod.GET, headers
	 * = "Accept=application/json") private List<CentroSalud> getCentros() { String
	 * uri = "https://www.datos.gov.co/resource/u82n-j82m.json"; RestTemplate
	 * restTemplate = new RestTemplate(); CentroSalud[] result =
	 * restTemplate.getForObject(uri, CentroSalud[].class); CentroSalud centroG =
	 * null; List<CentroSalud> centro = new ArrayList<>();
	 * 
	 * for (int i = 0; i < result.length; i++) { centro.add(result[i]); for (int i1
	 * = 0; i1 < centro.size(); i1++) { centroG = centro.get(i); } CentroSalud cen =
	 * centroService.findByCentro(centroG.getDireccion()); if (cen == null) {
	 * centroService.updateCentro(centroG); System.out.println("No existe"); } else
	 * { System.out.println("ya existe"); } } return centro; }
	 */

	// servicio para enviar vacunacion
	@RequestMapping(value = "/enviarVacuna", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<?> deleteUsuario(@RequestBody CentroSalud centro) {
		CentroSalud cen = centroService.findByC(centro.getId());
		if (cen == null) {
			return new ResponseEntity(new ErrorMessage2(1, "No sea encontrado el centro de salud"), HttpStatus.OK);
		}
		int cantidad = 0;
		if (cen.getCantidad() == null) {
			cantidad = 0;
		} else {
			cantidad = cen.getCantidad();
		}
		cen.setCantidad(cantidad + centro.getCantidad());
		Lote lo = loteService.findBy(centro.getLote().getId());
		if (lo.getId() != null) {
			int resta = 0;
			if (lo.getCantidad() > centro.getCantidad()) {
				resta = lo.getCantidad() - centro.getCantidad();
				lo.setCantidad(resta);
				centroService.updateCentro(cen);
				loteService.updateLote(lo);
			} else {
				return new ResponseEntity(new ErrorMessage2(2,
						"El lote no cuenta con la cantidad solicitada; Cantidad disponible :" + lo.getCantidad()),
						HttpStatus.OK);
			}
		} else {
			return new ResponseEntity(new ErrorMessage2(2, "El lote no se encuentra disponible"), HttpStatus.OK);
		}
		return new ResponseEntity(new ErrorMessage2(0, "centro de salud actualizado con exito!"), HttpStatus.OK);
	}

}
