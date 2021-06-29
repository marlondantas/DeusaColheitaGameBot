package br.com.luaazul.deusacolheita.model;

import java.util.Date;
import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.luaazul.deusacolheita.model.enuns.DificuldadeJogo;
import br.com.luaazul.deusacolheita.model.enuns.OpcoesJogo;

@Entity
@Table(name = "TBOD_HISTORICO_JOGO", indexes = { @Index(name = "IDX_hist_JOGO_JOGO", columnList = "ID_JOGO,ID_HIST_JOGO")} )
public class HistoricoJogo {

    @Id
    @Column(name = "ID_HIST_JOGO")
    @GeneratedValue
	private int id;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_OPERACAO",nullable=false,columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP")
	private Date dataOperacao;
	
    @ManyToOne
    @JoinColumn(name = "ID_JOGO")
	private Jogo jogo;
	
	@Column(name = "NR_NUMERO_ATUAL")
	private int numero;
	
	@Column(name = "NR_SEQUENCIA")
	private int sequencia;
	
	@Enumerated
	@Column(name = "ID_OPCAO")
	private OpcoesJogo opcao;
	
	public HistoricoJogo() {
		
	}
	
	public HistoricoJogo(HistoricoJogo historicoJogoOLD){
		this.setJogo(historicoJogoOLD.getJogo());
		this.setSequencia(historicoJogoOLD.getSequencia());
		this.setDataOperacao(new Date());
	}

	public HistoricoJogo(DificuldadeJogo dificuldadeJogo){
		
		this.gerarNumero(dificuldadeJogo);
		
	}

	public HistoricoJogo(Jogo jogo) {
		
		this.setJogo(jogo);
		this.setDataOperacao(new Date());
		this.setNumero(new Random().nextInt(jogo.getDificuldade().getDificuldade()*10));
		this.setSequencia(0);
		
	}
	
	public void gerarNumero(DificuldadeJogo dificuldadeJogo) {
		
		this.numero = new Random().nextInt(dificuldadeJogo.getDificuldade()*10);
		
	}
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDataOperacao() {
		return dataOperacao;
	}

	public void setDataOperacao(Date dataOperacao) {
		this.dataOperacao = dataOperacao;
	}

	public Jogo getJogo() {
		return jogo;
	}

	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public int getSequencia() {
		return this.sequencia;
	}

	public void setSequencia(int sequencia) {
		this.sequencia = sequencia;
	}

	public OpcoesJogo getOpcao() {
		return opcao;
	}

	public void setOpcao(OpcoesJogo opcao) {
		this.opcao = opcao;
	}

	public void aumentarSequencia() {
		this.setSequencia(this.getSequencia()+1);
	}
	
	
}
