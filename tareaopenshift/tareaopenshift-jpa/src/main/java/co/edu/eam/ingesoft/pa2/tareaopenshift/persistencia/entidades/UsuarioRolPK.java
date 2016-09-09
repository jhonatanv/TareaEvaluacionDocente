package  co.edu.eam.ingesoft.pa2.tareaopenshift.persistencia.entidades;

import java.io.Serializable;


public class UsuarioRolPK implements Serializable {

	private int usuario;

	private Long rol;

	public UsuarioRolPK() {
	}

	public UsuarioRolPK(int usuario, Long rol) {
		super();
		this.usuario = usuario;
		this.rol = rol;
	}

	/**
	 * @return the usuario
	 */
	public int getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the rol
	 */
	public Long getRol() {
		return rol;
	}

	/**
	 * @param rol the rol to set
	 */
	public void setRol(Long rol) {
		this.rol = rol;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rol == null) ? 0 : rol.hashCode());
		result = prime * result + ((String.valueOf(usuario) == null) ? 0 : String.valueOf(usuario).hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioRolPK other = (UsuarioRolPK) obj;
		if (rol == null) {
			if (other.rol != null)
				return false;
		} else if (!rol.equals(other.rol))
			return false;
		if (String.valueOf(usuario) == null) {
			if (String.valueOf(other.usuario) != null)
				return false;
		} else if (!String.valueOf(usuario).equals(String.valueOf(other.usuario)))
			return false;
		return true;
	}
	
	
	
}
