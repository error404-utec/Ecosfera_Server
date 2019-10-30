package com.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.framework.TipoDatos;



/**
 * Entity implementation class for Entity: Caracteristica
 *
 */
@Entity
@Table(name="CARACTERISTICAS") 
public class Caracteristica implements Serializable {
	@Id
	@SequenceGenerator(name="SEQ_ID_CARACTERISTICA",sequenceName="SEQ_ID_CARACTERISTICA",initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator="SEQ_ID_CARACTERISTICA")
	@Column(name="ID_CARACTERISTICA",columnDefinition="Number(38,0)")
	private Long id;
	
	@Column(nullable=false,length=50,name="NOMBRE")
	private String nombre;
	
	@Column(nullable=false,length=250,name="DESCRIPCION")
	private String descripcion;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false,length=10,name="TIPODATO")
	private TipoDatos tipoDato;
	
	
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="ID_CARACTERISTICA",foreignKey = @ForeignKey(name="FK_ITEMSELECCION_CARACT"), nullable=false)
	private List<ItemSeleccion> ItemSeleccion = new ArrayList<>();
	
	private static final long serialVersionUID = 1L;

	public Caracteristica() {
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

	public TipoDatos getTipoDato() {
		return tipoDato;
	}

	public void setTipoDato(TipoDatos tipoDato) {
		this.tipoDato = tipoDato;
	}
	
	
   
}
