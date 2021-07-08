package br.com.luaazul.deusacolheita.view.operacao;

import java.awt.Color;
import java.util.Iterator;
import java.util.List;

import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.text.MessageFormat;

import br.com.luaazul.deusacolheita.view.AbstractOperacao;
import br.com.luaazul.deusacolheita.dao.model.HistoricoJogoDAO;
import br.com.luaazul.deusacolheita.dao.model.JogoDAO;
import br.com.luaazul.deusacolheita.model.HistoricoJogo;
import br.com.luaazul.deusacolheita.model.Jogo;

public class RankOperacao extends AbstractOperacao {

	public static Logger logger = LoggerFactory.getLogger(RankOperacao.class);
	
	/**
	 * ENVIAR A MENSAGEM DE INFORMACAO SOBRE O BOT.
	 */
	@Override
	public void execute(MessageCreateEvent event) {
		logger.info("INICIANDO OPERACAO");
		
		JogoDAO jogoDAO = new JogoDAO();
		
		EmbedBuilder embed = new EmbedBuilder()
			    .setTitle("Melhores jogos do server")
			    .setDescription("TOP 3 Jogos com maiores pontos")
			    .setColor(Color.pink);
		
		String[] medalhas  = {":first_place:",":second_place:",":third_place:"};
		
		try {
			jogoDAO.beginTransaction();
			
			Jogo usuarioOpereacao = new Jogo(event);
				
			List<Jogo> melhoresJogo = jogoDAO.buscarTopTresServidor(usuarioOpereacao);
			
			int count = 0;
			for (Jogo jogoFor : melhoresJogo) {
				String totalPontos = MessageFormat.format("Com o total de {0} pontos.", jogoFor.getPontos());
				embed.addField((count+1)+"º Colocação "+medalhas[count], totalPontos);
				count++;
			}
			
			event.getMessage().reply(embed);

		} catch (Exception e) {
			logger.error(e.getMessage());
	
		}finally {
			jogoDAO.close();
		}
		
		logger.info("FINALIZANDO OPERACAO");
	}

}
