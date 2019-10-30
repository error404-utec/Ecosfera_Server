package com.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Departamento;
import com.entities.Zona;
import com.exceptions.ServiciosException;

/**
 * Session Bean implementation class ZonaBean
 */
@Stateless
@LocalBean
public class ZonaBean implements ZonaBeanRemote {
	@PersistenceContext
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public ZonaBean() {
        // TODO Auto-generated constructor stub
    }

    @Override
   	public void crear(Zona zona) throws ServiciosException {
   		try{
   			em.persist(zona);
   			em.flush();
   		}catch(PersistenceException e){
   			throw new ServiciosException("No se pudo crear la zona");
   		}	
   		
   	}
   	
   	@Override
   	public void actualizar(Zona zona) throws ServiciosException {
   		System.out.println("MERGE");
   		try{
   			em.merge(zona);
   			em.flush();
   		}catch(PersistenceException e){
   			throw new ServiciosException("No se pudo actualizar la zona");
   		}
   	}

   	@Override
   	public void borrar(Long id) throws ServiciosException {
   		try{
   			Zona zona= em.find(Zona.class, id);
   			em.remove(zona);
   			em.flush();
   		}catch(PersistenceException e){
   			throw new ServiciosException("No se pudo borrar la zona");
   		}
   	}

   	@Override
   	public List<Zona> obtenerTodos() {
   		TypedQuery<Zona> query = em.createQuery("select z from Zona z",Zona.class);
   		return query.getResultList();
   	}

   	@Override
   	public List<Zona> obtenerTodos(String filtro) {
   		TypedQuery<Zona> query = em.createQuery("select z from Zona z WHERE z.nombre LIKE :nombre",Zona.class).setParameter("nombre", filtro);
   		return query.getResultList();

   	}
   	
   	@Override
	public Zona obtenerporID(Long id) {
		TypedQuery<Zona> query = em.createQuery("select z from Zona z WHERE z.id LIKE :id",Zona.class).setParameter("id", id);
		return query.getSingleResult();
	}


}
