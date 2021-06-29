package br.com.luaazul.deusacolheita.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TBOD_registros")
public class Registro {

    @Id
    @Column(name = "ID_REGISTRO",nullable=false)
    @GeneratedValue
	private int id;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_REGISTRO",nullable=false,columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP")
	private Date dataRegistro;
	
    @Column(name = "DS_JOGADOR")
    private String nomeJogador;
	
    @Column(name = "CD_JOGADOR")
    private String idJogador;
    
    @Column(name = "DS_RESPOSTA")
	private String resposta;
	
	Registro(){
		
	}
	
}
