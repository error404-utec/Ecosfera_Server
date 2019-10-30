package com.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Zona
 *
 */
@Entity
@Table(name="ZONAS",uniqueConstraints={
	    @UniqueConstraint(columnNames = {"NOMBRE"},name="UK_ZONA_NOMBRE"),
	    @UniqueConstraint(columnNames = {"CODIGO"},name="UK_ZONA_CODIGO")
}) 
public class Zona implements Serializable {
	@Id
	@SequenceGenerator(name="SEQ_ID_ZONA",sequenceName="SEQ_ID_ZONA",initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator="SEQ_ID_ZONA")
	@Column(name="ID_ZONA",columnDefinition="Number(38,0)")
	private Long id;
	
	@Column(nullable=false,columnDefinition="Number(38,0)",name="CODIGO")
	private Long codigo;
	
	@Column(nullable=false,length=50,name="NOMBRE")
	private String nombre;
	
	@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="ID_Zona",foreignKey = @ForeignKey(name="FK_DEPARTAMENTO_ZONA"), nullable=false)
	private List<Departamento> departamentos = new ArrayList<>();
	
	private static final long serialVersionUID = 1L;

	public Zona() {
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

	public List<Departamento> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(List<Departamento> departamentos) {
		this.departamentos = departamentos;
	}
	
	public void asignarDepartamento(Departamento departamento) {
		this.departamentos.add(departamento);
	}
	
	
	
	
   
}
