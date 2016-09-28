package co.edu.eam.ingesoft.pa2.tareaopenshift.persistencia.entidades;

import java.io.Serializable;

public class CalificadoPK implements Serializable{

	private String idEstudiante;
	private String idGrupo;
	
	public CalificadoPK() {
		// TODO Auto-generated constructor stub
	}

	public CalificadoPK(String idEstudiante, String idGrupo) {
		super();
		this.idEstudiante = idEstudiante;
		this.idGrupo = idGrupo;
	}

	public String getIdEstudiante() {
		return idEstudiante;
	}

	public void setIdEstudiante(String idEstudiante) {
		this.idEstudiante = idEstudiante;
	}

	public String getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(String idGrupo) {
		this.idGrupo = idGrupo;
	}
	
	
	
	
}
