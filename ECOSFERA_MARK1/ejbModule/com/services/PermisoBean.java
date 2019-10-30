package com.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Estado;
import com.entities.Permiso;
import com.entities.TipoDocumento;
import com.exceptions.ServiciosException;

/**
 * Session Bean implementation class PermisoBean
 */
@Stateless
@LocalBean
public class PermisoBean implements PermisoBeanRemote {
	@PersistenceContext
	private EntityManager em;

    /**
     * Default constructor. 
     */
    public PermisoBean() {
        // TODO Auto-generated constructor stub
    }

    @Override
   	public void crear(Permiso permiso) throws ServiciosException {
   		try{
   			em.persist(permiso);
   			em.flush();
   		}catch(PersistenceException e){
   			throw new ServiciosException("No se pudo crear el permiso");
   		}	
   		
   	}
   	
   	@Override
   	public void actualizar(Permiso permiso) throws ServiciosException {
   		try{
   			em.merge(permiso);
   			em.flush();
   		}catch(PersistenceException e){
   			throw new ServiciosException("No se pudo actualizar el permiso");
   		}
   	}

   	@Override
   	public void borrar(Long id) throws ServiciosException {
   		try{
   			Permiso permiso= em.find(Permiso.class, id);
   			em.remove(permiso);
   			em.flush();
   		}catch(PersistenceException e){
   			throw new ServiciosException("No se pudo borrar el permiso");
   		}
   	}

   	@Override
   	public List<Permiso> obtenerTodos() {
   		TypedQuery<Permiso> query = em.createQuery("select p from Permiso p",Permiso.class);
   		return query.getResultList();
   	}

   	@Override
   	public List<Permiso> obtenerTodos(String filtro) {
   		TypedQuery<Permiso> query = em.createQuery("select p from Permiso p WHERE p.funcionalidad LIKE :funcionalidad",Permiso.class).setParameter("funcionalidad", filtro);
   		return query.getResultList();

   	}
   	
   	@Override
	public Permiso obtenerporID(Long id) {
		TypedQuery<Permiso> query = em.createQuery("select p from Permiso p WHERE p.id LIKE :id",Permiso.class).setParameter("id", id);
		return query.getSingleResult();
	}

}
