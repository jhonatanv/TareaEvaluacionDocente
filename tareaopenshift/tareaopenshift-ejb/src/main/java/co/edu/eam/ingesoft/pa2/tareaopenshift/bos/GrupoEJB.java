package co.edu.eam.ingesoft.pa2.tareaopenshift.bos;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import co.edu.eam.ingesoft.pa2.tareaopenshift.ejbremote.GrupoRemoteEJB;
import co.edu.eam.ingesoft.pa2.tareaopenshift.excepciones.ExcepcionNegocio;
import co.edu.eam.ingesoft.pa2.tareaopenshift.implementacion.EJBGenerico;
import co.edu.eam.ingesoft.pa2.tareaopenshift.persistencia.entidades.Grupo;

@LocalBean
@Stateless
@Remote(GrupoRemoteEJB.class)
public class GrupoEJB extends EJBGenerico<Grupo> implements GrupoRemoteEJB {

	@Override
	public Class getClase() {
		return Grupo.class;
	}
	
	public Grupo buscar(Object pk) {
		return dao.buscar(pk);
	}

	public void crear(Grupo grupo) {
		if (buscar(grupo.getIdGrupo()) == null) {
			dao.crear(grupo);
		} else {
			throw new ExcepcionNegocio("Este grupo ya se encuentro registrado");
		}
	}

	
}
