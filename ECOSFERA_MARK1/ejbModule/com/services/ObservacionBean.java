package com.services;

import com.entities.Localidad;
import com.entities.Observacion;
import com.entities.TipoDocumento;
import com.entities.Usuario;
import com.exceptions.ServiciosException;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Stateless
@LocalBean
public class ObservacionBean implements ObservacionBeanRemote {
    @PersistenceContext
    private EntityManager em;

    public ObservacionBean() {
    }

    @Override
    public void crear(Observacion observacion) throws ServiciosException {
        try {
            em.persist(observacion);
            em.flush();
        } catch (PersistenceException e) {
            throw new ServiciosException("No se pudo crear la observación");
        }
    }

    @Override
    public void borrar(Long id) throws ServiciosException {
        try {
            Observacion observacion = em.find(Observacion.class, id);
            em.remove(observacion);
            em.flush();
        } catch (PersistenceException e) {
            throw new ServiciosException("No se pudo borrar la observación");
        }
    }

    @Override
    public void actualizar(Observacion observacion) throws ServiciosException {
        try {
            em.merge(observacion);
            em.flush();
        } catch (PersistenceException e) {
            throw new ServiciosException("No se pudo actualizar la observación.");
        }
    }

    @Override
    public List<Observacion> obtenerTodas() {
        TypedQuery<Observacion> query = em.createQuery("select o from Observacion o", Observacion.class);
        return query.getResultList();
    }

    @Override
    public List<Observacion> obtenerTodas(Date start, Date end) {
        TypedQuery<Observacion> query = em.createQuery("SELECT i FROM Imagenes i WHERE i.fecha BETWEEN :start AND :end", Observacion.class).setParameter("start", new Date(), TemporalType.DATE).setParameter("end", new Date(), TemporalType.DATE);
        return query.getResultList();
    }
    
    @Override
	public String controles_PreDeleteLocalidad(Localidad localiadad) {
		String respuesta = "";
		TypedQuery<Observacion> query = em.createQuery("select u from Observacion u WHERE u.localidad.id LIKE :id",Observacion.class).setParameter("id", localiadad.getId());
		List<Observacion> col = query.getResultList();
		for (Observacion us: col) {
			respuesta = "La localidad se encuentra asociado a una observacion.";		
		}			
		return respuesta;
	}
}
