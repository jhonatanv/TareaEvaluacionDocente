package co.edu.eam.ingesoft.pa2.tareaopenshift.bos;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import co.edu.eam.ingesoft.pa2.tareaopenshift.implementacion.EJBGenerico;
import co.edu.eam.ingesoft.pa2.tareaopenshift.persistencia.entidades.Acceso;
import co.edu.eam.ingesoft.pa2.tareaopenshift.persistencia.entidades.Rol;
import co.edu.eam.ingesoft.pa2.tareaopenshift.persistencia.entidades.Usuario;

@LocalBean
@Stateless
public class SeguridadEJB extends EJBGenerico<Usuario> {

	

	
	public List<Rol> listarRolesUsuario(Usuario usuario) {
			return dao.ejecutarNamedQuery(Rol.LISTAROLESPORUSUARIO, usuario);
	}

	
	public List<Acceso> listarAccesosRol(List<Rol> roles) {
		return dao.ejecutarNamedQuery(Acceso.LISTAR_ACCSESOS_POR_ROL, roles);
	}


	@Override
	public Class getClase() {
		// TODO Auto-generated method stub
		return Usuario.class;
	}
	
	

}
