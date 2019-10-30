package com.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Permisos
 *
 */
@Entity
@Table(name="PERMISOS",uniqueConstraints={
	    @UniqueConstraint(columnNames = {"FUNCIONALIDAD"},name="UK_PERM_FUN")
}) 
public class Permiso implements Serializable {
	@Id
	@SequenceGenerator(name="SEQ_ID_PERMISO",sequenceName="SEQ_ID_PERMISO",initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator="SEQ_ID_PERMISO")
	@Column(name="ID_PERMISO",columnDefinition="Number(38,0)")
	private Long id;
	
	@Column(nullable=false,length=50,name="FUNCIONALIDAD")
	private String funcionalidad;
	
	@Column(nullable=false,length=50,name="DESCRIPCION")
	private String descripcion;
	
	private static final long serialVersionUID = 1L;

	public Permiso() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFuncionalidad() {
		return funcionalidad;
	}

	public void setFuncionalidad(String funcionalidad) {
		this.funcionalidad = funcionalidad;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


   
}
