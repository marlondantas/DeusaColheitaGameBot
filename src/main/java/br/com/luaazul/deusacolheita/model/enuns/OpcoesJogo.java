package br.com.luaazul.deusacolheita.model.enuns;

public enum OpcoesJogo {

	MAIOR(0),
	MENOR(1),
	SAIR(2);
	
	private int opcao;
	
	OpcoesJogo(int opcao){
		this.opcao = opcao;
	}
	
	public int getOpcao() {
		return this.opcao;
	}
}
