package br.com.luaazul.deusacolheita.controller.operacao;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.luaazul.deusacolheita.dao.model.HistoricoJogoDAO;
import br.com.luaazul.deusacolheita.dao.model.JogoDAO;
import br.com.luaazul.deusacolheita.model.Jogo;

public class ApagarOpereacaoService {

	public static Logger logger = LoggerFactory.getLogger(ApagarOpereacaoService.class);
	
	public ApagarOpereacaoService(){
		
	}
	
	
	public boolean deletarServidor(Jogo jogador) {
		boolean saida = false;
		
		JogoDAO jogoDAO = new JogoDAO();

		HistoricoJogoDAO historicoJogoDAO = new HistoricoJogoDAO();
		historicoJogoDAO.setEntityManager(jogoDAO.getEntityManager());
		
		
		try {

			jogoDAO.beginTransaction();
			
			List<Jogo> jogos = jogoDAO.buscarTodosJogosServidor(jogador);
			
			for (Jogo jogo :jogos) {
				if(historicoJogoDAO.apagarHistoricoID(jogo)) {
					logger.info("HISTORICO APAGADO COM SUCESSO");
				}else {
					return false;
				}				
			}
			
			jogoDAO.apagarJogadorServidor(jogador);
			
			jogoDAO.commit();
			saida = true;
		} catch (Exception e) {
			jogoDAO.rollBack();
			logger.error(e.getMessage());
		}
		finally {
			jogoDAO.close();
		}
		
		return saida;
	}
	
	public boolean deletarTudoDados(Jogo jogador) {
		boolean saida = false;
		
		JogoDAO jogoDAO = new JogoDAO();

		HistoricoJogoDAO historicoJogoDAO = new HistoricoJogoDAO();
		historicoJogoDAO.setEntityManager(jogoDAO.getEntityManager());
		
		
		try {

			jogoDAO.beginTransaction();
			
			List<Jogo> jogos = jogoDAO.buscarTodosJogos(jogador);
			
			for (Jogo jogo :jogos) {
				if(historicoJogoDAO.apagarHistoricoID(jogo)) {
					logger.info("HISTORICO APAGADO COM SUCESSO");
				}else {
					return false;
				}				
			}
			
			jogoDAO.apagarJogadorServidor(jogador);
			
			jogoDAO.commit();
			saida = true;
		} catch (Exception e) {
			jogoDAO.rollBack();
			logger.error(e.getMessage());
		}
		finally {
			jogoDAO.close();
		}
		
		return saida;
	}
	
	
}
