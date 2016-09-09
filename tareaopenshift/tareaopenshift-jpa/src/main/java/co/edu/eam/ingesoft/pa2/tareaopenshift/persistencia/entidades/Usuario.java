/**
 * 
 */
package co.edu.eam.ingesoft.pa2.tareaopenshift.persistencia.entidades;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * @author jhonatan
 *
 */
@Entity
@Table(name = "Usuario")
//@XmlSeeAlso({Decano.class,Coordinador.class})
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({ @NamedQuery(name = Usuario.LISTA_USUARIO, query = "select u from Usuario u where u.usuario=?1") })
public class Usuario implements Serializable {

	public static final String LISTA_USUARIO = "Usuario.listar";
	
	@Id
	@Column(name = "idUsuario")
	private int id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "apellido")
	private String apellido;

	
	@Column(name = "usuario")
	private String usuario;

	@Column(name = "password")
	private String password;

	// Constructor vacio
	public Usuario() {
		super();
	}

	// Constructor
	public Usuario(int id, String nombre, String apellido, String usuario, String password) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.usuario = usuario;
		this.password = password;
	}

	// Accesores y modificadores
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getUsuario() {
		return usuario;
	}public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
