package com.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Departamento;
import com.entities.Localidad;
import com.exceptions.ServiciosException;

/**
 * Session Bean implementation class DepartamentoBeans
 */
@Stateless
@LocalBean
public class DepartamentoBean implements DepartamentoBeanRemote {
	@PersistenceContext
	private EntityManager em;

    public DepartamentoBean() {
    }
    
    @Override
   	public void crear(Departamento departamento) throws ServiciosException {
   		try{
   			em.persist(departamento);
   			em.flush();
   		}catch(PersistenceException e){
   			throw new ServiciosException("No se pudo crear el departamento");
   		}	
   		
   	}
   	
   	@Override
   	public void actualizar(Departamento departamento) throws ServiciosException {
   		try{
   			em.merge(departamento);
   			em.flush();
   		}catch(PersistenceException e){
   			throw new ServiciosException("No se pudo actualizar el departamento");
   		}
   	}

   	@Override
   	public void borrar(Long id) throws ServiciosException {
   		try{
   			Departamento departamento= em.find(Departamento.class, id);
   			em.remove(departamento);
   			em.flush();
   		}catch(PersistenceException e){
   			throw new ServiciosException("No se pudo borrar el departamento");
   		}
   	}

   	@Override
   	public List<Departamento> obtenerTodos() {
   		TypedQuery<Departamento> query = em.createQuery("select d from Departamento d",Departamento.class);
   		return query.getResultList();
   	}

   	@Override
   	public List<Departamento> obtenerTodos(String filtro) {
   		TypedQuery<Departamento> query = em.createQuery("select d from Departamento d WHERE d.nombre LIKE :nombre",Departamento.class).setParameter("nombre", filtro);
   		return query.getResultList();

   	}
   	
   	@Override
	public Departamento obtenerporID(Long id) {
		TypedQuery<Departamento> query = em.createQuery("select d from Departamento d WHERE d.id LIKE :id",Departamento.class).setParameter("id", id);
		return query.getSingleResult();
	}
	@Override
	public String controles_postCreate(Departamento departamento) {
		String error = "";
		TypedQuery<Departamento> query = em.createQuery("select d from Departamento d WHERE d.nombre LIKE :nombre",Departamento.class).setParameter("nombre", departamento.getNombre());
   		List<Departamento> lista = query.getResultList();
		for(Departamento elemento : lista) {
			if(elemento.getNombre().equalsIgnoreCase(departamento.getNombre())) {
				error= "El nombre ingresado ya existe en el sistema.";
				break;
			}
		}
		
		if(error.isEmpty()) {
			System.out.println();
			query = em.createQuery("select d from Departamento d WHERE d.codigo LIKE :codigo",Departamento.class).setParameter("codigo", departamento.getCodigo());
	   		lista.removeAll(lista);
			lista = query.getResultList();
			for(Departamento elemento : lista) {
				error= "El código ingresado ya existe en el sistema.";
				break;
			}
		}
		
		return error;
	}


	
	@Override
	public String controles_preDelete(Departamento departamento) {
		String error = "";
		TypedQuery<Departamento> query = em.createQuery("select d from Departamento d WHERE d.id LIKE :id",Departamento.class).setParameter("id", departamento.getId());
		Departamento departamento2 = query.getSingleResult();
   		List<Localidad> colloc = departamento2.getLocalidades();
		if (!colloc.isEmpty()) {
			error= "No se puede eliminar el departamento, cuenta con localidades asociadas.";
		}
		return error;
	}

	@Override
	public String controles_postModify(Departamento departamento) {
		String error = "";
		TypedQuery<Departamento> query = em.createQuery("select d from Departamento d WHERE d.nombre LIKE :nombre",Departamento.class).setParameter("nombre", departamento.getNombre());
   		List<Departamento> lista = query.getResultList();
		for(Departamento elemento : lista) {
			if(elemento.getNombre().equalsIgnoreCase(departamento.getNombre()) && elemento.getId()!= departamento.getId()) {
				error= "El nombre ingresado ya existe en el sistema.";
				break;
			}
		}
		return error;
	}
}
