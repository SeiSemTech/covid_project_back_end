package com.app.covid.controller;

import com.app.covid.constants.ResourceMapping;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.app.covid.domain.CentroSalud;
import com.app.covid.domain.Role;
import com.app.covid.service.IRoleService;
import com.app.covid.util.ErrorMessage;

@RestController
@RequestMapping(ResourceMapping.ROL)
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST,
		RequestMethod.OPTIONS }, allowedHeaders = "*")
public class RoleController {

	@Autowired
	private IRoleService roleService;

	// servicio que trae el listado de roles
	@RequestMapping(value = "/getPerfiles", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<ErrorMessage<List<Role>>> getRoles() {
		List<Role> listado = roleService.getRoles();
		ErrorMessage<List<Role>> error = listado.isEmpty()
				? new ErrorMessage<>(1, "No se ha encontrado información", null)
				: new ErrorMessage<>(0, "Lista de Roles", listado);
		return new ResponseEntity<>(error, HttpStatus.OK);
	}

	/*@RequestMapping(value = "/get", method = RequestMethod.GET, headers = "Accept=application/json")
	private List<CentroSalud> getStudentObject() {
		String uri = "https://www.datos.gov.co/resource/u82n-j82m.json";
		RestTemplate restTemplate = new RestTemplate();
		CentroSalud result = restTemplate.getForObject(uri, CentroSalud.class);
		List<CentroSalud> result2 = (List<CentroSalud>) result;
		return result2;
	}*/

}
