package co.edu.eam.ingesoft.pa2.tareaopenshift.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import co.edu.eam.ingesoft.pa2.tareaopenshift.bos.UsuarioEJB;
import co.edu.eam.ingesoft.pa2.tareaopenshift.persistencia.entidades.Usuario;
import co.edu.eam.ingesoft.pa2.tareaopenshift.rest.dto.RespuestaDTO;

@Path("/usuario")
public class UsuarioRest {

	@EJB
	private UsuarioEJB usuarioEJB;
	
	public UsuarioRest() {
		// TODO Auto-generated constructor stub
	}
	
	@GET
	@Path("/buscarUsuario")
	@Produces(MediaType.APPLICATION_JSON)
	public RespuestaDTO buscarUsuario(@QueryParam(value ="ced")int cedula){
		System.out.println("buscando a:" + cedula);
		Usuario u = usuarioEJB.buscar(cedula);
		return new RespuestaDTO(u);
	}
	
	@Path("/crear")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RespuestaDTO crear(Usuario usuario){
		if(usuarioEJB.buscar(usuario.getId()) != null){
			usuarioEJB.crear(usuario);
			return new RespuestaDTO(true);
		}else{
			return new RespuestaDTO(false, "el cliente ya esxisste", "-1");
		}
		
		
	}
	@Path("/listar")
	@GET
	public RespuestaDTO listarUsuarios(){
		//List<Usuario> lista =
		return null;
		
	}
}
