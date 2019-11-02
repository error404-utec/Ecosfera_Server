package com.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: TipoObservacion
 *
 */

//cambio commit


@Entity
@Table(name="TIPOSOBSERVACIONES",uniqueConstraints={
	    @UniqueConstraint(columnNames = {"NOMBRE"},name="UK_TIPOOBSERVACION_NOM")
}) 
public class TipoObservacion implements Serializable {
	@Id
	@SequenceGenerator(name="SEQ_ID_TIPOOBSERVACION",sequenceName="SEQ_ID_TIPOOBSERVACION",initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator="SEQ_ID_TIPOOBSERVACION")
	@Column(name="ID_TIPOOBSERVACION",columnDefinition="Number(38,0)")
	private Long id;
	
	@Column(nullable=false,length=50,name="NOMBRE")
	private String nombre;
	
	@Column(nullable=false,length=250,name="DESCRIPCION")
	private String descripcion;
	
	@Column(nullable=false,length=20,name="TEL_EMERGENCIA")
	private String telEmergencia;
	
	
	@OneToMany()
	@JoinColumn(name="ID_TIPOOBSERVACION",foreignKey = @ForeignKey(name="FK_CARACTERISTICA_TPOOBS"), nullable=false)
	private List<Caracteristica> caracteristicas = new ArrayList<>();
	
	private static final long serialVersionUID = 1L;

	public TipoObservacion() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTelEmergencia() {
		return telEmergencia;
	}

	public void setTelEmergencia(String telEmergencia) {
		this.telEmergencia = telEmergencia;
	}

	public List<Caracteristica> getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(List<Caracteristica> caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	public void asignarCaracteristica(Caracteristica caracteristica) {
		// TODO Auto-generated method stub
		this.caracteristicas.add(caracteristica);
		
	}
	
	
   
}
