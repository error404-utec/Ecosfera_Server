package com.entities;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Imagenes
 *
 */
@Entity
@Table(name="IMAGENES")
public class Imagenes implements Serializable {
	@Id
	@SequenceGenerator(name="SEQ_ID_IMAGEN",sequenceName="SEQ_ID_IMAGEN",initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator="SEQ_ID_IMAGEN")
	@Column(name="ID_IMAGEN",columnDefinition="Number(38,0)")
	private Long id;
	
	@Lob
	@Column(nullable=false,name="IMAGEN")
	private BufferedImage img;
	
	@Column(nullable=true,length=250,name="DESCRIPCION")
	private String descripcion;
	
	
	@Column(nullable=true,length=500,name="TAGS")
	private String tag;
	
	private static final long serialVersionUID = 1L;

	public Imagenes() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BufferedImage getImg() {
		return img;
	}

	public void setImg(BufferedImage img) {
		this.img = img;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	
	
	
   
}
