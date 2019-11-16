package com.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Observacion
 *
 */
@Entity
@Table(name="OBSERVACIONES")
public class Observacion implements Serializable {
	@Id
	@SequenceGenerator(name="SEQ_ID_OBSERVACIONES",sequenceName="SEQ_ID_OBSERVACIONES",initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator="SEQ_ID_OBSERVACIONES")
	@Column(name="ID_OBSERVACION",columnDefinition="Number(38,0)")
	private Long id;
	
	@Column(nullable=false,length=250,name="DESCRIPCION")
	private String descripcion;
	
	@Column(nullable=false,length=100,name="GEOLOCALIZACION")
	private String geolocalizacion;
	
	@Column(nullable=false,name="FECHA")
	private Date fecha;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="ID_TIPOOBSERVACION",foreignKey = @ForeignKey(name="FK_OBSERVACION_TPOOBS"), nullable=false)
	private TipoObservacion tipo;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="ID_USUARIO",foreignKey = @ForeignKey(name="FK_OBSERVACION_USUARIO"), nullable=false)
	private Usuario usuario;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="ID_LOCALIDAD",foreignKey = @ForeignKey(name="FK_OBSERVACION_LOCALIDAD"), nullable=false)
	private Localidad localidad;
	
	private static final long serialVersionUID = 1L;
	
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="ID_OBSERVACION",foreignKey = @ForeignKey(name="FK_DETALLE_OBSERV"), nullable=false)
	private List<Detalle> detalles = new ArrayList<>();
	
	
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="ID_OBSERVACION",foreignKey = @ForeignKey(name="FK_IMAGEN_OBSERV"), nullable=false)
	private List<Imagenes> imagenes = new ArrayList<>();

	public Observacion() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getGeolocalizacion() {
		return geolocalizacion;
	}

	public void setGeolocalizacion(String geolocalizacion) {
		this.geolocalizacion = geolocalizacion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public TipoObservacion getTipo() {
		return tipo;
	}

	public void setTipo(TipoObservacion tipo) {
		this.tipo = tipo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Localidad getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}

	public List<Detalle> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<Detalle> detalles) {
		this.detalles = detalles;
	}

	public List<Imagenes> getImagenes() {
		return imagenes;
	}

	public void setImagenes(List<Imagenes> imagenes) {
		this.imagenes = imagenes;
	}	
	
   
}
