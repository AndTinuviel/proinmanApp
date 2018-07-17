package proinman.gestion.solicitud.ws;

import java.io.PrintStream;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import proinman.gestion.solicitud.entity.Solicitud;
import proinman.gestion.solicitud.servicio.SolicitudService;
import proinman.gestion.solicitud.util.exception.EntidadNoGuardadaException;

@Path("/gestionSolicitud")
public class SolicitudRest
{
  @EJB
  private SolicitudService solicitudService;
  
  @POST
  @Path("/crear")
  @Produces({"application/json"})
  public double potenciarJson(@QueryParam("base") double base, @QueryParam("exponente") double exponente)
  {
    try
    {
      return this.solicitudService.crearSolicitud();
    }
    catch (EntidadNoGuardadaException e)
    {
      System.out.println("Error");
    }
    return 0.0D;
  }
  
  @GET
  @Path("/consultar")
  @Produces({"application/xml", "application/json"})
  public Response consultarJson()
  {
    return Response.ok(this.solicitudService.consultarSolicitud(1)).build();
  }
  
  @GET
  @Path("/consultar2")
  @Produces({"application/xml", "application/json"})
  public Solicitud consultarJson2()
  {
    return this.solicitudService.consultarSolicitud(1);
  }
  
  @GET
  @Path("/test")
  @Produces({"application/xml", "application/json"})
  public String mostrarTest()
  {
    return "Hola mundo";
  }
}
