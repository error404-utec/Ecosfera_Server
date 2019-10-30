package com.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Usuario
 *
 */
@Entity
@Table(name="USUARIOS",uniqueConstraints={
	    @UniqueConstraint(columnNames = {"USUARIO"},name="UK_USUARIO_USUARIO"),
	    @UniqueConstraint(columnNames = {"MAIL"},name="UK_USUARIO_MAIL"),
	    @UniqueConstraint(columnNames = {"ID_TIPODOCUMENTO" , "DOCUMENTO"},name="UK_USUARIO_TDOC_DOC")
}) 
public class Usuario implements Serializable {
	@Id
	@SequenceGenerator(name="SEQ_ID_USUARIO",sequenceName="SEQ_ID_USUARIO",initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator="SEQ_ID_USUARIO")
	@Column(name="ID_USUARIO",columnDefinition="Number(38,0)")
	private Long id;
	
	@Column(nullable=false,length=30)
	private String usuario;
	
	@ManyToOne()
	@JoinColumn(name="ID_TIPODOCUMENTO",foreignKey = @ForeignKey(name="FK_USUARIO_TDOC"), nullable=false)
	private TipoDocumento tipoDocumento;
	
	@Column(nullable=false,length=30,name="DOCUMENTO")
	private String documento;
	
	@Column(nullable=false,length=50,name="NOMBRE")
	private String nombre;
	
	@Column(nullable=false,length=50,name="APELLIDO")
	private String apellido;
	
	@Column(nullable=false,length=100,name="DIRECCION")
	private String direccion;
	
	@Column(nullable=false,length=30,name="MAIL")
	private String mail;
	
	@Column(nullable=false,length=30,name="PASSWRD")
	private String passwrd;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="ID_ESTADO",foreignKey = @ForeignKey(name="FK_USUARIO_EST"), nullable=false)
	private Estado estado;
	
	@ManyToMany(fetch = FetchType.EAGER)
	
	@JoinTable(
			  name = "USUARIOS_PERFILES", 
			  joinColumns = @JoinColumn(name = "ID_USUARIO",foreignKey=@ForeignKey(name="FK_USUARIO_PERFIL_USUARIO")), 
			  inverseJoinColumns = @JoinColumn(name = "ID_PERFIL",foreignKey=@ForeignKey(name="FK_USUARIO_PERFIL_PERFIL"))
			  )
	private List<Perfil> perfiles = new ArrayList<>();
	
	private static final long serialVersionUID = 1L;

	public Usuario() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPasswrd() {
		return passwrd;
	}

	public void setPasswrd(String passwrd) {
		this.passwrd = passwrd;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<Perfil> getPerfiles() {
		return perfiles;
	}

	public void setPerfiles(List<Perfil> perfiles) {
		this.perfiles = perfiles;
	}

	
	
	
	
	
	
	
   
}
