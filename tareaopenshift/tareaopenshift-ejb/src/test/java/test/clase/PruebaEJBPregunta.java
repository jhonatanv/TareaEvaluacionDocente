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

import co.edu.eam.ingesoft.pa2.tareaopenshift.bos.PreguntaEJB;
import co.edu.eam.ingesoft.pa2.tareaopenshift.persistencia.entidades.Pregunta;

@RunWith(Arquillian.class)
public class PruebaEJBPregunta {

	@EJB
	private PreguntaEJB ejb;

	@Deployment
	public static EnterpriseArchive desplegar() {

		EnterpriseArchive ear = ArquillianUtil.createDeployment("../tareaopenshift-ear/target/tareaopenshift-ear.ear");
		ear.addAsLibraries(ShrinkWrap.create(JavaArchive.class).addClass(PruebaEJBPregunta.class));
		return ear;
	}

	@BeforeClass
	public static void inicializar() {
		TestDataUtil.ejecutarSQL("sqltest/Prueba-add.sql");
		System.err.println("inicializar");
	}

	@Test
	public void testCrearPregunta() {
		Pregunta pre = new Pregunta();
		pre.setIdPregunta(1912012);
		pre.setTexto("como estas?");
		pre.setValor(20);

		ejb.crear(pre);

		Pregunta pregunta = ejb.buscar(1912012);
		Assert.assertNotNull(pregunta);

	}

	@AfterClass
	public static void finalizar() {
		TestDataUtil.ejecutarSQL("sqltest/Prueba-del.sql");
		System.err.println("finalizando");
	}

}
