package co.edu.eam.ingesoft.pa2.tareaopenshift.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import co.edu.eam.ingesoft.pa2.tareaopenshift.bos.WSLDEJB;
import co.edu.eam.ingesoft.pa2.tareaopenshift.persistencia.entidades.Grupo;
import co.edu.eam.ingesoft.pa2.tareaopenshift.rest.dto.RespuestaDTO;

@Path("/estudiante")
public class EstudianteRest {

	@EJB
	private WSLDEJB wsdl;

	public EstudianteRest() {
		// TODO Auto-generated constructor stub
	}

	@GET
	@Path("/buscarEstudiante")
	@Produces(MediaType.APPLICATION_JSON)
	public RespuestaDTO buscarEstudiante(@QueryParam(value = "cod") String codigo,
			@QueryParam(value = "ced") String cedula) {

		if (wsdl.buscarEstudiante(codigo, cedula)) {
			return new RespuestaDTO(true);
		} else {
			return new RespuestaDTO(false, "el estudiante no existe", "-1");
		}
	}

	@Path("/listarGrupos")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public RespuestaDTO listarGrupos(@QueryParam(value = "cod") String codigo,
			@QueryParam(value = "ced") String cedula) {
		List<Grupo> lista = wsdl.listarGrupos(codigo, cedula);
		if (lista.isEmpty()) {
			return new RespuestaDTO(null, "no hay grupos", "-1");
		} else {
			return new RespuestaDTO(lista);
		}
	}

}
