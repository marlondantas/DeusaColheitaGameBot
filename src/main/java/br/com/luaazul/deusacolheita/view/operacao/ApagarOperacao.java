package br.com.luaazul.deusacolheita.view.operacao;

import org.javacord.api.event.message.MessageCreateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.luaazul.deusacolheita.view.AbstractOperacao;
import br.com.luaazul.deusacolheita.controller.operacao.ApagarOpereacaoService;
import br.com.luaazul.deusacolheita.model.Jogo;

public class ApagarOperacao extends AbstractOperacao {

	public static Logger logger = LoggerFactory.getLogger(ApagarOperacao.class);
	
	/**
	 * ENVIAR A MENSAGEM DE INFORMACAO SOBRE O BOT.
	 */
	@Override
	public void execute(MessageCreateEvent event) {
		logger.info("INICIANDO OPERACAO");
	
		String mensagem;
		
		ApagarOpereacaoService apagarOpereacaoService = new ApagarOpereacaoService();
		
		try {
			
			
			Jogo usuarioOpereacao = new Jogo(event);
			
			boolean saida = apagarOpereacaoService.deletarServidor(usuarioOpereacao);
			
			if(saida) {
				mensagem = "Todos os dados nesse servidor foram apagados.";
			}else {
				mensagem = "Aconteceu um erro no momento da exclusao";
			}
			
			event.getChannel().sendMessage(mensagem);
			
		} catch (Exception e) {
			logger.error(e.getMessage());
	
		}
		
		logger.info("FINALIZANDO OPERACAO");
	}

}
