package com.services;

import java.util.List;

import javax.ejb.Remote;

import com.entities.Estado;
import com.entities.Perfil;
import com.entities.TipoDocumento;
import com.entities.Usuario;
import com.exceptions.ServiciosException;

@Remote
public interface UsuarioBeanRemote {
	void crear(Usuario usuario) throws ServiciosException;
	void actualizar(Usuario usuario) throws ServiciosException;
	void borrar(Long id) throws ServiciosException;
	List<Usuario> obtenerTodos();
	List<Usuario> obtenerTodos(String filtro);
	Usuario obtenerPorNomber(String nombre);
	Usuario obtenerPorId(Long id);
	String controlarUnicidad(Usuario usuario);
	String controles_preModify(Usuario usuario);
	String controles_PreDeltePerfiles(Perfil perfil);
	String controles_PreDelteEstado(Estado estado);
	String controles_PreDelteTdco(TipoDocumento tipoDocumento);
}

