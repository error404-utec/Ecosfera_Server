package com.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Departamento
 *
 */
@Entity
@Table(name="DEPARTAMENTOS",uniqueConstraints={
	    @UniqueConstraint(columnNames = {"NOMBRE"},name="UK_DEPARTAMENTO_NOMBRE"),
	    @UniqueConstraint(columnNames = {"ID_ZONA"},name="UK_DEPARTAMENTO_ZONA_CODIGO")
}) 
public class Departamento implements Serializable {
	@Id
	@SequenceGenerator(name="SEQ_ID_DEPARTAMENTO",sequenceName="SEQ_ID_DEPARTAMENTO",initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator="SEQ_ID_DEPARTAMENTO")
	@Column(name="ID_DEPARTAMENTO",columnDefinition="Number(38,0)")
	private Long id;
	
	@Column(nullable=false,columnDefinition="Number(38,0)",name="CODIGO")
	private Long codigo;
	
	@Column(nullable=false,length=50,name="NOMBRE")
	private String nombre;
	
	@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="ID_DEPARTAMENTO",foreignKey = @ForeignKey(name="FK_LOCALIDAD_DEPARTAMENTO"), nullable=false)
	private List<Localidad> localidades = new ArrayList<>();
	
	private static final long serialVersionUID = 1L;

	public Departamento() {
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

	public List<Localidad> getLocalidades() {
		return localidades;
	}

	public void setLocalidades(List<Localidad> localidades) {
		this.localidades = localidades;
	}
   
	
	//hola coso comentario
	
}
