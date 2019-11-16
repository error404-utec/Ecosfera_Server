package com.services;



import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.entities.Usuario;

/**
 * Session Bean implementation class IniciarSesionBean
 */
@Stateless
@LocalBean
public class IniciarSesionBean implements IniciarSesionBeanRemote {
	@PersistenceContext
	private EntityManager em;

    public IniciarSesionBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Usuario iniciar(String nombre, String Password) {
		TypedQuery<Usuario> query = em.createQuery("select u from Usuario u WHERE u.usuario LIKE :nombre",Usuario.class).setParameter("nombre", nombre);
		return query.getSingleResult();
	}

	@Override
	public String controles_PreInit(String nombre, String password) {
		String respuesta="";
		TypedQuery<Usuario> query = em.createQuery("select u from Usuario u WHERE u.usuario LIKE :nombre",Usuario.class).setParameter("nombre", nombre);
		List<Usuario> usuarios = query.getResultList();
		respuesta="Usuario incorrecto";
		for (Usuario user: usuarios) {
			respuesta = "";
			if(user.getUsuario().equalsIgnoreCase(nombre)) {
				if(!user.getPasswrd().equals(password)) {
					respuesta = "Contraseña Incorrecta";
				}
			}
		}
		return respuesta;
	}

}
