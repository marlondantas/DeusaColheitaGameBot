package br.com.luaazul.deusacolheita.dao.model;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.luaazul.deusacolheita.dao.AbstractDAO;
import br.com.luaazul.deusacolheita.model.Servidor;

public class ServidorDAO extends AbstractDAO<Integer, Servidor> {

	private final String VERIFICA_PREFIXO = "SELECT ser FROM Servidor ser where ser.id = :id";
	
	private final String VERIFICA_PREFIXO_SERVIDOR = "SELECT ser FROM Servidor ser where ser.servidor = :idServidor";
	
	
	
	public ServidorDAO() {
		super();
	}

	public Servidor buscarServidorId(Servidor servidor) {
		
		Query query = entityManager.createQuery(VERIFICA_PREFIXO, Servidor.class);
		
		query.setParameter("id", servidor.getId());
		return (Servidor) query.getSingleResult();
	}
	
	public Servidor buscarServidorServidor(Servidor servidor) {
		
		Query query = entityManager.createQuery(VERIFICA_PREFIXO_SERVIDOR, Servidor.class);
		
		query.setParameter("idServidor", servidor.getIdServidor());
		
		try {
			return (Servidor) query.getSingleResult();
		} catch ( NoResultException nre ) {
			return null;
		}
	}
	
	public Servidor buscarServidorServidor(String servidor) {
		
		Query query = entityManager.createQuery(VERIFICA_PREFIXO_SERVIDOR, Servidor.class);
		
		query.setParameter("idServidor", servidor);
		
		try {
			return (Servidor) query.getSingleResult();
		} catch ( NoResultException nre ) {
			return null;
		}
	}
	
}

