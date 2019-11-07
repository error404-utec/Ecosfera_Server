package com.services;


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Departamento;
import com.entities.TipoDocumento;
import com.exceptions.ServiciosException;


/**
 * Session Bean implementation class TipoDocumentoBean
 */
@Stateless
public class TipoDocumentoBean implements TipoDocumentoBeanRemote {
	@PersistenceContext
	private EntityManager em;


	/**
     * Default constructor. 
     */
    public TipoDocumentoBean() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
	public void crearOModificar(TipoDocumento tipoDocumento) throws ServiciosException {
    	System.out.println(tipoDocumento.getId());
    	if (tipoDocumento.getId()!= 0) {
    		actualizar(tipoDocumento);
    	}
		
	}

	@Override
	public void crear(TipoDocumento tipoDocumento) throws ServiciosException {
		try{
			em.persist(tipoDocumento);
			em.flush();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo crear el tipo de documento");
		}	
		
	}
	
	@Override
	public void actualizar(TipoDocumento tipoDocumento) throws ServiciosException {
		try{
			System.out.println("1- " + tipoDocumento.getId());
			em.merge(tipoDocumento);
			em.flush();
			System.out.println("2- " + tipoDocumento.getId());
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo actualizar el tipo de documento");
		}
	}

	@Override
	public void borrar(Long id) throws ServiciosException {
		try{
			TipoDocumento tipoDocumento = em.find(TipoDocumento.class, id);
			em.remove(tipoDocumento);
			em.flush();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo borrar el tipo de documento");
		}
	}
	
	

	@Override
	public List<TipoDocumento> obtenerTodos() {
		TypedQuery<TipoDocumento> query = em.createQuery("select d from TipoDocumento d",TipoDocumento.class);
		return query.getResultList();
	}

	@Override
	public List<TipoDocumento> obtenerTodos(String filtro) {
		TypedQuery<TipoDocumento> query = em.createQuery("select t from TipoDocumento t WHERE t.nombre LIKE :nombre",TipoDocumento.class).setParameter("nombre", filtro);
		return query.getResultList();

	}

	@Override
	public TipoDocumento obtenerporID(Long id) {
		TypedQuery<TipoDocumento> query = em.createQuery("select t from TipoDocumento t WHERE t.id = :id",TipoDocumento.class).setParameter("id", id);
		return query.getSingleResult();
	}

	@Override
	public TipoDocumento obtenerPorNombre(String nombre) {
		TypedQuery<TipoDocumento> query = em.createQuery("select t from TipoDocumento t WHERE t.nombre = :nombre",TipoDocumento.class).setParameter("nombre", nombre);
		return query.getSingleResult();
	}

	
}
