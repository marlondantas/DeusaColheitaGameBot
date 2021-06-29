package br.com.luaazul.deusacolheita.model.enuns;

public enum DificuldadeJogo {

	FACIL(1),
	MEDIO(10),
	DIFICIL(100),
	DEUSA(1000);
	
	private int dificuldade;
	
	DificuldadeJogo(int dificuldade){
		this.dificuldade = dificuldade;
	}
	
	public int getDificuldade() {
		return this.dificuldade;
	}
}
