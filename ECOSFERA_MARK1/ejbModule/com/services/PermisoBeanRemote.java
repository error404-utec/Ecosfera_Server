package com.services;

import java.util.List;

import javax.ejb.Remote;

import com.entities.Permiso;
import com.exceptions.ServiciosException;

@Remote
public interface PermisoBeanRemote {
	void crear(Permiso permiso) throws ServiciosException;
	void actualizar(Permiso permiso) throws ServiciosException;
	void borrar(Long id) throws ServiciosException;
	List<Permiso> obtenerTodos();
	List<Permiso> obtenerTodos(String filtro);
	Permiso obtenerporID(Long id);
}
