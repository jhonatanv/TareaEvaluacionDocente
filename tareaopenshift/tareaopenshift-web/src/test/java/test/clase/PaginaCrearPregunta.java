package test.clase;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.page.Location;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Location("paginas/gestionPreguntas.jsf")
public class PaginaCrearPregunta {

	@Drone
	private WebDriver browser;
	
	@FindBy(id="idpregunta")
	private WebElement idpregunta;
	
	@FindBy(id="preguntas")
	private WebElement preguntas;
	
	@FindBy(id="valor")
	private WebElement valor;
	
	@FindBy(id="botonP")
	private WebElement botonP;
	
	@FindBy(id="msj")
	private WebElement msj;
	
	public String crearPreguntas(){
		idpregunta.sendKeys("123221");
		preguntas.sendKeys("hola mundo, como estas");
		valor.sendKeys("20");		
		Graphene.guardAjax(botonP).click();
		Graphene.waitModel().until().element(msj).is().present();		
		return msj.getText();
	}
}
