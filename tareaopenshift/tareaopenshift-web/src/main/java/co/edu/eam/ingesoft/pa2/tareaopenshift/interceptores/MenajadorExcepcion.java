package co.edu.eam.ingesoft.pa2.tareaopenshift.interceptores;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import co.edu.eam.ingesoft.pa2.tareaopenshift.excepciones.ExcepcionNegocio;
import co.edu.eam.ingesoft.pa2.tareaopenshift.rest.dto.RespuestaDTO;



@Provider
public class MenajadorExcepcion implements ExceptionMapper<Throwable> {

	/**
	 * @param exc:
	 *            excepcion que paso
	 */
	@Override
	public Response toResponse(Throwable exc) {

		if (exc instanceof ExcepcionNegocio) {
			ExcepcionNegocio excNeg = (ExcepcionNegocio) exc;
			RespuestaDTO dto = new RespuestaDTO(null, excNeg.getMessage(), "-1");
			exc.printStackTrace();

			return Response.status(500).entity(dto).build();
		} else {

			RespuestaDTO dto = new RespuestaDTO(null, "Error inesperado", "-2");
			exc.printStackTrace();
			return Response.status(500).entity(dto).build();
		}

	}

}
