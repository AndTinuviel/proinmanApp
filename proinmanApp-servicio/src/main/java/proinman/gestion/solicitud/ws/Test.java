package proinman.gestion.solicitud.ws;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@RequestScoped
@Path("")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class Test {
	@GET
	@Path("/consultar")
	@Produces({ "application/json" })
	public String consultarJson() {
		return "Hola mundo";
	}
}
