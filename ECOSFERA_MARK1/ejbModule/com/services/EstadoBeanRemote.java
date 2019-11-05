package com.services;

import java.util.List;

import javax.ejb.Remote;

import com.entities.Estado;
import com.exceptions.ServiciosException;

@Remote
public interface EstadoBeanRemote {
	void crear(Estado estado) throws ServiciosException;
	void actualizar(Estado estado) throws ServiciosException;
	void borrar(Long id) throws ServiciosException;
	List<Estado> obtenerTodos();
	List<Estado> obtenerTodos(String filtro);
	
}
