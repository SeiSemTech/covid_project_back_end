package com.app.covid.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.covid.domain.AuthenticationRequest;
import com.app.covid.domain.AuthenticationResponse;
import com.app.covid.domain.Usuario;
import com.app.covid.repository.IUserRepository;
import com.app.covid.security.UserDetailService;
import com.app.covid.util.JwtUtil;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST,
		RequestMethod.OPTIONS }, allowedHeaders = "*")
public class AuthenticationController {

	private static final Logger log = (Logger) LoggerFactory.getLogger(AuthenticationController.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailService userDetailService;

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody AuthenticationRequest usuario) throws Exception {
		try {
			log.info(usuario.getUsername() + "   -   " + usuario.getPassword());
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(usuario.getUsername(), usuario.getPassword()));
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Credenciales incorrectas");
		}

		final UserDetails userDetails = userDetailService.loadUserByUsername(usuario.getUsername());
		final String jwt = jwtTokenUtil.generateToken(userDetails);

		Usuario user = userRepository.findByUser(usuario.getUsername());
		return ResponseEntity.ok(new AuthenticationResponse(jwt, user));
	}

}