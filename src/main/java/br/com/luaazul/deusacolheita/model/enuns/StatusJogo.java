package br.com.luaazul.deusacolheita.model.enuns;

public enum StatusJogo {

	ATIVO(0),
	FINALIZADO(1),
	CANCELADO(2);
	
	private int status;
	
	StatusJogo(int status){
		this.status = status;
	}
	
	public int getStatus() {
		return this.status;
	}
	
}
