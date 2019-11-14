package com.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Perfil
 *
 */
@Entity
@Table(name="PERFILES",uniqueConstraints={
	    @UniqueConstraint(columnNames = {"NOMBRE"},name="UK_PERF_NOM")
}) 
public class Perfil implements Serializable {
	@Id
	@SequenceGenerator(name="SEQ_ID_PERFIL",sequenceName="SEQ_ID_PERFIL",initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator="SEQ_ID_PERFIL")
	@Column(name="ID_PERFIL",columnDefinition="Number(38,0)")
	private Long id;
	
	@Column(nullable=false,length=50,name="NOMBRE")
	private String nombre;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			  name = "PERFILES_PERMISOS", 
			  joinColumns = @JoinColumn(name = "ID_PERFIL",foreignKey=@ForeignKey(name="FK_PERFPERM_PERF")), 
			  inverseJoinColumns = @JoinColumn(name = "ID_PERMISO",foreignKey=@ForeignKey(name="FK_PERFPERM_PERM")))
	private List<Permiso> permisos = new ArrayList<>();
	
	private static final long serialVersionUID = 1L;

	public Perfil() {
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

	public List<Permiso> getPermisos() {
		return permisos;
	}

	public void setPermisos(List<Permiso> permisos) {
		this.permisos = permisos;
	}
	
	public void asginarPermiso(Permiso permiso) {
		this.permisos.add(permiso);
	}
	
	public void eliminarPermiso(Permiso permiso) {
		this.permisos.remove(permiso);
	}
	
	
	
   
}
