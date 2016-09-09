package co.edu.eam.ingesoft.pa2.tareaopenshift.seguridad;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.omnifaces.util.Faces;

import co.edu.eam.ingesoft.pa2.tareaopenshift.bos.SeguridadEJB;
import co.edu.eam.ingesoft.pa2.tareaopenshift.bos.UsuarioEJB;
import co.edu.eam.ingesoft.pa2.tareaopenshift.persistencia.entidades.Acceso;
import co.edu.eam.ingesoft.pa2.tareaopenshift.persistencia.entidades.Rol;
import co.edu.eam.ingesoft.pa2.tareaopenshift.persistencia.entidades.Usuario;

@Named
@SessionScoped
public class SesionBean implements Serializable {

	private Usuario usuario;

	private String nombreUsuario;

	private String contraseña;

	@EJB
	private UsuarioEJB usuarioejb;

	@EJB
	private SeguridadEJB seguridadejb;

	private List<Acceso> accesos;

	private List<Rol> roles;

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String login() {
		Usuario u = null;
		if(!usuarioejb.buscarUsuario(nombreUsuario).isEmpty()){
		u = usuarioejb.buscarUsuario(nombreUsuario).get(0);
		
		contraseña = MD5Util.code(contraseña);
		}
		if (u != null && contraseña.equals(u.getPassword())) {
			usuario = u;
	
			roles = seguridadejb.listarRolesUsuario(usuario);
			accesos = seguridadejb.listarAccesosRol(roles);

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Inicio de sesson exitoso", null);
			FacesContext.getCurrentInstance().addMessage(null, message);
			return "/paginas/inicio.jsf?faces-redirect=true";
		} else {
			usuario = null;
			roles = null;
			accesos = null;
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "usuario o password incorrecto", null);
			FacesContext.getCurrentInstance().addMessage(null, message);
			return null;
		}
		
	}

	/**
	 * metodo que valida si hay sesion
	 * 
	 * @return
	 */
	public boolean isLogged() {
		return usuario != null;
	}
	
	public String logOut(){
		Faces.getSession().invalidate();
		return "/index.jsf?faces-redirect=true";
	}
	
	public List<Acceso> getAccesos() {
		return accesos;
	}
	
	public List<Rol> getRoles() {
		return roles;
	}
}
