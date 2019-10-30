package com.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Localidad
 *
 */
@Entity
@Table(name="LOCALIDADES",uniqueConstraints={
	    @UniqueConstraint(columnNames = {"NOMBRE"},name="UK_LOCALIDAD_NOMBRE"),
	    @UniqueConstraint(columnNames = {"ID_DEPARTAMENTO"},name="UK_LOCALIDAD_DEPTO_CODIGO")
}) 
public class Localidad implements Serializable {
	@Id
	@SequenceGenerator(name="SEQ_ID_LOCALIDAD",sequenceName="SEQ_ID_LOCALIDAD",initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator="SEQ_ID_LOCALIDAD")
	@Column(name="ID_LOCALIDAD",columnDefinition="Number(38,0)")
	private Long id;
	
	@Column(nullable=false,columnDefinition="Number(38,0)",name="CODIGO")
	private Long codigo;
	
	@Column(nullable=false,length=50,name="NOMBRE")
	private String nombre;
	
	private static final long serialVersionUID = 1L;

	public Localidad() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	
   
}
