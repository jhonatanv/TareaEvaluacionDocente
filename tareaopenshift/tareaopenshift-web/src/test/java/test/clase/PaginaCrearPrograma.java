package test.clase;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.page.Location;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

@Location("gestionProgramas.jsf")
public class PaginaCrearPrograma {

	
	@Drone
	private WebDriver browser;
	
	@FindBy(id="codigo")
	private WebElement codigo;
	
	@FindBy(id="nombre")
	private WebElement nombre;
	
	@FindBy(id="comboFacultad")
	private Select comboFacultad;
	
	@FindBy(id="botonPro")
	private WebElement botonPro;
	
	@FindBy(id="msj")
	private WebElement msj;
	
	public String crearProgramas(){
		codigo.sendKeys("98989");
		nombre.sendKeys("diseño");
		comboFacultad.selectByValue("2222");
		Graphene.guardAjax(botonPro).click();
		Graphene.waitModel().until().element(msj).is().present();		
		return msj.getText();
	}
	
}
