package com.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Departamento;
import com.exceptions.ServiciosException;

/**
 * Session Bean implementation class DepartamentoBeans
 */
@Stateless
@LocalBean
public class DepartamentoBean implements DepartamentoBeanRemote {
	@PersistenceContext
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public DepartamentoBean() {
        // TODO Auto-generated constructor stub
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
}
