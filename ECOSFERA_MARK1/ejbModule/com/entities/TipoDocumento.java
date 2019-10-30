package com.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: TipoDocumento
 *
 */
@Entity
@Table(name="TIPOSDOCUMENTOS",uniqueConstraints={
	    @UniqueConstraint(columnNames = {"NOMBRE"},name="UK_TIPDOC_NOM"),
	    @UniqueConstraint(columnNames = {"ABREVIATURA"},name="UK_TIPDOC_ABRE")
}) 
public class TipoDocumento implements Serializable {
	@Id
	@SequenceGenerator(name="SEQ_ID_TIPODOCUMENTO",sequenceName="SEQ_ID_TIPODOCUMENTO",initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator="SEQ_ID_TIPODOCUMENTO")
	@Column(name="ID_TIPODOCUMENTO",columnDefinition="Number(38,0)")
	private Long id;
	
	@Column(name="NOMBRE",columnDefinition="Varchar(50) not null")
	private String nombre;
	
	@Column(name="ABREVIATURA",length=5,nullable=false)
	private String abreviatura;
	
	private static final long serialVersionUID = 1L;

	public TipoDocumento() {
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

	public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}
}
