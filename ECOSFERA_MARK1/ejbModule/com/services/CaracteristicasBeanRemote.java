package com.services;

import java.util.List;

import javax.ejb.Remote;

import com.entities.Caracteristica;
import com.exceptions.ServiciosException;

@Remote
public interface CaracteristicasBeanRemote {

	void crear(Caracteristica caracteristica) throws ServiciosException;
	void actualizar(Caracteristica caracteristica) throws ServiciosException;
	void borrar(Long id) throws ServiciosException;
	List<Caracteristica> obtenerTodos();
	List<Caracteristica> obtenerTodos(String filtro);
	Caracteristica obtenerporID(Long id);
	void crearOModificar(Caracteristica caracteristica) throws ServiciosException;

}
