package com.app.covid.controller;

import com.app.covid.constants.ResourceMapping;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.covid.domain.AuthenticationRequest;
import com.app.covid.domain.Usuario;
import com.app.covid.service.IUserService;
import com.app.covid.util.ErrorMessage;
import com.app.covid.util.ErrorMessage2;

@RestController
@RequestMapping(ResourceMapping.USER)
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST,
		RequestMethod.OPTIONS }, allowedHeaders = "*")
public class UsuarioController {

	@Autowired
	private IUserService userService;

	@GetMapping()
	public ResponseEntity<?> saludo() {
		return new ResponseEntity<>(new ErrorMessage2(0, "Bienvenido al sistema covid!"), HttpStatus.OK);
	}

	// servicio que tarea el listado de usuarios
	@GetMapping("/getUsuarios")
	public ResponseEntity<ErrorMessage<List<Usuario>>> getUser() {
		List<Usuario> listado = userService.getUsuarios();
		ErrorMessage<List<Usuario>> error = listado.isEmpty() ?
				new ErrorMessage<>(1, "No se ha encontrado información",null) :
				new ErrorMessage<>(0, "Lista de Usuarios", listado);
		return new ResponseEntity<>(error, HttpStatus.OK);
	}

	// servicio para crear un usuario
	@RequestMapping(value = "/createUsuario", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<?> createUser(@RequestBody Usuario user) {
		Usuario user2 = userService.findByUser(user.getDocument());
		if (user2 != null) {
			return new ResponseEntity(new ErrorMessage2(1, "El usuario ya se encuentra registrado"), HttpStatus.OK);
		}

		if (user.getName().isEmpty() || user.getLastname().isEmpty()) {
			return new ResponseEntity(new ErrorMessage2(2, "información incompleta"), HttpStatus.OK);
		}
		user.setState(true);
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

	// servcio validacion login opcional

	@RequestMapping(value = "/login", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<?> login(@RequestBody AuthenticationRequest user) {
		Usuario user2 = userService.findByLogin(user.getUsername(), user.getPassword());
		System.out.println("usuario camilo " + user2);
		if (user2 == null) {
			return new ResponseEntity(new ErrorMessage2(1, "Credenciales incorrectas"), HttpStatus.OK);
		}

		return new ResponseEntity(new ErrorMessage2(0, "Autenticacion exitosa!"), HttpStatus.OK);
	}

}
