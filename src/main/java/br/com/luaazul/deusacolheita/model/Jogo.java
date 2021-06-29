package br.com.luaazul.deusacolheita.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.javacord.api.event.message.MessageCreateEvent;

import br.com.luaazul.deusacolheita.model.enuns.DificuldadeJogo;
import br.com.luaazul.deusacolheita.model.enuns.StatusJogo;

@Entity
@Table(name = "TBOD_JOGO", indexes = { @Index(name = "IDX_JOGO_DSJOGADOR", columnList = "ID_JOGO,DS_SERVIDOR,DS_JOGADOR"),
			@Index(name = "IDX_JOGO_STATUS", columnList = "ID_STATUS") })
public class Jogo {

    @Id
    @Column(name = "ID_JOGO")
    @GeneratedValue
	private int id;
    
    @Column(name = "DS_SERVIDOR")
	private String servidor;
    
    @Column(name = "DS_JOGADOR")
    private String jogador;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_INICIAL",nullable=false,columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP")
	private Date dataInicial;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_FINAL")
	private Date dataFinal;
    
    @Column(name = "ID_TOTAL_PONTOS")
	private int pontos;
    
    @Enumerated
    @Column(name = "ID_STATUS")
	private StatusJogo status;
    
    @Enumerated
    @Column(name = "ID_DIFICULDADE")
	private DificuldadeJogo dificuldade;
	
	public Jogo(){
		
	}
	
	public Jogo(MessageCreateEvent event){
		
		this.setJogador(event.getMessage().getAuthor().getIdAsString());
		this.setServidor(event.getServer().get().getIdAsString());
		
		this.setDificuldade(DificuldadeJogo.FACIL);
		this.setStatus(StatusJogo.ATIVO);	
		this.setDataInicial(new Date());
	
	}
	


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getServidor() {
		return servidor;
	}

	public void setServidor(String servidor) {
		this.servidor = servidor;
	}

	public String getJogador() {
		return jogador;
	}

	public void setJogador(String jogador) {
		this.jogador = jogador;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public int getPontos() {
		return pontos;
	}

	public void setPontos(int pontos) {
		this.pontos = pontos;
	}

	public StatusJogo getStatus() {
		return status;
	}

	public void setStatus(StatusJogo status) {
		this.status = status;
	}

	public DificuldadeJogo getDificuldade() {
		return dificuldade;
	}

	public void setDificuldade(DificuldadeJogo dificuldade) {
		this.dificuldade = dificuldade;
	}

	@Override
	public String toString() {
		return "Jogo [id=" + id + ", servidor=" + servidor + ", jogador=" + jogador + ", dataInicial=" + dataInicial
				+ ", dataFinal=" + dataFinal + ", pontos=" + pontos + ", status=" + status + ", dificuldade="
				+ dificuldade + "]";
	}

	public void aumentarPonto() {
		this.setPontos(this.getPontos()+(1*this.getDificuldade().getDificuldade()));
	}

	public void perdeuJogo() {
		this.setDataFinal(new Date());
		this.setStatus(StatusJogo.FINALIZADO);
		
	}
	
	
	
}
