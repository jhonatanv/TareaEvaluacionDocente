package co.edu.eam.ingesoft.pa2.tareaopenshift.bos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.xml.ws.BindingProvider;

import co.edu.eam.ingesoft.pa2.tareaopenshift.entidades.Curso;
import co.edu.eam.ingesoft.pa2.tareaopenshift.entidades.Estudiante;
import co.edu.eam.ingesoft.pa2.tareaopenshift.entidades.ServiciosAcademicos;
import co.edu.eam.ingesoft.pa2.tareaopenshift.entidades.ServiciosEducativosService;
import co.edu.eam.ingesoft.pa2.tareaopenshift.persistencia.entidades.Asignatura;
import co.edu.eam.ingesoft.pa2.tareaopenshift.persistencia.entidades.Docente;
import co.edu.eam.ingesoft.pa2.tareaopenshift.persistencia.entidades.Facultad;
import co.edu.eam.ingesoft.pa2.tareaopenshift.persistencia.entidades.Grupo;
import co.edu.eam.ingesoft.pa2.tareaopenshift.persistencia.entidades.PregEval;
import co.edu.eam.ingesoft.pa2.tareaopenshift.persistencia.entidades.Pregunta;
import co.edu.eam.ingesoft.pa2.tareaopenshift.persistencia.entidades.Programa;

@LocalBean
@Stateless
public class WSLDEJB implements Serializable {

	@EJB
	private AsignaturaEJB asignaturaEJB;

	@EJB
	private ProgramaEJB programaEJB;

	@EJB
	private FacultadEJB facultadEJB;

	private Programa programa;

	private Facultad facultad;

	private Asignatura asignatura;

	private Docente docente;

	public boolean crear(String codigo, String cedula) {
		try {

			ServiciosEducativosService cliente = new ServiciosEducativosService();
			ServiciosAcademicos servicio = cliente.getServiciosAcademicos();

			BindingProvider bindingP = (BindingProvider) servicio;
			bindingP.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
					"http://174.142.65.144:28080/eamweb/serviciosAcademicos");

			List<Curso> dto = servicio.consultarCursosEstudiante(codigo, cedula);
			for (int i = 0; i < dto.size(); i++) {

				if (facultadEJB.buscar(dto.get(i).getAsignatura().getPrograma().getFacultad().getCodigo()) != null) {
					facultad = facultadEJB.buscar(dto.get(i).getAsignatura().getPrograma().getFacultad().getCodigo());

				} else {

					facultad = new Facultad();

					facultad.setIdFacultad(dto.get(i).getAsignatura().getPrograma().getFacultad().getCodigo());
					facultad.setNombre(dto.get(i).getAsignatura().getPrograma().getFacultad().getNombre());
					facultadEJB.crear(facultad);
				}

				if (programaEJB.buscar(dto.get(i).getAsignatura().getPrograma().getCodigo()) != null) {
					programa = programaEJB.buscar(dto.get(i).getAsignatura().getPrograma().getCodigo());

				} else {

					programa = new Programa();

					programa.setIdFacultad(facultad);
					programa.setIdPrograma(dto.get(i).getAsignatura().getPrograma().getCodigo());
					programa.setNombrePrograma(dto.get(i).getAsignatura().getPrograma().getNombre());
					programaEJB.crear(programa);
				}

				if (asignaturaEJB.buscar(dto.get(i).getAsignatura().getCodigo()) == null) {

					asignatura = new Asignatura();

					asignatura.setIdAsignatura(dto.get(i).getAsignatura().getCodigo());
					asignatura.setIdPrograma(programa);
					asignatura.setNombreAsignatura(dto.get(i).getAsignatura().getNombre());
					asignaturaEJB.crear(asignatura);
				}

			}
			if (!dto.isEmpty()) {
				return true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	public boolean buscarEstudiante(String codigo, String cedula) {

		ServiciosEducativosService cliente = new ServiciosEducativosService();
		ServiciosAcademicos servicio = cliente.getServiciosAcademicos();

		BindingProvider bindingP = (BindingProvider) servicio;
		bindingP.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
				"http://174.142.65.144:28080/eamweb/serviciosAcademicos");

		Estudiante estudiante = servicio.consultaEstudiante(codigo, cedula);

		if (estudiante != null) {
			return true;
		} else {
			return false;
		}
	}

	public List<Grupo> listarGrupos(String cod, String ced) {

		ServiciosEducativosService cliente = new ServiciosEducativosService();
		ServiciosAcademicos servicio = cliente.getServiciosAcademicos();

		BindingProvider bp = (BindingProvider) servicio;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
				"http://174.142.65.144:28080/eamweb/serviciosAcademicos");

		List<Curso> dto = servicio.consultarCursosEstudiante(cod, ced);
		List<Grupo> lista = new ArrayList<>();
		for (int i = 0; i < dto.size(); i++) {
			facultad = new Facultad();
			facultad.setIdFacultad(dto.get(i).getAsignatura().getPrograma().getFacultad().getCodigo());
			facultad.setNombre(dto.get(i).getAsignatura().getPrograma().getFacultad().getNombre());
			programa = new Programa();
			programa.setIdFacultad(facultad);
			programa.setIdPrograma(dto.get(i).getAsignatura().getPrograma().getCodigo());
			programa.setNombrePrograma(dto.get(i).getAsignatura().getPrograma().getNombre());
			asignatura = new Asignatura();
			asignatura.setIdAsignatura(dto.get(i).getAsignatura().getCodigo());
			asignatura.setIdPrograma(programa);
			asignatura.setNombreAsignatura(dto.get(i).getAsignatura().getNombre());

			Facultad facul = new Facultad();
			facul.setIdFacultad(dto.get(i).getDocente().getPrograma().getFacultad().getCodigo());
			facul.setNombre(dto.get(i).getDocente().getPrograma().getFacultad().getNombre());
			Programa program = new Programa();
			program.setIdFacultad(facultad);
			program.setIdPrograma(dto.get(i).getDocente().getPrograma().getCodigo());
			program.setNombrePrograma(dto.get(i).getDocente().getPrograma().getNombre());

			docente = new Docente();
			docente.setApellido(dto.get(i).getDocente().getApellido());
			docente.setId(dto.get(i).getDocente().getDocumentoidentificacion());
			docente.setIdPrograma(program);
			docente.setNombre(dto.get(i).getDocente().getNombre());

			Grupo grupo = new Grupo();
			grupo.setAnio(GregorianCalendar.getInstance().getTime());
			grupo.setGrupo(dto.get(i).getGrupo().toString());
			grupo.setIdAsignatura(asignatura);
			grupo.setIdDocente(docente);
			grupo.setIdGrupo(dto.get(i).getId());
			grupo.setPeriodo(2);

			lista.add(grupo);
		}
		return lista;
	}
	
	
	

}