package co.edu.eam.ingesoft.pa2.tareaopenshift.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Calificado")
@IdClass(value = CalificadoPK.class)
public class Calificado implements Serializable {

	@Id
	@Column(name = "idEstudiante")
	private String idEstudiante;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "idGrupo")
	private Grupo idGrupo;
	
	@Column(name = "estado")
	private boolean estado;
	
	public Calificado() {
		// TODO Auto-generated constructor stub
	}

	
	
	
	public Calificado(String idEstudiante, Grupo idGrupo, boolean estado) {
		super();
		this.idEstudiante = idEstudiante;
		this.idGrupo = idGrupo;
		this.estado = estado;
	}




	public String getIdEstudiante() {
		return idEstudiante;
	}

	public void setIdEstudiante(String idEstudiante) {
		this.idEstudiante = idEstudiante;
	}

	public Grupo getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(Grupo idGrupo) {
		this.idGrupo = idGrupo;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	


}
