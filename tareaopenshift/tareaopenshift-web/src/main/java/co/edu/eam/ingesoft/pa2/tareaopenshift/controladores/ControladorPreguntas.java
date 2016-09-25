package co.edu.eam.ingesoft.pa2.tareaopenshift.controladores;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import co.edu.eam.ingesoft.pa2.tareaopenshift.bos.PreguntaEJB;
import co.edu.eam.ingesoft.pa2.tareaopenshift.bos.ProgramaEJB;
import co.edu.eam.ingesoft.pa2.tareaopenshift.persistencia.entidades.Pregunta;
import co.edu.eam.ingesoft.pa2.tareaopenshift.seguridad.Secured;

@Named("gestionPregunta")
@ViewScoped
public class ControladorPreguntas implements Serializable {

	@EJB
	private PreguntaEJB preguntaejb;

	/**
	 * Identificador de la pregunta
	 */
	private int idPregrunta;

	/**
	 * La pregunta
	 */
	private String pregunta;

	/**
	 * El valor de la pregunta
	 */
	private double valor;

	/**
	 * crea una pregunta
	 */
	@Secured
	public void crear() {
		idPregrunta = preguntaejb.listarPregunta().size();
		
		Pregunta p = new Pregunta(idPregrunta, pregunta, valor);
		preguntaejb.crear(p);
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,"registro con exito Pregunta", null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public int getIdPregrunta() {
		return idPregrunta;
	}

	public double getValor() {
		return valor;
	}

	public String getPregunta() {
		return pregunta;
	}

	public void setIdPregrunta(int idPregrunta) {
		this.idPregrunta = idPregrunta;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

}
