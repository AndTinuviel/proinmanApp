package proinman.gestion.solicitud.filtros;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebFilter(urlPatterns = { "/*" })
public class FiltroLogin {
//implements Serializable, Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7729580092165362226L;

//	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

//	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("Ingresooooo doFilter FiltroLogin");
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// Obtengo el bean que representa el usuario desde el scope sesión
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute("loginBean");

		// Proceso la URL que está requiriendo el cliente
		String urlStr = req.getRequestURL().toString().toLowerCase();
		boolean noProteger = noProteger(urlStr);
		System.out.println(urlStr + " - desprotegido=[" + noProteger + "]");

		// Si no requiere protección continúo normalmente.
		if (noProteger(urlStr)) {
			chain.doFilter(request, response);
			return;
		}

		// El usuario no está logueado
		if (loginBean == null || !loginBean.estaLogeado()) {
			res.sendRedirect(req.getContextPath() + "/login.xhtml");
			return;
		}

		// El recurso requiere protección, pero el usuario ya está logueado.
		chain.doFilter(request, response);

	}

//	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("Ingresooooo init FiltroLogin");
	}
	
	
	private boolean noProteger(String urlStr) {

		/*
		 * Este es un buen lugar para colocar y programar todos los patrones que
		 * creamos convenientes para determinar cuales de los recursos no
		 * requieren protección. Sin duda que habría que crear un mecanizmo tal
		 * que se obtengan de un archivo de configuración o algo que no requiera
		 * compilación.
		 */
		if (urlStr.endsWith("login.xhtml"))
			return true;
		if (urlStr.indexOf("/javax.faces.resource/") != -1)
			return true;
		return false;
	}

}
