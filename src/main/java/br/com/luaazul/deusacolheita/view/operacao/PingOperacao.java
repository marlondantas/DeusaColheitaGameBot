package br.com.luaazul.deusacolheita.view.operacao;

import org.javacord.api.event.message.MessageCreateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.luaazul.deusacolheita.view.AbstractOperacao;

public class PingOperacao extends AbstractOperacao {

	public static Logger logger = LoggerFactory.getLogger(PingOperacao.class);
	
	/**
	 * ENVIAR A MENSAGEM DE INFORMACAO SOBRE O BOT.
	 */
	@Override
	public void execute(MessageCreateEvent event) {
		logger.info("INICIANDO OPERACAO");
		
		try {
			event.getChannel().sendMessage("Pong!");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		logger.info("FINALIZANDO OPERACAO");
	}

}
