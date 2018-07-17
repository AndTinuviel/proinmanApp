
package proinman.gestion.solicitud.filtros;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.servlet.ServletContext;

/**
 * 
 * Clase Controlador Basico
 * 
 * 
 * 
 */

public class ControladorBase {

	/**
	 * @return
	 */
	protected HttpServletRequest getHttpRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}

	/**
	 * @return
	 */
	protected HttpServletResponse getHttpResponse() {
		return (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
	}

	/**
	 * @return
	 */
	protected HttpSession getHttpSession() {
		return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	}

	/**
	 * @param summary
	 * @param detail
	 */
	protected void addErrorMessage(final String summary, final String detail) {
		FacesMessage errorMessage = new FacesMessage();
		errorMessage.setSummary(summary);
		errorMessage.setDetail(detail);
		errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
		FacesContext.getCurrentInstance().addMessage(null, errorMessage);
	}

	/**
	 * Para la creaci�n de multiples mensajes de error que se encuentren en una cadena de texto separados por
	 * ConstanteAplicacion.SEPARADOR_MENSAJES
	 * 
	 * @param summary
	 */
	protected void addMultipleErrorMessage(final String summary) {
		String[] mensajes = summary.split("/&/");

		for (int i = 0; i < mensajes.length; i++) {
			FacesMessage errorMessage = new FacesMessage();
			errorMessage.setSummary(mensajes[i]);
			errorMessage.setDetail(mensajes[i]);
			errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, errorMessage);
		}
	}

	/**
	 * @param summary
	 * @param detail
	 */
	protected void addInfoMessage(final String summary, final String detail) {
		FacesMessage infoMessage = new FacesMessage();
		infoMessage.setSummary("INFO-" + summary);
		infoMessage.setDetail(detail);
		infoMessage.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance().addMessage(null, infoMessage);
	}

	/**
	 * @param summary
	 * @param detail
	 */
	protected void addSuccessMessage(final String summary, final String detail) {
		FacesMessage infoMessage = new FacesMessage();
		infoMessage.setSummary(summary);
		infoMessage.setDetail(detail);
		infoMessage.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance().addMessage(null, infoMessage);
	}

	/**
	 * @param summary
	 * @param detail
	 */
	protected void addWarningMessage(final String summary, final String detail) {
		FacesMessage warningMessage = new FacesMessage();
		warningMessage.setSummary(summary);
		warningMessage.setDetail(detail);
		warningMessage.setSeverity(FacesMessage.SEVERITY_WARN);
		FacesContext.getCurrentInstance().addMessage(null, warningMessage);
	}

	/**
	 * @return
	 */
	public String getUsuarioConectado() {
		return getHttpRequest().getRemoteUser();
	}

	/**
	 * M�todo que carga el el archivo de propiedades y obtiene la descripci�n del mensaje mediante la clave
	 * 
	 * @param clave
	 * @return
	 */
	public String obtenerEtiquetaPorClave(String clave) {
		ResourceBundle resources = getResourceBundle();
		return resources.getString(clave);
	}

	/**
	 * M�todo que carga el el archivo de propiedades y obtiene la descripci�n una validaci�n utilizada en la capa web
	 * 
	 * @param clave
	 * @return
	 */
	public String obtenerValidacionPorClave(String clave) {
		ResourceBundle resources = getResourceBundleValidacionWeb();
		return resources.getString(clave);
	}

	/**
	 * M�todo que obtiene la direccion ip del cliente
	 * @return
	 */
	public String getHostAddress()
	{
		String ipCliente="";
		try {
			ipCliente = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			return "";
		}
		return ipCliente;
	}
	
	protected ServletContext getServletContext() {
		return (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
	}
	
	public ResourceBundle getResourceBundle() {
		return ResourceBundle.getBundle("fedatarios", new Locale("es"));
	}

	public ResourceBundle getResourceBundleValidacionWeb() {
		return ResourceBundle.getBundle("mensajes_validacion", new Locale("es"));
	}

	public ResourceBundle getResourceBundleException() {
		return ResourceBundle.getBundle("mensajes_excepcion", new Locale("es"));
	}

	public String getExcepcionEtiquetaXClave(String clave) {
		ResourceBundle resources = getResourceBundleException();
		return resources.getString(clave);
	}
	
	protected Object obtenerObjetoDelAmbitoFlash(String nombreObjeto){
		return FacesContext.getCurrentInstance().getExternalContext().getFlash().get(nombreObjeto);
	}
	
	protected void colocarObjetoEnAmbitoFlash(String nombreObjeto,Object objeto){
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put(nombreObjeto, objeto);
	}
}
