package co.edu.eam.ingesoft.pa2.tareaopenshift.persistencia.dto;

public class RespuestaPreguntaDTO {

	private int idPregunta;
	private double Calificacion;
	
	public RespuestaPreguntaDTO() {
		// TODO Auto-generated constructor stub
	}

	public RespuestaPreguntaDTO(int idPregunta, double calificacion) {
		super();
		this.idPregunta = idPregunta;
		Calificacion = calificacion;
	}

	public int getIdPregunta() {
		return idPregunta;
	}

	public void setIdPregunta(int idPregunta) {
		this.idPregunta = idPregunta;
	}

	public double getCalificacion() {
		return Calificacion;
	}

	public void setCalificacion(double calificacion) {
		Calificacion = calificacion;
	}

	
	
}
