package br.com.luaazul.deusacolheita.controller;

import java.util.HashMap;
import java.util.Map;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import br.com.luaazul.deusacolheita.model.Servidor;
import br.com.luaazul.deusacolheita.view.AbstractOperacao;
import br.com.luaazul.deusacolheita.view.operacao.*;

public class OperacaoService implements MessageCreateListener {

	
	private Map<String, AbstractOperacao> operacoes;
	
	private ServidorService servidorService;
	
	public OperacaoService(){
		operacoes = getOperacoes();
		servidorService = new ServidorService();
	}
	
	@Override
	public void onMessageCreate(MessageCreateEvent event) {
		
		//verifica se nao e um bot
		
		//verificar prefixo do servidor
		Servidor servidor = new Servidor();
		servidor.setIdServidor(event.getServer().get().getIdAsString());
		
		String prefixo = servidorService.setServidor(servidor).getPrefixo();
		
		String[] arrayValores = event.getMessage().getContent().toLowerCase().split(" ");
		
		String chaveOperacao = arrayValores[0].replace(prefixo,"");
		
		if(this.operacoes.containsKey(chaveOperacao)) {
			this.operacoes.get(chaveOperacao).execute(event);
		}

	}

	public Map<String, AbstractOperacao> getOperacoes() {
		Map<String, AbstractOperacao> operacoes = new HashMap<String, AbstractOperacao>();

		operacoes.put("start", new StartOperacao());
		operacoes.put("me", new PingOperacao());
		operacoes.put("rank", new PingOperacao());
		operacoes.put("maior", new MaiorOperacao());
		operacoes.put("menor", new MenorOperacao());
		operacoes.put("info", new InfoOperacao());
		operacoes.put("ping", new PingOperacao());
		operacoes.put("cancelar", new PingOperacao());
		operacoes.put("apagar", new PingOperacao());
		operacoes.put("apagarall", new PingOperacao());
		operacoes.put("prefixo", new PrefixoOperacao());
		
		return operacoes;
	}

}
