package br.com.luaazul.deusacolheita.controller;

import java.util.HashMap;
import java.util.Map;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import br.com.luaazul.deusacolheita.view.AbstractOperacao;
import br.com.luaazul.deusacolheita.view.operacao.*;

public class OperacaoService implements MessageCreateListener {

	@Override
	public void onMessageCreate(MessageCreateEvent event) {

		
		Map<String, AbstractOperacao> operacoes = getOperacoes();
		
		
		for (String key : operacoes.keySet()) {
			
			if (key.equalsIgnoreCase(event.getMessage().getContent())) {
				operacoes.get(key).execute(event);
				break;
			}
			
		}

	}

	public Map<String, AbstractOperacao> getOperacoes() {
		Map<String, AbstractOperacao> operacoes = new HashMap<String, AbstractOperacao>();

		operacoes.put("!!start", new StartOperacao());
		operacoes.put("!!me", new PingOperacao());
		operacoes.put("!!rank", new PingOperacao());
		operacoes.put("!!maior", new MaiorOperacao());
		operacoes.put("!!menor", new MenorOperacao());
		operacoes.put("!!info", new InfoOperacao());
		operacoes.put("!!ping", new PingOperacao());
		operacoes.put("!!cancelar", new PingOperacao());
		operacoes.put("!!apagar", new PingOperacao());
		operacoes.put("!!apagarall", new PingOperacao());

		return operacoes;
	}

}
