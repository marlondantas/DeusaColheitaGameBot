package br.com.luaazul.deusacolheita.view.operacao;

import java.awt.Color;
import java.text.MessageFormat;
import java.util.List;

import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.luaazul.deusacolheita.view.AbstractOperacao;
import br.com.luaazul.deusacolheita.dao.model.JogoDAO;
import br.com.luaazul.deusacolheita.model.Jogo;

public class MeOperacao extends AbstractOperacao {

	public static Logger logger = LoggerFactory.getLogger(MeOperacao.class);

	/**
	 * ENVIAR A MENSAGEM DE INFORMACAO SOBRE O BOT.
	 */
	@Override
	public void execute(MessageCreateEvent event) {
		logger.info("INICIANDO OPERACAO");

		JogoDAO jogoDAO = new JogoDAO();

		EmbedBuilder embed = new EmbedBuilder().setTitle("Seus melhores jogos")
				.setDescription("TOP 3 Jogos com maiores pontos").setColor(Color.pink);

		String[] medalhas = { ":first_place:", ":second_place:", ":third_place:" };

		try {
			jogoDAO.beginTransaction();

			Jogo usuarioOpereacao = new Jogo(event);

			List<Jogo> melhoresJogo = jogoDAO.buscarTopTresJogo(usuarioOpereacao);

			int count = 0;
			for (Jogo jogoFor : melhoresJogo) {
				String totalPontos = MessageFormat.format("Com o total de {0} pontos.", jogoFor.getPontos());
				embed.addField((count + 1) + "º Colocação " + medalhas[count], totalPontos);
				count++;
			}

			event.getMessage().reply(embed);

		} catch (Exception e) {
			logger.error(e.getMessage());

		} finally {
			jogoDAO.close();
		}

		logger.info("FINALIZANDO OPERACAO");
	}

}
