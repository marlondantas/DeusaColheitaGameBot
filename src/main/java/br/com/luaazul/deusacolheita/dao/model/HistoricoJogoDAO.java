package br.com.luaazul.deusacolheita.dao.model;

import javax.persistence.Query;

import br.com.luaazul.deusacolheita.dao.AbstractDAO;
import br.com.luaazul.deusacolheita.model.HistoricoJogo;
import br.com.luaazul.deusacolheita.model.Jogo;

public class HistoricoJogoDAO extends AbstractDAO<Integer, HistoricoJogo> {

	private final String BUSCAR_ULTIMO_MOVIMENTO = "select  hist from HistoricoJogo hist where hist.jogo = :jogo and hist.sequencia = (select max(hist2.sequencia) from HistoricoJogo hist2 where hist2.jogo = :jogo ) ";
	
	public HistoricoJogoDAO() {
		super(false);
	}
	
	
	
	
	public HistoricoJogo buscarUltimoMovimento(Jogo jogador) {
		Query query = entityManager.createQuery(BUSCAR_ULTIMO_MOVIMENTO, HistoricoJogo.class);
		
		query.setParameter("jogo", jogador);
		return (HistoricoJogo) query.getSingleResult();
	}
	
	
	public boolean apagarHistoricoID(Jogo jogador) {
		
		Query query = entityManager.createNativeQuery("DELETE FROM TBOD_HISTORICO_JOGO WHERE  ID_JOGO = :ID_JOGO");
		
		query.setParameter("ID_JOGO",jogador.getId());
		
		int result = query.executeUpdate();

		if( result > 0) {
			return true;
		}
		return false;
	}
	
	
}
