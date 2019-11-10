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
import com.sun.org.apache.xml.internal.serializer.ToUnknownStream;

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

	@Override
	public String controles_postCreate(Zona zona) {
		String error = "";
   		TypedQuery<Zona> query = em.createQuery("select z from Zona z WHERE z.nombre LIKE :nombre",Zona.class).setParameter("nombre", zona.getNombre());
   		List<Zona> lista = query.getResultList();
		for(Zona elemento : lista) {
			if(elemento.getNombre().equalsIgnoreCase(zona.getNombre())) {
				error= "El nombre ingresado ya existe en el sistema.";
				break;
			}
		}
		
		if(error.isEmpty()) {
			query = em.createQuery("select z from Zona z WHERE z.codigo LIKE :codigo",Zona.class).setParameter("codigo", zona.getCodigo());
	   		lista.removeAll(lista);
			lista = query.getResultList();
			for(Zona elemento : lista) {
				error= "El código ingresado ya existe en el sistema.";
				break;
			}
		}
		
		return error;
	}

	@Override
	public String controles_preDelete(Zona zona) {
		String error = "";
   		TypedQuery<Zona> query = em.createQuery("select z from Zona z WHERE z.id LIKE :id",Zona.class).setParameter("id", zona.getId());
   		Zona zona2 = query.getSingleResult();
   		List<Departamento> coldep = zona2.getDepartamentos();
		if (!coldep.isEmpty()) {
			error= "No se puede eliminar la zona, cuenta con departamentos asociados..";
		}
		return error;
	}

	@Override
	public String controles_postModify(Zona zona) {
		String error = "";
   		TypedQuery<Zona> query = em.createQuery("select z from Zona z WHERE z.nombre LIKE :nombre",Zona.class).setParameter("nombre", zona.getNombre());
   		List<Zona> lista = query.getResultList();
		for(Zona elemento : lista) {
			if(elemento.getNombre().equalsIgnoreCase(zona.getNombre()) && elemento.getId()!= zona.getId()) {
				error= "El nombre ingresado ya existe en el sistema.";
				break;
			}
		}
		return error;
	}


}
