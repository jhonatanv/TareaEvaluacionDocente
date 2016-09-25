package co.edu.eam.ingesoft.pa2.tareaopenshift.interceptores;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.ws.rs.NameBinding;

@Retention(RetentionPolicy.RUNTIME)
@Target(value={ElementType.TYPE,ElementType.METHOD})
@NameBinding// indicar que ese usara como interceptor REST
public @interface Secured {

	
	
}
