package com.mx.apiPlataformasEspeciales2.dao;


public class TransaccionRequest {

	private String operacion;
    private String importe;
    private String cliente;
    private String secreto;
	public String getOperacion() {
		return operacion;
	}
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}
	public String getImporte() {
		return importe;
	}
	public void setImporte(String importe) {
		this.importe = importe;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getSecreto() {
		return secreto;
	}
	public void setSecreto(String secreto) {
		this.secreto = secreto;
	}
    
    
    
}
