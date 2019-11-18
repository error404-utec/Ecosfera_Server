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

	@Override
	public Localidad obtenerPorID(Long id) {
		TypedQuery<Localidad> query = em.createQuery("select l from Localidad l WHERE l.id LIKE :id",Localidad.class).setParameter("id", id);
		return query.getSingleResult();
	}

	@Override
	public String controles_postCreate(Localidad localidad) {
		String error = "";
		TypedQuery<Localidad> query = em.createQuery("select l from Localidad l WHERE l.nombre LIKE :nombre",Localidad.class).setParameter("nombre", localidad.getNombre());
   		List<Localidad> lista = query.getResultList();
		for(Localidad elemento : lista) {
			if(elemento.getNombre().equalsIgnoreCase(localidad.getNombre())) {
				error= "El nombre ingresado ya existe en el sistema.";
				break;
			}
		}
		
		if(error.isEmpty()) {
			System.out.println();
			query = em.createQuery("select l from Localidad l WHERE l.codigo LIKE :codigo",Localidad.class).setParameter("codigo", localidad.getCodigo());
	   		lista.removeAll(lista);
			lista = query.getResultList();
			
			for(Localidad elemento : lista) {
				error= "El código ingresado ya existe en el sistema.";
				break;
			}
		}
		
		return error;
	}


	
	@Override
	public String controles_preDelete(Localidad localidad) {
		String error = "";
		/*
		TypedQuery<Localidad> query = em.createQuery("select l from Localidad l WHERE l.id LIKE :id",Departamento.class).setParameter("id", departamento.getId());
		Localidad localidad2 = query.getSingleResult();
   		List<Localidad> colloc = localidad2.getLocalidades();
		if (!colloc.isEmpty()) {
			error= "No se puede eliminar el departamento, cuenta con localidades asociadas.";
		}
		*/
		return error;
	}

	@Override
	public String controles_postModify(Localidad localidad) {
		String error = "";
		TypedQuery<Localidad> query = em.createQuery("select l from Localidad l WHERE l.nombre LIKE :nombre",Localidad.class).setParameter("nombre", localidad.getNombre());
   		List<Localidad> lista = query.getResultList();
		for(Localidad elemento : lista) {
			if(elemento.getNombre().equalsIgnoreCase(localidad.getNombre()) && elemento.getId()!= localidad.getId()) {
				error= "El nombre ingresado ya existe en el sistema.";
				break;
			}
		}
		return error;
	}

}
