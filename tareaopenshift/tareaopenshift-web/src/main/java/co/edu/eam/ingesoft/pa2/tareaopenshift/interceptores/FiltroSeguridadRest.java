package co.edu.eam.ingesoft.pa2.tareaopenshift.interceptores;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import co.edu.eam.ingesoft.pa2.tareaopenshift.rest.LoginRest;
import co.edu.eam.ingesoft.pa2.tareaopenshift.rest.dto.RespuestaDTO;






@Secured
@Provider
public class FiltroSeguridadRest implements ContainerRequestFilter{
	
	@Override
	public void filter(ContainerRequestContext ctxReq) throws IOException {
		// TODO Auto-generated method stub
		String token = ctxReq.getHeaderString("Authorization");
		if(!LoginRest.tokens.containsKey(token)){
			RespuestaDTO dto = new RespuestaDTO(null, "No autorizado", "-3");
			Response res = Response.status(401).entity(dto).build();

			ctxReq.abortWith(res);

		}
		
		
	}

}
