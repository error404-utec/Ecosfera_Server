package com.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.hibernate.engine.jdbc.connections.internal.UserSuppliedConnectionProviderImpl;

import com.entities.Caracteristica;
import com.entities.Perfil;
import com.entities.Permiso;
import com.entities.TipoObservacion;
import com.entities.Usuario;
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
	
	@Override
	public void eliminarPermisos(Perfil perfil, Permiso permiso) throws ServiciosException {
		try{
			perfil.eliminarPermiso(permiso);
   			perfil= em.merge(perfil);
   			em.flush();
   		}catch(PersistenceException e){
   			throw new ServiciosException("No se pudo eliminar el perfil");
   		}
		
	}
	
	@Override
	public Perfil obtenerPorID(Long id) {
		TypedQuery<Perfil> query = em.createQuery("select p from Perfil p WHERE p.id LIKE :id",Perfil.class).setParameter("id", id);
		return query.getSingleResult();
	}
	
	@Override
	public String controles_postCreate(Perfil perfil) {
		String error = "";
		TypedQuery<Perfil> query = em.createQuery("select p from Perfil p WHERE p.nombre LIKE :nombre",Perfil.class).setParameter("nombre", perfil.getNombre());
   		List<Perfil> lista = query.getResultList();
		for(Perfil elemento : lista) {
			if(elemento.getNombre().equalsIgnoreCase(perfil.getNombre())) {
				error= "El nombre ingresado ya existe en el sistema.";
				break;
			}
		}	
		return error;
	}


	@Override
	public String controles_preDelete(Perfil perfil) {
		String error = "";

		return error;
	}

}
