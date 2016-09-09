/**
 * 
 */
package co.edu.eam.ingesoft.pa2.tareaopenshift.bos;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import co.edu.eam.ingesoft.pa2.tareaopenshift.ejbremote.ProgramaRemoteEJB;
import co.edu.eam.ingesoft.pa2.tareaopenshift.excepciones.ExcepcionNegocio;
import co.edu.eam.ingesoft.pa2.tareaopenshift.implementacion.EJBGenerico;
import co.edu.eam.ingesoft.pa2.tareaopenshift.persistencia.entidades.Programa;
import co.edu.eam.ingesoft.pa2.tareaopenshift.persistencia.entidades.Usuario;

/**
 * @author jhonatan
 *
 */
@LocalBean
@Stateless
@Remote(ProgramaRemoteEJB.class)
public class ProgramaEJB extends EJBGenerico<Programa> implements ProgramaRemoteEJB {

	@Override
	public List<Programa> listarPrograma() {

		return dao.ejecutarNamedQuery(Programa.LISTA_PROGRAMAS);
	}

	public Programa buscar(Object pk) {
		return dao.buscar(pk);
	}
	
	public void crear(Programa pro){
		
		dao.crear(pro);
	
	}
	
	

	

	@Override
	public Class getClase() {
		// TODO Auto-generated method stub
		return Programa.class;
	}

}
