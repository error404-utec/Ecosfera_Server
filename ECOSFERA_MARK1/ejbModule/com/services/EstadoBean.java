package com.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Estado;
import com.entities.TipoDocumento;
import com.exceptions.ServiciosException;

/**
 * Session Bean implementation class EstadoBean
 */
@Stateless
@LocalBean
public class EstadoBean implements EstadoBeanRemote {
	@PersistenceContext
	private EntityManager em;

    public EstadoBean() {
    	

    }

    @Override
	public void crear(Estado estado) throws ServiciosException {
		try{
			em.persist(estado);
			em.flush();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo crear el estado");
		}	
		
	}
	
	@Override
	public void actualizar(Estado estado) throws ServiciosException {
		try{
			em.merge(estado);
			em.flush();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo actualizar el estado");
		}
	}

	@Override
	public void borrar(Long id) throws ServiciosException {
		try{
			Estado estado= em.find(Estado.class, id);
			em.remove(estado);
			em.flush();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo borrar el estado");
		}
	}

	@Override
	public List<Estado> obtenerTodos() {
		TypedQuery<Estado> query = em.createQuery("select e from Estado e",Estado.class);
		return query.getResultList();
	}

	@Override
	public List<Estado> obtenerTodos(String filtro) {
		TypedQuery<Estado> query = em.createQuery("select e from Estado e WHERE e.nombre LIKE :nombre",Estado.class).setParameter("nombre", filtro);
		return query.getResultList();

	}

	@Override
	public Estado obtenerPorNombre(String nombre) {
		TypedQuery<Estado> query = em.createQuery("select e from Estado e WHERE e.nombre LIKE :nombre",Estado.class).setParameter("nombre", nombre);
		return query.getSingleResult();
	}

}
