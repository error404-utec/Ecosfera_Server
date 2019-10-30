package com.entities;

import java.io.Serializable;
import java.util.Date;


import javax.persistence.*;

import com.framework.Fiabilidad;


/**
 * Entity implementation class for Entity: Revision
 *
 */
@Entity
@Table(name="REVISIONES")
public class Revision implements Serializable {
	@Id
	@SequenceGenerator(name="SEQ_ID_REVISION",sequenceName="SEQ_ID_REVISION",initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator="SEQ_ID_REVISION")
	@Column(name="ID_REVISION",columnDefinition="Number(38,0)")
	private Long id;
	
	@Column(nullable=false,length=250,name="COMENTARIOS")
	private String comentarios;
	
	@Column(nullable=true,name="FECHA")
	private Date fecha;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false,length=50,name="FIABILIDAD")
	private Fiabilidad fiabilidad;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="ID_USUARIO",foreignKey = @ForeignKey(name="FK_REVISION_USUARI"), nullable=false)
	private Usuario usuario;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="ID_OBSERVACION",foreignKey = @ForeignKey(name="FK_REVISION_OBSERV"), nullable=false)
	private Observacion observacion;
	
	private static final long serialVersionUID = 1L;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Fiabilidad getFiabilidad() {
		return fiabilidad;
	}

	public void setFiabilidad(Fiabilidad fiabilidad) {
		this.fiabilidad = fiabilidad;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Observacion getObservacion() {
		return observacion;
	}

	public void setObservacion(Observacion observacion) {
		this.observacion = observacion;
	}
   
}
