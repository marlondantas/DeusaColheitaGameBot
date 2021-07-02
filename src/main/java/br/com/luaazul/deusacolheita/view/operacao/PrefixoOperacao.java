package br.com.luaazul.deusacolheita.view.operacao;

import java.awt.Color;

import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.luaazul.deusacolheita.dao.model.ServidorDAO;
import br.com.luaazul.deusacolheita.model.Servidor;
import br.com.luaazul.deusacolheita.view.AbstractOperacao;

public class PrefixoOperacao extends AbstractOperacao {

	public static Logger logger = LoggerFactory.getLogger(PrefixoOperacao.class);
	
	/**
	 * ENVIAR A MENSAGEM DE INFORMACAO SOBRE O BOT.
	 */
	@Override
	public void execute(MessageCreateEvent event) {
		logger.info("INICIANDO OPERACAO");
		
		ServidorDAO servidorDAO = new ServidorDAO();
		
		EmbedBuilder embed = new EmbedBuilder()
			    .setTitle("Operacao Realizada")
			    .setDescription("Bot by Mar@MoonBlue")
			    .setColor(Color.GREEN);
		
		try {
			servidorDAO.beginTransaction();
			//pega o prefixo
			String[] arrayValores = event.getMessage().getContent().toUpperCase().split(" ");
			//pega o servidor
			//verifica se existe no banco
			Servidor servidor = servidorDAO.buscarServidorServidor(event.getServer().get().getIdAsString());
			
			if(servidor == null) {
				servidor = new Servidor();
				servidor.setIdServidor(event.getServer().get().getIdAsString());
			}
			
			servidor.setPrefixo(arrayValores[1]);				
			//salva
			
			servidorDAO.save(servidor);
			event.getChannel().sendMessage(embed);
			
			servidorDAO.commit();
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.getStackTrace();
			servidorDAO.rollBack();
		}finally {
			servidorDAO.close();
		}
		
		logger.info("FINALIZANDO OPERACAO");
	}

}
