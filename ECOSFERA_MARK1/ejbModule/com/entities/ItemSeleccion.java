package com.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: ItemSeleccion
 *
 */
@Entity
@Table(name="ITEMSSELECCIONES")
public class ItemSeleccion implements Serializable {
	@Id
	@SequenceGenerator(name="SEQ_ID_ITEMSELECCION",sequenceName="SEQ_ID_ITEMSELECCION",initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator="SEQ_ID_ITEMSELECCION")
	@Column(name="ID_ITEMSELECCION",columnDefinition="Number(38,0)")
	private Long id;
	
	@Column(nullable=false,columnDefinition="Number(38,0)",name="CODIGO")
	private Long codigo;
	
	@Column(nullable=false,length=50,name="DESCRIPCION")
	private String descripcion;
	
	private static final long serialVersionUID = 1L;

	public ItemSeleccion() {
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
   
}
