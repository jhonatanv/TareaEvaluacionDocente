package co.edu.eam.ingesoft.pa2.tareaopenshift.rest;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.ejb.EJB;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import co.edu.eam.ingesoft.pa2.tareaopenshift.bos.WSLDEJB;
import co.edu.eam.ingesoft.pa2.tareaopenshift.rest.dto.RespuestaDTO;

@Path("/seguridad")
public class LoginRest {

	@EJB
	private WSLDEJB wsdlEJB;

	public static Map<String, String> tokens = new HashMap<>();

	@GET
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public RespuestaDTO login(@QueryParam(value = "codigo") String codigo, @QueryParam(value = "cedula") String cedula) {

		if (wsdlEJB.buscarEstudiante(codigo, cedula)) {
			String token = UUID.randomUUID().toString();
			tokens.put(token, cedula);
			return new RespuestaDTO(token);
		} else {
			return new RespuestaDTO(null, "NO AUTORIZADO", "-1");
		}

	}
}
