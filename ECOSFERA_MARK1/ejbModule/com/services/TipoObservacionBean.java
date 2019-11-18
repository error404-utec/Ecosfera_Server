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


@Stateless
public class TipoObservacionBean implements TipoObservacionBeanRemote {
	@PersistenceContext
	private EntityManager em;
    public TipoObservacionBean() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
	public void crearOModificar(com.entities.TipoObservacion tipoObservacion) throws ServiciosException {
    	System.out.println(tipoObservacion.getId());
    	if (tipoObservacion.getId()!= 0) {
    		actualizar(tipoObservacion);
    	}
	}


	@Override
	public void crear(com.entities.TipoObservacion tipoObservacion) throws ServiciosException {
		try{
			em.persist(tipoObservacion);
			em.flush();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo crear el tipo de Observación");
		}
	}
	
	@Override
   	public void actualizar(TipoObservacion tipoObservacion) throws ServiciosException {
   		try{
   			em.merge(tipoObservacion);
   			em.flush();
   		}catch(PersistenceException e){
   			throw new ServiciosException("No se pudo actualizar el tipo de observaci�n");
   		}
   	}

	@Override
	public void borrar(Long id) throws ServiciosException {
		// TODO Auto-generated method stub
		try{
			TipoObservacion tipoObservacion = em.find(TipoObservacion.class, id);
			em.remove(tipoObservacion);
			em.flush();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo borrar el tipo de Observación");
		}
	}

	@Override
	public List<TipoObservacion> obtenerTodos() {
		TypedQuery<TipoObservacion> query = em.createQuery("select o from TipoObservacion o",TipoObservacion.class);
		return query.getResultList();
	}

	@Override
	public List<TipoObservacion> obtenerTodos(String filtro) {
		TypedQuery<TipoObservacion> query = em.createQuery("select o from TipoObservacion o WHERE o.nombre LIKE :nombre",TipoObservacion.class).setParameter("nombre", filtro);
		return query.getResultList();

	}

	@Override
	public TipoObservacion obtenerporID(Long id) {
		TypedQuery<TipoObservacion> query = em.createQuery("select o from TipoObservacion o WHERE o.id = :id",TipoObservacion.class).setParameter("id", id);
		return query.getSingleResult();
	}
	
	@Override
	public String controles_postCreate(TipoObservacion tipoObservacion) {
		String error = "";
		TypedQuery<TipoObservacion> query = em.createQuery("select o from TipoObservacion o WHERE o.nombre LIKE :nombre",TipoObservacion.class).setParameter("nombre", tipoObservacion.getNombre());
   		List<TipoObservacion> lista = query.getResultList();
		for(TipoObservacion elemento : lista) {
			if(elemento.getNombre().equalsIgnoreCase(tipoObservacion.getNombre())) {
				error= "El nombre ingresado ya existe en el sistema.";
				break;
			}
		}	
		return error;
	}


	@Override
	public String controles_preDelete(TipoObservacion tipoObservacion) {
		String error = "";
		TypedQuery<TipoObservacion> query = em.createQuery("select o from TipoObservacion o WHERE o.id = :id",TipoObservacion.class).setParameter("id", tipoObservacion.getId());
		TipoObservacion tipoObservacion2 = query.getSingleResult();
   		List<Caracteristica> colloc = tipoObservacion2.getCaracteristicas();
		if (!colloc.isEmpty()) {
			error= "No se puede eliminar el tipo de observacion, cuenta con caracteristicas asociadas.";
		}else {
			//Controlar contra observaciones.
		}
		return error;
	}

	

}
