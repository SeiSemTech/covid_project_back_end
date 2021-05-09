package com.app.covid.controller;

import com.app.covid.constants.ResourceMapping;
import com.app.covid.domain.AuthenticationRequest;
import com.app.covid.domain.AuthenticationResponse;
import com.app.covid.domain.Usuario;
import com.app.covid.repository.IUserRepository;
import com.app.covid.security.UserDetailService;
import com.app.covid.util.ErrorMessage2;
import com.app.covid.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST,
		RequestMethod.OPTIONS }, allowedHeaders = "*")
public class AuthenticationController {

	private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailService userDetailService;

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@PostMapping(ResourceMapping.LOGIN)
	public ResponseEntity<?> login(@RequestBody AuthenticationRequest usuario) throws Exception {
		try {

			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(usuario.getUsername(), usuario.getPassword()));

		} catch (Exception e) {
			return ResponseEntity.ok(new ErrorMessage2(403, "Credenciales incorrectas"));
		}

		final UserDetails userDetails = userDetailService.loadUserByUsername(usuario.getUsername());
		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}

}
