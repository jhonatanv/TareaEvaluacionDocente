package co.edu.eam.ingesoft.pa2.tareaopenshift.seguridad;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.LogRecord;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.eam.ingesoft.pa2.tareaopenshift.persistencia.entidades.Acceso;

//@WebFilter(urlPatterns = "/paginas/*")
public class FiltroAcceso implements Filter {

	@Inject
	private SesionBean sesion;

	org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(FiltroAcceso.class);

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpreq = (HttpServletRequest) request;
		HttpServletResponse httpresp = (HttpServletResponse) resp;

		String urlCompleta = httpreq.getRequestURI().toString();
		String contextpaht = httpreq.getContextPath();

		logger.info("url=" + urlCompleta + ".path=" + contextpaht);
		String url = urlCompleta.substring(contextpaht.length());

		logger.info("URL o filtrar" + url);

		if (sesion.isLogged()) {
			boolean exito = false;
			if (url.equals("/paginas/inicio.jsf")) {
				exito = true;
			} else {
				List<Acceso> accesos = sesion.getAccesos();
				for (Acceso acceso : accesos) {
					if (acceso.getUrl().equals(url)) {
						exito = true;
					}
				}
			}
			if (!exito) {
				httpresp.sendRedirect(httpreq.getContextPath() + "/paginas/inicio.jsf");
			}else{
				chain.doFilter(request, resp);
			}
		}else{
			httpresp.sendRedirect(httpreq.getContextPath());
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
