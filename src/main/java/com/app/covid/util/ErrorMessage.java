package com.app.covid.util;

import java.util.List;

public class ErrorMessage<E> {

	private int error;
	private String mensaje;
	private List<E> response;
	public ErrorMessage(int error, String mensaje, List<E> response) {
		super();
		this.error = error;
		this.mensaje = mensaje;
		this.response = response;
	}
	
	public ErrorMessage(int error, String mensaje) {
		super();
		this.error = error;
		this.mensaje = mensaje;
	}
	public int getError() {
		return error;
	}
	public void setError(int error) {
		this.error = error;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public List<E> getResponse() {
		return response;
	}
	public void setResponse(List<E> response) {
		this.response = response;
	}

	

}