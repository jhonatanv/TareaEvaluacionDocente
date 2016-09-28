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
	private GrupoEJB grupoEJB;
	
	@EJB
	private DocenteEJB docenteEJB;

	@EJB
	private ProgramaEJB programaEJB;

	@EJB
	private FacultadEJB facultadEJB;

	private Programa programa;

	private Facultad facult;

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
					facult = facultadEJB.buscar(dto.get(i).getAsignatura().getPrograma().getFacultad().getCodigo());

				} else {

					facult = new Facultad();

					facult.setIdFacultad(dto.get(i).getAsignatura().getPrograma().getFacultad().getCodigo());
					facult.setNombre(dto.get(i).getAsignatura().getPrograma().getFacultad().getNombre());
					facultadEJB.crear(facult);
				}

				if (programaEJB.buscar(dto.get(i).getAsignatura().getPrograma().getCodigo()) != null) {
					programa = programaEJB.buscar(dto.get(i).getAsignatura().getPrograma().getCodigo());

				} else {

					programa = new Programa();

					programa.setIdFacultad(facult);
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

	/**
	 * lista grupos
	 * @param cod, codigo estudiante
	 * @param ced, cedula estudiante
	 * @return lista
	 */
	public List<Grupo> listarGrupos(String cod, String ced) {
		ServiciosEducativosService cliente = new ServiciosEducativosService();
		ServiciosAcademicos servicio = cliente.getServiciosAcademicos();

		BindingProvider bp = (BindingProvider) servicio;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
				"http://174.142.65.144:28080/eamweb/serviciosAcademicos");

		List<Curso> dto = servicio.consultarCursosEstudiante(cod, ced);
		List<Grupo> listaGrupo = new ArrayList<>();
		for (int i = 0; i < dto.size(); i++) {

			facult = facultadEJB.buscar(dto.get(i).getAsignatura().getPrograma().getFacultad().getCodigo());
			if (facult == null) {
				
				facult = new Facultad();
				facult.setIdFacultad(dto.get(i).getAsignatura().getPrograma().getFacultad().getCodigo());
				facult.setNombre(dto.get(i).getAsignatura().getPrograma().getFacultad().getNombre());
				facultadEJB.crear(facult);
			}

			programa = programaEJB.buscar(dto.get(i).getAsignatura().getPrograma().getCodigo());
			
			if (programa == null) {
				
				programa = new Programa();
				programa.setIdFacultad(facult);
				programa.setIdPrograma(dto.get(i).getAsignatura().getPrograma().getCodigo());
				programa.setNombrePrograma(dto.get(i).getAsignatura().getPrograma().getNombre());
				programaEJB.crear(programa);
			}

			asignatura = asignaturaEJB.buscar(dto.get(i).getAsignatura().getCodigo());
			
			if (asignatura == null) {
				
				asignatura = new Asignatura();
				asignatura.setIdAsignatura(dto.get(i).getAsignatura().getCodigo());
				asignatura.setIdPrograma(programa);
				asignatura.setNombreAsignatura(dto.get(i).getAsignatura().getNombre());
				asignaturaEJB.crear(asignatura);
			}

			Facultad fac = facultadEJB.buscar(dto.get(i).getDocente().getPrograma().getFacultad().getCodigo());
			
			if (fac == null) {
				fac = new Facultad();
				fac.setIdFacultad(dto.get(i).getDocente().getPrograma().getFacultad().getCodigo());
				fac.setNombre(dto.get(i).getDocente().getPrograma().getFacultad().getNombre());
				facultadEJB.crear(fac);
			}

			Programa programa = programaEJB.buscar(dto.get(i).getDocente().getPrograma().getCodigo());
			
			if (programa == null) {
				
				programa = new Programa();
				programa.setIdFacultad(fac);
				programa.setIdPrograma(dto.get(i).getDocente().getPrograma().getCodigo());
				programa.setNombrePrograma(dto.get(i).getDocente().getPrograma().getNombre());
				programaEJB.crear(programa);
			}

			docente = docenteEJB.buscar(dto.get(i).getDocente().getDocumentoidentificacion());
			
			if (docente == null) {
				
				docente = new Docente();
				docente.setApellido(dto.get(i).getDocente().getApellido());
				docente.setId(dto.get(i).getDocente().getDocumentoidentificacion());
				docente.setIdPrograma(programa);
				docente.setNombre(dto.get(i).getDocente().getNombre());
				docenteEJB.crear(docente);
			}

			Grupo g = grupoEJB.buscar(dto.get(i).getId());
			if (g == null) {
				
				g = new Grupo();
				g.setAnio(GregorianCalendar.getInstance().getTime());
				g.setGrupo(dto.get(i).getGrupo().toString());
				g.setIdAsignatura(asignatura);
				g.setIdDocente(docente);
				g.setIdGrupo(dto.get(i).getId());
				g.setPeriodo(2);
				grupoEJB.crear(g);
			}
			
			listaGrupo.add(g);
		}
		return listaGrupo;
	}

}