package com.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Estado
 *
 */
@Entity
@Table(name="ESTADOS",uniqueConstraints={
	    @UniqueConstraint(columnNames = {"NOMBRE"},name="UK_ESTA_NOM")
}) 
public class Estado implements Serializable {
	@Id
	@SequenceGenerator(name="SEQ_ID_ESTADO",sequenceName="SEQ_ID_ESTADO",initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator="SEQ_ID_ESTADO")
	@Column(name="ID_ESTADO", columnDefinition ="number(38,0)")
	private Long id;
	
	@Column(name="Nombre",nullable=false,length=50)
	private String nombre;
	
	@Column(name="PER_LOGIN",columnDefinition = "CHAR(1) not null")
	private boolean permiteLogin;
	
	@Column(name="PER_REGISTRO",columnDefinition = "CHAR(1) not null")
	private boolean permiteRegistro;
	
	@Column(name="ELIM_LOGICA",columnDefinition = "CHAR(1) not null")
	private boolean eliminado;
	
	
	private static final long serialVersionUID = 1L;

	public Estado() {
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

	public boolean isPermiteLogin() {
		return permiteLogin;
	}

	public void setPermiteLogin(boolean permiteLogin) {
		this.permiteLogin = permiteLogin;
	}

	public boolean isPermiteRegistro() {
		return permiteRegistro;
	}

	public void setPermiteRegistro(boolean permiteRegistro) {
		this.permiteRegistro = permiteRegistro;
	}

	public boolean isEliminado() {
		return eliminado;
	}

	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}
   
	
	
}
