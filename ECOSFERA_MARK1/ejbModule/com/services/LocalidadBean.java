package com.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Localidad;
import com.exceptions.ServiciosException;

/**
 * Session Bean implementation class LocalidadBean
 */
@Stateless
@LocalBean
public class LocalidadBean implements LocalidadBeanRemote {
	@PersistenceContext
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public LocalidadBean() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
   	public void crear(Localidad localidad) throws ServiciosException {
   		try{
   			em.persist(localidad);
   			em.flush();
   		}catch(PersistenceException e){
   			throw new ServiciosException("No se pudo crear la localidad");
   		}	
   		
   	}
   	
   	@Override
   	public void actualizar(Localidad localidad) throws ServiciosException {
   		try{
   			em.merge(localidad);
   			em.flush();
   		}catch(PersistenceException e){
   			throw new ServiciosException("No se pudo actualizar la localidad");
   		}
   	}

   	@Override
   	public void borrar(Long id) throws ServiciosException {
   		try{
   			Localidad localidad= em.find(Localidad.class, id);
   			em.remove(localidad);
   			em.flush();
   		}catch(PersistenceException e){
   			throw new ServiciosException("No se pudo borrar la localidad");
   		}
   	}

   	@Override
   	public List<Localidad> obtenerTodos() {
   		TypedQuery<Localidad> query = em.createQuery("select l from Localidad l",Localidad.class);
   		return query.getResultList();
   	}

   	@Override
   	public List<Localidad> obtenerTodos(String filtro) {
   		TypedQuery<Localidad> query = em.createQuery("select l from Localidad l WHERE l.nombre LIKE :nombre",Localidad.class).setParameter("nombre", filtro);
   		return query.getResultList();
   	}

}
