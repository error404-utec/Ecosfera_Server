package com.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Estado;
import com.entities.Usuario;
import com.exceptions.ServiciosException;

/**
 * Session Bean implementation class UsuarioBean
 */
@Stateless
@LocalBean
public class UsuarioBean implements UsuarioBeanRemote {
	@PersistenceContext
	private EntityManager em;
    /**
     * Default constructor. 
     */
	
    public UsuarioBean() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
   	public void crear(Usuario usuario) throws ServiciosException {
   		try{
   			em.persist(usuario);
   			em.flush();
   		}catch(PersistenceException e){
   			throw new ServiciosException("No se pudo crear el usuario");
   		}	
   		
   	}
   	
   	@Override
   	public void actualizar(Usuario usuario) throws ServiciosException {
   		try{
   			System.out.println(usuario.getId());
   			em.merge(usuario);
   			em.flush();
   		}catch(PersistenceException e){
   			throw new ServiciosException("No se pudo actualizar el usuario");
   		}
   	}

   	@Override
   	public void borrar(Long id) throws ServiciosException {
   		try{
   			Usuario usuario= em.find(Usuario.class, id);
   			em.remove(usuario);
   			em.flush();
   		}catch(PersistenceException e){
   			throw new ServiciosException("No se pudo borrar el usuario");
   		}
   	}

   	@Override
   	public List<Usuario> obtenerTodos() {
   		TypedQuery<Usuario> query = em.createQuery("select p from Usuario p",Usuario.class);
   		return query.getResultList();
   	}

   	@Override
   	public List<Usuario> obtenerTodos(String filtro) {
   		TypedQuery<Usuario> query = em.createQuery("select u from Usuario u WHERE u.nombre LIKE :nombre",Usuario.class).setParameter("nombre", filtro);
   		return query.getResultList();

   	}

	@Override
	public Usuario obtenerPorNomber(String nombre) {
   		TypedQuery<Usuario> query = em.createQuery("select u from Usuario u WHERE u.nombre LIKE :nombre",Usuario.class).setParameter("nombre", nombre);
   		return query.getSingleResult();
	}

	@Override
	public Usuario obtenerPorId(Long id) {
		TypedQuery<Usuario> query = em.createQuery("select u from Usuario u WHERE u.id LIKE :id",Usuario.class).setParameter("id", id);
   		return query.getSingleResult();
	}

	@Override
	public String controlarUnicidad(Usuario usuario) {
		
		String respuesta = "";
   		TypedQuery<Usuario> query = em.createQuery("select u from Usuario u WHERE u.usuario LIKE :usuario",Usuario.class).setParameter("usuario", usuario.getUsuario());
   		List<Usuario> col = query.getResultList();
   		for(Usuario ele: col) {
   			respuesta ="Nombre de usuario ya existe en el sistema.";
   		}
   		
   		TypedQuery<Usuario> query2 = em.createQuery("select u from Usuario u WHERE u.mail LIKE :mail",Usuario.class).setParameter("mail", usuario.getMail());
   		col.removeAll(col);
   		col = query2.getResultList();
   		for(Usuario ele: col) {
   			respuesta ="El mail ya se encuentra registrado en el sistema.";
   		}
		return respuesta;
	}
	
	public String controles_preModify(Usuario usuario) {
		
		String respuesta = "";
   		TypedQuery<Usuario> query = em.createQuery("select u from Usuario u WHERE u.usuario LIKE :usuario",Usuario.class).setParameter("usuario", usuario.getUsuario());
   		List<Usuario> col = query.getResultList();
   		for(Usuario ele: col) {
   			System.out.println(usuario.getId());
   			System.out.println(ele.getId());
   			System.out.println(ele.getNombre());
   			if(ele.getUsuario().equals(usuario.getUsuario()) && ele.getId()!=usuario.getId()){
   				respuesta ="Nombre de usuario ya existe en el sistema.";
   			}
   		}
   		
   		TypedQuery<Usuario> query2 = em.createQuery("select u from Usuario u WHERE u.mail LIKE :mail",Usuario.class).setParameter("mail", usuario.getMail());
   		col.removeAll(col);
   		col = query2.getResultList();
   		for(Usuario ele: col) {
   			System.out.println(usuario.getId());
   			System.out.println(ele.getId());
   			System.out.println(ele.getMail());
   			if(ele.getMail().equals(usuario.getMail()) && ele.getId()!=usuario.getId()){
   				respuesta ="El mail ya se encuentra registrado en el sistema.";
   			}
   		}
		return respuesta;
	}
	
	

}
