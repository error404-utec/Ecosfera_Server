package com.services;

import java.util.List;

import javax.ejb.Remote;

import com.entities.TipoDocumento;
import com.exceptions.ServiciosException;

@Remote
public interface TipoDocumentoBeanRemote {
	void crear(TipoDocumento tipoDocumento) throws ServiciosException;
	void actualizar(TipoDocumento tipoDocumento) throws ServiciosException;
	void borrar(Long id) throws ServiciosException;
	List<TipoDocumento> obtenerTodos();
	List<TipoDocumento> obtenerTodos(String filtro);
	TipoDocumento obtenerporID(Long id);
	void crearOModificar(TipoDocumento tipoDocumento) throws ServiciosException;
}
