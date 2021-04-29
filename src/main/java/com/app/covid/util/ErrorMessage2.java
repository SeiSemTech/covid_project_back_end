package com.app.covid.util;

public class ErrorMessage2 {

	private int error;
	private String mensaje;

	public ErrorMessage2(int error, String mensaje) {
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
	

}