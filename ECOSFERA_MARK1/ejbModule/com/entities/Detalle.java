package com.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Detalle
 *
 */
@Entity
@Table(name="DETALLES") 
public class Detalle implements Serializable {
	@Id
	@SequenceGenerator(name="SEQ_ID_DETALLE",sequenceName="SEQ_ID_DETALLE",initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator="SEQ_ID_DETALLE")
	@Column(name="ID_DETALLE",columnDefinition="Number(38,0)")
	private Long id;
	
	@Column(nullable=true,length=50,name="VAL_TEXTO")
	private String valTexto;
	
	@Column(nullable=true,name="VAL_NUMERICO",columnDefinition="Number(38,0)")
	private String valNumerico;
	
	@Column(nullable=true,name="VAL_FECHA")
	private String valFecha;
	
	@ManyToOne()
	@JoinColumn(name="ID_CARACTERISTICA",foreignKey = @ForeignKey(name="FK_DETALLE_CARACT"), nullable=false)
	private Caracteristica caracteristia;
	
	@ManyToOne()
	@JoinColumn(name="ID_ITEMSELECCION",foreignKey = @ForeignKey(name="FK_DETALLE_ITEMS"), nullable=true)
	private ItemSeleccion itemSeleccion;
	
	private static final long serialVersionUID = 1L;

	public Detalle() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getValTexto() {
		return valTexto;
	}

	public void setValTexto(String valTexto) {
		this.valTexto = valTexto;
	}

	public String getValNumerico() {
		return valNumerico;
	}

	public void setValNumerico(String valNumerico) {
		this.valNumerico = valNumerico;
	}

	public String getValFecha() {
		return valFecha;
	}

	public void setValFecha(String valFecha) {
		this.valFecha = valFecha;
	}

	public Caracteristica getCaracteristia() {
		return caracteristia;
	}

	public void setCaracteristia(Caracteristica caracteristia) {
		this.caracteristia = caracteristia;
	}

	public ItemSeleccion getItemSeleccion() {
		return itemSeleccion;
	}

	public void setItemSeleccion(ItemSeleccion itemSeleccion) {
		this.itemSeleccion = itemSeleccion;
	}
	
	
   //coso detalle
}
