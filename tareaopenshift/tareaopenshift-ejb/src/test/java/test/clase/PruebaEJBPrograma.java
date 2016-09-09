package test.clase;

import javax.ejb.EJB;

import org.caferrer.testdata.junit.ArquillianUtil;
import org.caferrer.testdata.junit.TestDataUtil;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.edu.eam.ingesoft.pa2.tareaopenshift.bos.FacultadEJB;
import co.edu.eam.ingesoft.pa2.tareaopenshift.bos.ProgramaEJB;
import co.edu.eam.ingesoft.pa2.tareaopenshift.persistencia.entidades.Facultad;
import co.edu.eam.ingesoft.pa2.tareaopenshift.persistencia.entidades.Programa;


@RunWith(Arquillian.class)
public class PruebaEJBPrograma {

	@EJB
	private ProgramaEJB ejb;
	
	@EJB
	private FacultadEJB facultadejb;

	@Deployment
	public static EnterpriseArchive desplegar() {

		EnterpriseArchive ear = ArquillianUtil.createDeployment("../tareaopenshift-ear/target/tareaopenshift-ear.ear");
		ear.addAsLibraries(ShrinkWrap.create(JavaArchive.class).addClass(PruebaEJBPrograma.class));
		return ear;
	}

	@BeforeClass
	public static void inicializar() {
		TestDataUtil.ejecutarSQL("sqltest/Prueba-add.sql");
		System.err.println("inicializar");
	}

	@Test
	public void testCrearPrograma() {
		Programa pro = new Programa();
		Facultad fa = new Facultad();
		fa.setIdFacultad("3");
		fa.setNombre("Hoteleria");
		facultadejb.crear(fa);
		
		pro.setIdFacultad(fa);
		pro.setNombrePrograma("publicidad");
		pro.setIdPrograma("938");

		ejb.crear(pro);

		Programa programa = ejb.buscar("938");
		Assert.assertNotNull(programa);
	}

	@AfterClass
	public static void finalizar() {
		TestDataUtil.ejecutarSQL("sqltest/Prueba-del.sql");
		System.err.println("finalizando");
	}

}
