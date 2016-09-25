package co.edu.eam.ingesoft.pa2.tareaopenshift.rest;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.ejb.EJB;
import javax.enterprise.inject.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import co.edu.eam.ingesoft.pa2.tareaopenshift.bos.UsuarioEJB;
import co.edu.eam.ingesoft.pa2.tareaopenshift.bos.WSLDEJB;
import co.edu.eam.ingesoft.pa2.tareaopenshift.persistencia.entidades.Usuario;
import co.edu.eam.ingesoft.pa2.tareaopenshift.rest.dto.RespuestaDTO;

@Path("/seguridad")
public class LoginRest {

	@EJB
	private WSLDEJB wsdlEJB;

	public static Map<String, Object> tokens = new HashMap<>();

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/login")
	@javax.ws.rs.Produces(MediaType.APPLICATION_JSON)
	public RespuestaDTO login(@FormParam(value = "codigo") String codigo, @FormParam(value = "cedula") String cedula) {

		
		Object estudiante = wsdlEJB.buscarEstudiante(codigo, cedula);
		
		if ( estudiante != null) {
			String token = UUID.randomUUID().toString();
			tokens.put(token, estudiante);
			return new RespuestaDTO(token);
		} else {
			return new RespuestaDTO(null, "NO AUTORIZADO", "-1");
		}

	}
}
