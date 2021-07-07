package br.com.luaazul.deusacolheita.dao.model;

import java.util.List;

import javax.persistence.Query;

import br.com.luaazul.deusacolheita.dao.AbstractDAO;
import br.com.luaazul.deusacolheita.model.Jogo;
import br.com.luaazul.deusacolheita.model.enuns.StatusJogo;


public class JogoDAO extends AbstractDAO<Integer, Jogo>{

	private final String BUSCAR_JOGO_ABERTO = "SELECT J FROM Jogo J WHERE J.jogador =:jogador AND J.servidor =:servidor AND J.status = '0' ";
	
	private final String BUSCAR_JOGO = "SELECT J FROM Jogo J WHERE J.jogador=:jogador AND J.servidor=:servidor AND J.status=:status ";
	
	private final String BUSCAR_TODOS_JOGO_SERVIDOR = "SELECT J FROM Jogo J WHERE J.jogador=:jogador AND J.servidor=:servidor";
	
	private final String BUSCAR_TODOS_JOGO = "SELECT J FROM Jogo J WHERE J.jogador=:jogador";
	
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
	
	@SuppressWarnings("unchecked")
	public List<Jogo> buscarTodosJogosServidor(Jogo jogador){
	
		Query query = entityManager.createQuery(BUSCAR_TODOS_JOGO_SERVIDOR, Jogo.class);
		
		query.setParameter("jogador",jogador.getJogador());
		query.setParameter("servidor",jogador.getServidor());
				
		return (List<Jogo>) query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Jogo> buscarTodosJogos(Jogo jogador){
	
		Query query = entityManager.createQuery(BUSCAR_TODOS_JOGO, Jogo.class);
		
		query.setParameter("jogador",jogador.getJogador());
				
		return (List<Jogo>) query.getResultList();
	}
	
	
	public boolean apagarJogadorServidor(Jogo jogador) {
		
		Query query = entityManager.createNativeQuery("DELETE FROM TBOD_JOGO WHERE  DS_SERVIDOR = :DS_SERVIDOR AND DS_JOGADOR = :DS_JOGADOR");
		
		query.setParameter("DS_SERVIDOR",jogador.getServidor());
		query.setParameter("DS_JOGADOR",jogador.getJogador());
		
		int result = query.executeUpdate();

		if( result > 0) {
			return true;
		}
		return false;
	}
	

}
