package com.services;

import java.util.List;

import javax.ejb.Remote;

import com.entities.Localidad;
import com.exceptions.ServiciosException;

@Remote
public interface LocalidadBeanRemote {
	void crear(Localidad localidad) throws ServiciosException;
	void actualizar(Localidad localidad) throws ServiciosException;
	void borrar(Long id) throws ServiciosException;
	List<Localidad> obtenerTodos();
	List<Localidad> obtenerTodos(String filtro);
	public Localidad obtenerPorID(Long id);
}
