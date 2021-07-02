package br.com.luaazul.deusacolheita.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBOD_SERVIDOR")
public class Servidor {

    @Id
    @Column(name = "ID_SERVIDOR",nullable=false)
    @GeneratedValue
	private int id;
	
    @Column(name = "DS_SERVIDOR",nullable=false)
	private String servidor;
	
    @Column(name = "DS_PREFIXO",nullable=false)
	private String prefixo;
	
	
    public Servidor(){
    	
    }

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getIdServidor() {
		return servidor;
	}


	public void setIdServidor(String idServidor) {
		this.servidor = idServidor;
	}


	public String getPrefixo() {
		return prefixo;
	}


	public void setPrefixo(String prefixo) {
		this.prefixo = prefixo;
	}
    
    
    
	
}
