package test.clase;

import org.caferrer.testdata.junit.ArquillianUtil;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.page.InitialPage;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(Arquillian.class)
public class PruebaCrearPrograma {

	@Drone
	private WebDriver browser;
	
	@Deployment
	public static EnterpriseArchive desplegar(){
		
		EnterpriseArchive ear= ArquillianUtil.createDeployment("../tareaopenshift-ear/target/tareaopenshift-ear.ear");
		ear.addAsLibraries(ShrinkWrap.create(JavaArchive.class).addClass(PruebaCrearPrograma.class));
		
		return ear;
	}
	
	@Test
	@RunAsClient
	public void testCrearPrograma(@InitialPage PaginaCrearPrograma pag){
		
		String respu = pag.crearProgramas();
		ArquillianUtil.takeScreenshot(browser, "testCrearPregunta.jpg");
		Assert.assertEquals("registro con exito de programa", respu);
	}
}
