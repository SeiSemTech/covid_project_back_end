package com.app.covid.controller;

import com.app.covid.constants.ResourceMapping;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.covid.domain.Role;
import com.app.covid.service.IRoleService;
import com.app.covid.util.ErrorMessage;

@RestController
@RequestMapping("/rol")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST,
		RequestMethod.OPTIONS }, allowedHeaders = "*")
public class RoleController {

	@Autowired
	private IRoleService roleService;

	// servicio que tarea el listado de usuarios
	@GetMapping("/getRoles")
	public ResponseEntity<ErrorMessage<List<Role>>> getRoles() {
		List<Role> listado = roleService.getRoles();
		ErrorMessage<List<Role>> error = listado.isEmpty()
				? new ErrorMessage<>(1, "No se ha encontrado información", null)
				: new ErrorMessage<>(0, "Lista de Roles", listado);
		return new ResponseEntity<>(error, HttpStatus.OK);
	}
}
