package br.com.luaazul.deusacolheita.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;


public abstract class AbstractDAO<PK, T> {

	public EntityManager entityManager;
	
	protected AbstractDAO() {	
		this.entityManager = GeradorManagerFactory.getEntityManager();
	}
	
	protected AbstractDAO(boolean naoCriar) {	
	}
	
	protected AbstractDAO(Class<T> persistedClass) {
		this();
		this.entityManager = GeradorManagerFactory.getEntityManager();
	}
	
	@SuppressWarnings("unchecked")
	public T getById(PK pk) {
        return (T) this.entityManager.find(getTypeClass(), pk);
    }
	
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
        return this.entityManager.createQuery(("FROM " + getTypeClass().getName()))
                .getResultList();
    }
 
    public void save(T entity) {
    	this.entityManager.persist(entity);
    }
 
    public void update(T entity) {
    	entity = this.entityManager.merge(entity);
    }
 
    public void delete(T entity) {
    	this.entityManager.remove(entity);
    }
 
    private Class<?> getTypeClass() {
        Class<?> clazz = (Class<?>) ((ParameterizedType) this.getClass()
                .getGenericSuperclass()).getActualTypeArguments()[1];
        return clazz;
    }
    
	/**
	 * CAMADA SERVICE
	 */
	public void beginTransaction(){
		this.entityManager.getTransaction().begin();
    }
     
    public void commit(){
    	this.entityManager.getTransaction().commit();
    }
    
    /**
     * THIS METHOD NEEDS TO BE ALWAYS CALLED
     */
    public void close(){
    	this.entityManager.close();
    }
     
    public void rollBack(){
    	this.entityManager.getTransaction().rollback();
    }
     
    public EntityManager getEntityManager(){
        return entityManager;
    }
    
    public void setEntityManager(EntityManager entityManager) {
    	this.entityManager = entityManager;
    }
    
}
