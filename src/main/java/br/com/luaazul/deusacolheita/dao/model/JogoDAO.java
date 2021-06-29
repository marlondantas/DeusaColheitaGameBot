package br.com.luaazul.deusacolheita.dao.model;

import javax.persistence.Query;

import br.com.luaazul.deusacolheita.dao.AbstractDAO;
import br.com.luaazul.deusacolheita.model.Jogo;
import br.com.luaazul.deusacolheita.model.enuns.StatusJogo;


public class JogoDAO extends AbstractDAO<Integer, Jogo>{

	private final String BUSCAR_JOGO_ABERTO = "SELECT J FROM Jogo J WHERE J.jogador =:jogador AND J.servidor =:servidor AND J.status = '0' ";
	
	private final String BUSCAR_JOGO = "SELECT J FROM Jogo J WHERE J.jogador=:jogador AND J.servidor=:servidor AND J.status=:status ";
	
	private final String BUSCAR_JOGO_COUNT = "SELECT COUNT(J.id) FROM Jogo J WHERE J.jogador=:jogador AND J.servidor=:servidor AND J.status=:status ";

	private final String BUSCAR_ULTIMO_JOGO = "SELECT J FROM Jogo J WHERE J.jogador =:jogador AND J.servidor =:servidor AND J.status = '0' ";
	
	public JogoDAO() {
        super();
    }
	
	public boolean existeJogoStatusJogadorServer(Jogo jogador) {
		Query query = entityManager.createQuery(BUSCAR_JOGO_COUNT);
		
		query.setParameter("jogador",jogador.getJogador());
		query.setParameter("servidor",jogador.getServidor());
		query.setParameter("status",StatusJogo.ATIVO);
				
		long resultado = (long) query.getSingleResult();
		
		if(resultado <= 0l) {
			return false;
		}
		
		return true;
	}
	
	
	public Jogo buscarPorJogadorJogoAberto(Jogo jogador) {
		
		Query query = entityManager.createQuery(BUSCAR_JOGO, Jogo.class);
		
		query.setParameter("jogador",jogador.getJogador());
		query.setParameter("servidor",jogador.getServidor());
		query.setParameter("status",StatusJogo.ATIVO);
				
		return (Jogo) query.getSingleResult();
		
	}
	

}
