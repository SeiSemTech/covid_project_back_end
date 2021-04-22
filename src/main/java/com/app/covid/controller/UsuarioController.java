package com.app.covid.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.query.criteria.internal.predicate.IsEmptyPredicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.covid.domain.Usuario;
import com.app.covid.service.IUserService;
import com.app.covid.util.ErrorMessage;
import com.app.covid.util.ErrorMessage2;



@RestController
@RequestMapping(value = "user/v1")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST,
		RequestMethod.OPTIONS }, allowedHeaders = "*")
public class UsuarioController {

	@Autowired
	private IUserService userService;

	@RequestMapping(value = "")
	public ResponseEntity saludo() {
		return new ResponseEntity(new ErrorMessage2(0, "Bienvenido al sistema covid!"), HttpStatus.OK);
	}

	// servcio que trea el listado de usuarios

	@RequestMapping(value = "/getUsuarios", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<List<Usuario>> getUser() {
		List<Usuario> listado = new ArrayList<>();
		listado = userService.getUsuarios();
		if (listado.isEmpty()) {
			return new ResponseEntity(new ErrorMessage2(1, "No se ha encontrado informacion"), HttpStatus.OK);
		} else {
			return new ResponseEntity(new ErrorMessage(0, "Lista de Usuarios", listado), HttpStatus.OK);
		}

	}
	// servicio para crear un usuario
	@RequestMapping(value = "/createUsuario", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<?> createUser(@RequestBody Usuario user) {
		if (user.getNombre().isEmpty() || user.getApellido().isEmpty()) {
			return new ResponseEntity(new ErrorMessage2(1, "informacion incompleta"), HttpStatus.OK);
		}
		user.setEstado(true);
		userService.createUsuario(user);
		return new ResponseEntity(new ErrorMessage2(0, "Usuario creado con exito!"), HttpStatus.OK);
	}

	// servicio para actualizar un usuario
	@RequestMapping(value = "/updateUsuario", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<?> updateUsuario(@RequestBody Usuario user) {
		Optional<Usuario> us = userService.findByIdUsuario(user.getId());
		if (!us.isPresent()) {
			return new ResponseEntity(new ErrorMessage2(1, "No sea encontrado el usuario"), HttpStatus.OK);
		}
		userService.updateUsuario(user);
		return new ResponseEntity(new ErrorMessage2(0, "Usuario actualizado con exito!"), HttpStatus.OK);
	}
	
	// servicio para eliminar un usuario
		@RequestMapping(value = "/deleteUsuario", method = RequestMethod.POST, headers = "Accept=application/json")
		public ResponseEntity<?> deleteUsuario(@RequestBody Usuario user) {
			Optional<Usuario> us = userService.findByIdUsuario(user.getId());
			if (!us.isPresent()) {
				return new ResponseEntity(new ErrorMessage2(1, "No sea encontrado el usuario"), HttpStatus.OK);
			}
			userService.deleteUsuario(user.getId());
			return new ResponseEntity(new ErrorMessage2(0, "Usuario eliminado con exito!"), HttpStatus.OK);
		}

}
