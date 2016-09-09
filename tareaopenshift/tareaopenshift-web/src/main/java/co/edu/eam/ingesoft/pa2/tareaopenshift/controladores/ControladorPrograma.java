package co.edu.eam.ingesoft.pa2.tareaopenshift.controladores;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import co.edu.eam.ingesoft.pa2.tareaopenshift.bos.FacultadEJB;
import co.edu.eam.ingesoft.pa2.tareaopenshift.bos.ProgramaEJB;
import co.edu.eam.ingesoft.pa2.tareaopenshift.persistencia.entidades.Facultad;
import co.edu.eam.ingesoft.pa2.tareaopenshift.persistencia.entidades.Pregunta;
import co.edu.eam.ingesoft.pa2.tareaopenshift.persistencia.entidades.Programa;

@Named("gestionPrograma")
@ViewScoped
public class ControladorPrograma implements Serializable{
	
	@EJB
	private ProgramaEJB programaejb;
	
	@EJB
	private FacultadEJB facultadejb;

	
	/**
	 * Identificador 
	 */
	private String codigo;

	/**
	 * el nombre
	 */
	private String nombre;

	/**
	 * la facultad
	 */
	private Facultad facultad;
	
	
	/**
	 * La lista de las facultades
	 */
	private List<Facultad> ListaFacultad;
	
	private Facultad facultadSelecccionado;
	
	public Facultad getFacultadSelecccionado() {
		return facultadSelecccionado;
	}
	
	public void setFacultadSelecccionado(Facultad facultadSelecccionado) {
		this.facultadSelecccionado = facultadSelecccionado;
	}
	
	public List<Facultad> getListaFacultad() {
		return ListaFacultad;
	}
	
	
	@PostConstruct
	public void inicializar() {
		ListaFacultad = facultadejb.listarFacultad();
	}
	public void crear() {
		
		Programa pro = new Programa(codigo, nombre, facultadSelecccionado);
		programaejb.crear(pro);
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"registro con exito de programa", null);
		FacesContext.getCurrentInstance().addMessage(null, message);
			
		
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public Facultad getFacultad() {
		return facultad;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public void setFacultad(Facultad facultad) {
		this.facultad = facultad;
	}
}
