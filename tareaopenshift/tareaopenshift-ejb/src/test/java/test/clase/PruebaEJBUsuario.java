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

import co.edu.eam.ingesoft.pa2.tareaopenshift.bos.UsuarioEJB;
import co.edu.eam.ingesoft.pa2.tareaopenshift.persistencia.entidades.Usuario;

@RunWith(Arquillian.class)
public class PruebaEJBUsuario {

	@EJB
	private UsuarioEJB ejb;
	
	@Deployment
	public static EnterpriseArchive desplegar(){
		
		EnterpriseArchive ear= ArquillianUtil.createDeployment("../tareaopenshift-ear/target/tareaopenshift-ear.ear");
		ear.addAsLibraries(ShrinkWrap.create(JavaArchive.class).addClass(PruebaEJBUsuario.class));
		return ear;
	}
	
	@BeforeClass
	public static void inicializar() {
		TestDataUtil.ejecutarSQL("sqltest/Prueba-add.sql");
		System.err.println("inicializar");
	}
	
	@Test
	public void testCrearUsuario(){
		Usuario usu=new Usuario();
		usu.setId(1212);
		usu.setNombre("steven");
		usu.setApellido("vanegas");
		usu.setUsuario("lola");
		usu.setPassword("1234");
		
		ejb.crear(usu);
		
		Usuario usuario=ejb.buscar(1212);
		Assert.assertNotNull(usuario);
		
	}
	
	@AfterClass
	public static void finalizar() {
		TestDataUtil.ejecutarSQL("sqltest/Prueba-del.sql");
		System.err.println("finalizando");
	}


}
