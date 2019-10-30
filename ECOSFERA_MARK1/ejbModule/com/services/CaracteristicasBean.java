package com.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Caracteristica;
import com.entities.TipoObservacion;
import com.exceptions.ServiciosException;

/**
 * Session Bean implementation class CaracteristicasBean
 */
@Stateless
public class CaracteristicasBean implements CaracteristicasBeanRemote {
	@PersistenceContext
	private EntityManager em;
    
    public CaracteristicasBean() {
     
    }
    
    @Override
	public void crearOModificar(Caracteristica caracteristica) throws ServiciosException {
    	System.out.println(caracteristica.getId());
    	if (caracteristica.getId()!= 0) {
    		actualizar(caracteristica);
    	}
	}

	@Override
	public void crear(Caracteristica caracteristica) throws ServiciosException {
		try{
			em.persist(caracteristica);
			em.flush();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo crear la característica");
		}
	}

	@Override
	public void actualizar(Caracteristica caracteristica) throws ServiciosException {
		try{
   			em.merge(caracteristica);
   			em.flush();
   		}catch(PersistenceException e){
   			throw new ServiciosException("No se pudo actualizar la Característica");
   		}
	}

	@Override
	public void borrar(Long id) throws ServiciosException {
		try{
			Caracteristica caracteristica = em.find(Caracteristica.class, id);
			em.remove(caracteristica);
			em.flush();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo borrar la Característica");
		}
	}

	@Override
	public List<Caracteristica> obtenerTodos() {
		TypedQuery<Caracteristica> query = em.createQuery("select c from Caracteristica c",Caracteristica.class);
		return query.getResultList();
	}

	@Override
	public List<Caracteristica> obtenerTodos(String filtro) {
		TypedQuery<Caracteristica> query = em.createQuery("select c from Caracteristica c WHERE c.nombre LIKE :nombre",Caracteristica.class).setParameter("nombre", filtro);
		return query.getResultList();

	}

	@Override
	public Caracteristica obtenerporID(Long id) {
		TypedQuery<Caracteristica> query = em.createQuery("select c from Caracteristica c WHERE c.id = :id",Caracteristica.class).setParameter("id", id);
		return query.getSingleResult();
	}

	

}
