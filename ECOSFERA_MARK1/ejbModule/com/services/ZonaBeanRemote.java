package com.services;

import java.util.List;

import javax.ejb.Remote;

import com.entities.Zona;
import com.exceptions.ServiciosException;

@Remote
public interface ZonaBeanRemote {
	void crear(Zona zona) throws ServiciosException;
	void actualizar(Zona zona) throws ServiciosException;
	void borrar(Long id) throws ServiciosException;
	List<Zona> obtenerTodos();
	List<Zona> obtenerTodos(String filtro);
	public Zona obtenerporID(Long id);
	String controles_postCreate(Zona zona);
	String controles_preDelete(Zona zona);
	String controles_postModify(Zona zona);
}
