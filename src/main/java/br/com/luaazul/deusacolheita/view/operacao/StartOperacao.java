package br.com.luaazul.deusacolheita.view.operacao;

import org.javacord.api.event.message.MessageCreateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.luaazul.deusacolheita.view.AbstractOperacao;
import br.com.luaazul.deusacolheita.dao.model.HistoricoJogoDAO;
import br.com.luaazul.deusacolheita.dao.model.JogoDAO;
import br.com.luaazul.deusacolheita.model.HistoricoJogo;
import br.com.luaazul.deusacolheita.model.Jogo;

public class StartOperacao extends AbstractOperacao {

	public static Logger logger = LoggerFactory.getLogger(StartOperacao.class);
	
	/**
	 * ENVIAR A MENSAGEM DE INFORMACAO SOBRE O BOT.
	 */
	@Override
	public void execute(MessageCreateEvent event) {
		logger.info("INICIANDO OPERACAO");
		
		JogoDAO jogoDAO = new JogoDAO();
		
		HistoricoJogoDAO historicoJogoDAO = new HistoricoJogoDAO();
		historicoJogoDAO.setEntityManager(jogoDAO.getEntityManager());
		
		String mensagem;
		
		try {
			jogoDAO.beginTransaction();
			
			Jogo usuarioOpereacao = new Jogo(event);

			if(!jogoDAO.existeJogoStatusJogadorServer(usuarioOpereacao)) {
				logger.debug("Novo jogo. Jogador {} server {}",usuarioOpereacao.getJogador(),usuarioOpereacao.getServidor());
				
				mensagem = "Iniciando Jogo :deciduous_tree: \n";
				
				// Criar um novo número.
				HistoricoJogo historicoJogo = new HistoricoJogo(usuarioOpereacao);
				
				mensagem = mensagem + " Número atual: "+historicoJogo.getNumero()+" \nO proximo é !!maior ou !!menor?";
							
				jogoDAO.save(usuarioOpereacao);
				historicoJogo.aumentarSequencia();
				historicoJogoDAO.save(historicoJogo);
				
			}else {
				logger.debug("Já existe um jogo em aberto. Jogador {} server {}",usuarioOpereacao.getJogador(),usuarioOpereacao.getServidor());
				
				//buscar o ultimo jogo
				usuarioOpereacao = jogoDAO.buscarPorJogadorJogoAberto(usuarioOpereacao);
				
				HistoricoJogo historicoJogo = historicoJogoDAO.buscarUltimoMovimento(usuarioOpereacao);
				if(historicoJogo == null) {
					historicoJogo = new HistoricoJogo(usuarioOpereacao);
					
					historicoJogo.aumentarSequencia();
					historicoJogoDAO.save(historicoJogo);
				}
				
				mensagem = "Já existe um JOGO aberto\nNumero atual: "+ historicoJogo.getNumero()  +"\nO proximo é !!maior ou !!menor? \nDigite *!!cancelar* para fechar todos os jogos";
				
			}
			
			event.getChannel().sendMessage(mensagem);
			jogoDAO.commit();
			
		} catch (Exception e) {
			jogoDAO.rollBack();
			logger.error(e.getMessage());
	
		}finally {
			jogoDAO.close();
		}
		
		logger.info("FINALIZANDO OPERACAO");
	}

}
