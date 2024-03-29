package com.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Perfil;
import com.entities.Permiso;
import com.exceptions.ServiciosException;

/**
 * Session Bean implementation class PerfilesBean
 */
@Stateless
@LocalBean
public class PerfilesBean implements PerfilesBeanRemote {
	@PersistenceContext
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public PerfilesBean() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
   	public void crear(Perfil perfil) throws ServiciosException {
   		try{
   			em.persist(perfil);
   			em.flush();
   		}catch(PersistenceException e){
   			throw new ServiciosException("No se pudo crear el perfil");
   		}	
   		
   	}
   	
   	@Override
   	public void actualizar(Perfil perfil) throws ServiciosException {
   		try{
   			em.merge(perfil);
   			em.flush();
   		}catch(PersistenceException e){
   			throw new ServiciosException("No se pudo actualizar el perfil");
   		}
   	}

   	@Override
   	public void borrar(Long id) throws ServiciosException {
   		try{
   			Perfil perfil= em.find(Perfil.class, id);
   			em.remove(perfil);
   			em.flush();
   		}catch(PersistenceException e){
   			throw new ServiciosException("No se pudo borrar el perfil");
   		}
   	}

   	@Override
   	public List<Perfil> obtenerTodos() {
   		TypedQuery<Perfil> query = em.createQuery("select p from Perfil p",Perfil.class);
   		return query.getResultList();
   	}

   	@Override
   	public List<Perfil> obtenerTodos(String filtro) {
   		TypedQuery<Perfil> query = em.createQuery("select p from Perfil p WHERE p.nombre LIKE :nombre",Perfil.class).setParameter("nombre", filtro);
   		return query.getResultList();

   	}

	@Override
	public void asignarPermisos(Perfil perfil, Permiso permiso) throws ServiciosException {
		try{
			perfil.asginarPermiso(permiso);
   			perfil= em.merge(perfil);
   			em.flush();
   		}catch(PersistenceException e){
   			throw new ServiciosException("No se pudo asignar el nuevo perfil");
   		}
		
	}

}
