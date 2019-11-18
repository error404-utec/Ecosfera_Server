package com.services;

import java.util.List;

import javax.ejb.Remote;

import com.entities.Perfil;
import com.entities.Permiso;
import com.exceptions.ServiciosException;

@Remote
public interface PerfilesBeanRemote {
	void crear(Perfil perfil) throws ServiciosException;
	void actualizar(Perfil perfil) throws ServiciosException;
	void borrar(Long id) throws ServiciosException;
	List<Perfil> obtenerTodos();
	List<Perfil> obtenerTodos(String filtro);
	void asignarPermisos(Perfil perfil, Permiso permiso)throws ServiciosException;
	Perfil obtenerPorID(Long id);
	String controles_postCreate(Perfil perfil);
	String controles_preDelete(Perfil perfil);
	void eliminarPermisos(Perfil perfil, Permiso permiso) throws ServiciosException;
}
