package com.services;

import com.entities.Localidad;
import com.entities.Observacion;
import com.exceptions.ServiciosException;

import javax.ejb.Remote;
import java.util.Date;
import java.util.List;

@Remote
public interface ObservacionBeanRemote {
    void crear(Observacion observacion) throws ServiciosException;

    void actualizar(Observacion observacion) throws ServiciosException;

    void borrar(Long id) throws ServiciosException;

    List<Observacion> obtenerTodas();

    List<Observacion> obtenerTodas(Date start, Date end);

	String controles_PreDeleteLocalidad(Localidad localiadad);
}
