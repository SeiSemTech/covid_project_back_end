package com.app.covid.domain;

public class AuthenticationResponse {

	private final String jwt;
	private final Usuario usuario;

	public AuthenticationResponse(String jwt, Usuario user) {
		super();
		this.jwt = jwt;
		this.usuario = user;
	}

	public String getJwt() {
		return jwt;
	}

	public Usuario getUsuario() {
		return usuario;
	}

}