package br.com.luaazul.deusacolheita.controller;

import br.com.luaazul.deusacolheita.dao.model.ServidorDAO;
import br.com.luaazul.deusacolheita.model.Servidor;

public class ServidorService {

	private Servidor servidor;

	private ServidorDAO servidorDAO;
	
	public ServidorService(Servidor Servidor) {
		servidorDAO = new ServidorDAO();
		this.servidor = servidor;
	}
	
	public ServidorService() {
		servidorDAO = new ServidorDAO();
	}
	
	public ServidorService setServidor(Servidor servidor) {
		this.servidor = servidor;
		this.temPrefixo();
		return this;
	}
	
	public boolean temPrefixo() {
		
		this.servidor = servidorDAO.buscarServidorServidor(servidor);
		
		if(this.servidor!=null) {
			return true;
		}
		
		return false;
		
	}
	
	public String getPrefixo() {
		if(this.servidor!=null) {
			return this.servidor.getPrefixo();
		} 
		
		return "!!";
	}
	
}
