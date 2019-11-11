package com.services;

import java.util.List;

import javax.ejb.Remote;

import com.entities.Permiso;
import com.entities.TipoObservacion;
import com.exceptions.ServiciosException;

//cambio commit
@Remote
public interface TipoObservacionBeanRemote {
	
	void crear(TipoObservacion tipoObservacion) throws ServiciosException;
	void actualizar(TipoObservacion tipoObservacion) throws ServiciosException;
	void borrar(Long id) throws ServiciosException;
	List<TipoObservacion> obtenerTodos();
	List<TipoObservacion> obtenerTodos(String filtro);
	TipoObservacion obtenerporID(Long id);
	void crearOModificar(TipoObservacion tipoObservacion) throws ServiciosException;
	String controles_postCreate(TipoObservacion tipoObservacion);
	String controles_preDelete(TipoObservacion tipoObservacion);

}
