package com.services;

import java.util.List;

import javax.ejb.Remote;

import com.entities.Departamento;
import com.exceptions.ServiciosException;

@Remote
public interface DepartamentoBeanRemote {
	void crear(Departamento Departamento) throws ServiciosException;
	void actualizar(Departamento Departamento) throws ServiciosException;
	void borrar(Long id) throws ServiciosException;
	List<Departamento> obtenerTodos();
	List<Departamento> obtenerTodos(String filtro);
	public Departamento obtenerporID(Long id);
	String controles_postCreate(Departamento departamento);
	String controles_preDelete(Departamento departamento);
	String controles_postModify(Departamento departamento);
}
