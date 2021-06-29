package br.com.luaazul.deusacolheita.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GeradorManagerFactory {

	public static EntityManagerFactory factory = Persistence.createEntityManagerFactory("DeusaColheita");
	
	public static void iniciarBanco() {
		
	}
	
	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}
	
}
