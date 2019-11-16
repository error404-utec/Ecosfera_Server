package com.services;

import javax.ejb.Remote;

import com.entities.Usuario;

@Remote
public interface IniciarSesionBeanRemote {
	String controles_PreInit(String nombre,String Password);
	Usuario iniciar(String nombre,String Password);
}
