package br.com.luaazul.deusacolheita.view.operacao;

import org.javacord.api.event.message.MessageCreateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.luaazul.deusacolheita.view.AbstractOperacao;
import br.com.luaazul.deusacolheita.dao.model.HistoricoJogoDAO;
import br.com.luaazul.deusacolheita.dao.model.JogoDAO;
import br.com.luaazul.deusacolheita.model.HistoricoJogo;
import br.com.luaazul.deusacolheita.model.Jogo;
import br.com.luaazul.deusacolheita.model.enuns.OpcoesJogo;

public class MaiorOperacao extends AbstractOperacao {

	public static Logger logger = LoggerFactory.getLogger(MaiorOperacao.class);
	
	/**
	 * ENVIAR A MENSAGEM DE INFORMACAO SOBRE O BOT.
	 */
	@Override
	public void execute(MessageCreateEvent event) {
		logger.info("INICIANDO OPERACAO");
		
		JogoDAO jogoDAO = new JogoDAO();
		
		HistoricoJogoDAO historicoJogoDAO = new HistoricoJogoDAO();
		historicoJogoDAO.setEntityManager(jogoDAO.getEntityManager());
		
		String mensagem = "";
		
		try {
			jogoDAO.beginTransaction();
			
			Jogo usuarioOpereacao = new Jogo(event);

			if(jogoDAO.existeJogoStatusJogadorServer(usuarioOpereacao)) {
				
				logger.debug("Inicinado novo movimento");
				//buscar o ultimo jogo
				usuarioOpereacao = jogoDAO.buscarPorJogadorJogoAberto(usuarioOpereacao);
				
				HistoricoJogo historicoJogoOLD = historicoJogoDAO.buscarUltimoMovimento(usuarioOpereacao);
				historicoJogoOLD.setOpcao(OpcoesJogo.MAIOR);
				
				historicoJogoDAO.update(historicoJogoOLD);
				
				HistoricoJogo historicoJogoNEW = new HistoricoJogo(historicoJogoOLD);
				historicoJogoNEW.gerarNumero(usuarioOpereacao.getDificuldade());
				
				String resultado = "";
				
				if(historicoJogoNEW.getNumero() >= historicoJogoOLD.getNumero()) {
					resultado = "\nParabens!! Vc ganhou 1 ponto!";
					resultado = "\nO proximo é !!maior ou !!menor?";
					usuarioOpereacao.aumentarPonto();
				}else {
					resultado = "\nOH NO perdeu irmão";
					usuarioOpereacao.perdeuJogo();
				}
				
				mensagem = mensagem + " Número Anteiror: "+historicoJogoOLD.getNumero()+"\nVc escolheu a opcao MAIOR\nNovo número é "+historicoJogoNEW.getNumero() +resultado;
				
				jogoDAO.update(usuarioOpereacao);
				historicoJogoNEW.aumentarSequencia();
				historicoJogoDAO.save(historicoJogoNEW);
				
			}else {
				logger.debug("Não existe jogo aberto para esse jogador. Jogador {} server {}",usuarioOpereacao.getJogador(),usuarioOpereacao.getServidor());
				mensagem = "Não tem um jogo em andamento, para iniciar um use !!start";
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
