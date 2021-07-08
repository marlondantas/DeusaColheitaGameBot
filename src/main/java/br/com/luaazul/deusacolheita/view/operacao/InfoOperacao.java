package br.com.luaazul.deusacolheita.view.operacao;

import java.awt.Color;

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
			    
			    .addField("start", "Começa um novo jogo")

			    .addField("maior", "Aposta que o proximo número vai ser maior")
			    .addField("menor", "Aposta que o proximo número vai ser menor")
			    
			    .addField("me", "Mostra os seus melhores jogos")
			    .addField("rank", "Mostra os melhores jogos do servidor")
			    
			    .addField("info", "Mostra esse bloco de ajuda hehe")
			    .addField("ping", "?")
			    .addField("cancelar", "Cancela a interação com o BOT")
			    .addField("apagar", "Apaga os dados DESSE servidor")
			    .addField("apagarALL", "Apaga os dados de TODOS os servidores")
			    .addField("prefixo", "Muda o prefixo do BOT")
			    
			    .setDescription("Bot by Mar@MoonBlue \nAgradecimentos @AWildFilli")
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
