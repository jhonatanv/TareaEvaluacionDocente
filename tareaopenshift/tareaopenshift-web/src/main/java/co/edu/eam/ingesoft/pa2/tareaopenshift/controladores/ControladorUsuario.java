package co.edu.eam.ingesoft.pa2.tareaopenshift.controladores;

import java.io.Serializable;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import co.edu.eam.ingesoft.pa2.tareaopenshift.bos.FacultadEJB;
import co.edu.eam.ingesoft.pa2.tareaopenshift.bos.ProgramaEJB;
import co.edu.eam.ingesoft.pa2.tareaopenshift.bos.UsuarioEJB;
import co.edu.eam.ingesoft.pa2.tareaopenshift.excepciones.ExcepcionNegocio;
import co.edu.eam.ingesoft.pa2.tareaopenshift.persistencia.entidades.Coordinador;
import co.edu.eam.ingesoft.pa2.tareaopenshift.persistencia.entidades.Decano;
import co.edu.eam.ingesoft.pa2.tareaopenshift.persistencia.entidades.Facultad;
import co.edu.eam.ingesoft.pa2.tareaopenshift.persistencia.entidades.Programa;
import co.edu.eam.ingesoft.pa2.tareaopenshift.seguridad.MD5Util;
import co.edu.eam.ingesoft.pa2.tareaopenshift.seguridad.Secured;

@Named("gestionUsuario")
@ViewScoped
public class ControladorUsuario implements Serializable {

	
	/**
	 * EJB Programa
	 */
	@EJB
	private ProgramaEJB programaejb;

	/**
	 * EJB Facultad
	 */
	@EJB
	private FacultadEJB facultadejb;
	
	/**
	 * EJB de usuario
	 */
	@EJB
	private UsuarioEJB usuarioejb;


	/**
	 * Identificador del usuario
	 */
	private int id;

	/**
	 * nombre del usuario
	 */
	private String nombre;

	/**
	 * apellido del usuario
	 */
	private String apellido;

	/**
	 * usuario del usuario
	 */
	private String usuario;

	/**
	 * contraseña del usuario
	 */
	private String password;

	/**
	 * El usuario Seleccionado
	 */
	private String usuarioSeleccionado;

	/**
	 * La lista de programas
	 */
	private List<Programa> ListaPrograma;

	/**
	 * La lista de las facultades
	 */
	private List<Facultad> ListaFacultad;

	private Programa programaSelecccionado;
	private Facultad facultadSelecccionado;

	@PostConstruct
	public void inicializar() {
		usuarioSeleccionado = "0";
		ListaPrograma = programaejb.listarPrograma();
	}

	public void llenarListas() {
		if (usuarioSeleccionado.equals("0")) {
			ListaPrograma = programaejb.listarPrograma();
		} else {
			ListaFacultad = facultadejb.listarFacultad();
		}

	}

	/**
	 * crea un usuario
	 */
	
	@Secured
	public void crear() {
		try {
			if (nombre != "" || apellido != "" || usuario != "" || password != "") {
				

				if (usuarioSeleccionado.equals("0")) {
					password = MD5Util.code(password);
					Coordinador c = new Coordinador(nombre, apellido, usuario, password, id, programaSelecccionado);
					usuarioejb.crear(c);
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
							"registro con exito cordinador", null);
					FacesContext.getCurrentInstance().addMessage(null, message);
				} else {
					password = MD5Util.code(password);
					Decano d = new Decano(nombre, apellido, usuario, password, id, facultadSelecccionado);
					usuarioejb.crear(d);
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
							"registro con exito decano", null);
					FacesContext.getCurrentInstance().addMessage(null, message);
				}
			} else {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
						"LLene campos", null);
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
		} catch (Exception e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, e.getMessage(), null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public boolean comboProgama() {
		if (usuarioSeleccionado.equals("0")) {
			return true;
		}
		return false;
	}

	public boolean comboFacultad() {
		if (usuarioSeleccionado.equals("1")) {
			return true;
		}
		return false;
	}

	public String getApellido() {
		return apellido;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getPassword() {
		return password;
	}

	public String getUser() {
		return usuario;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUser(String user) {
		this.usuario = user;
	}

	public String getUsuarioSeleccionado() {
		return usuarioSeleccionado;
	}

	public void setUsuarioSeleccionado(String usuarioSeleccionado) {
		this.usuarioSeleccionado = usuarioSeleccionado;
	}

	public List<Programa> getListaPrograma() {
		return ListaPrograma;
	}

	public void setListaPrograma(List<Programa> programa) {
		this.ListaPrograma = programa;
	}

	public List<Facultad> getListaFacultad() {
		return ListaFacultad;
	}

	public void setListaFacultad(List<Facultad> facultad) {
		this.ListaFacultad = facultad;
	}

	public Facultad getFacultadSelecccionado() {
		return facultadSelecccionado;
	}

	public Programa getProgramaSelecccionado() {
		return programaSelecccionado;
	}

	public void setFacultadSelecccionado(Facultad facultadSelecccionado) {
		this.facultadSelecccionado = facultadSelecccionado;
	}

	public void setProgramaSelecccionado(Programa programaSelecccionado) {
		this.programaSelecccionado = programaSelecccionado;
	}

}

