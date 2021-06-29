package br.com.luaazul.deusacolheita.view.operacao;

import java.awt.Color;
import java.util.List;

import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.luaazul.deusacolheita.view.AbstractOperacao;

public class InfoOperacao extends AbstractOperacao {

	public static Logger logger = LoggerFactory.getLogger(InfoOperacao.class);
	
	/**
	 * ENVIAR A MENSAGEM DE INFORMACAO SOBRE O BOT.
	 */
	@Override
	public void execute(MessageCreateEvent event) {
		logger.info("INICIANDO OPERACAO");
		
		EmbedBuilder embed = new EmbedBuilder()
			    .setTitle("Informações")
			    .setDescription("Bot by Mar@MoonBlue")
			    .setAuthor("Mar", "", "https://i.imgur.com/4M34hi2.png")
			    .setColor(Color.BLUE);
		try {
			event.getChannel().sendMessage(embed);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		logger.info("FINALIZANDO OPERACAO");
	}

}
