package co.edu.eam.ingesoft.pa2.tareaopenshift.persistencia.dto;

import java.util.List;


public class RespuestaEvaluacionDTO {

	List<RespuestaPreguntaDTO> respuesta;
	private String idGrupo;
	private String comentario;
	private String codigoEstudiante;
	
	public RespuestaEvaluacionDTO() {
		// TODO Auto-generated constructor stub
	}

	public RespuestaEvaluacionDTO(List<RespuestaPreguntaDTO> respuesta, String idGrupo, String comentario,
			String codigoEstudiante, int idEvaluacion) {
		super();
		this.respuesta = respuesta;
		this.idGrupo = idGrupo;
		this.comentario = comentario;
		this.codigoEstudiante = codigoEstudiante;
	}

	public List<RespuestaPreguntaDTO> getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(List<RespuestaPreguntaDTO> respuesta) {
		this.respuesta = respuesta;
	}

	public String getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(String idGrupo) {
		this.idGrupo = idGrupo;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getCodigoEstudiante() {
		return codigoEstudiante;
	}

	public void setCodigoEstudiante(String codigoEstudiante) {
		this.codigoEstudiante = codigoEstudiante;
	}
	
	
	
}
