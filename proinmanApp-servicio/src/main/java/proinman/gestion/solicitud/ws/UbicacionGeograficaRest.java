package proinman.gestion.solicitud.ws;

import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import proinman.gestion.solicitud.entity.UbicacionGeografica;
import proinman.gestion.solicitud.servicio.UbicacionGeograficaService;

@Path("/ubicacionGeografica")
public class UbicacionGeograficaRest
{
  @EJB
  private UbicacionGeograficaService ubicacionGeograficaService;
  
  @GET
  @Path("/consultarRegiones")
  @Produces({"application/json"})
  public List<UbicacionGeografica> consultarRegiones()
  {
    return this.ubicacionGeograficaService.consultarRegiones();
  }
  
  @GET
  @Path("/consultarProvincias")
  @Produces({"application/json"})
  public List<UbicacionGeografica> consultarProvincias(Integer codigoRegion)
  {
    return this.ubicacionGeograficaService.consultarProvinciasPorRegion(codigoRegion);
  }
  
  @GET
  @Path("/consultarCantones")
  @Produces({"application/json"})
  public List<UbicacionGeografica> consultarCantones(Integer codigoProvincia)
  {
    return this.ubicacionGeograficaService.consultarCantonesPorProvincia(codigoProvincia);
  }
  
  @GET
  @Path("/consultarParroquias")
  @Produces({"application/json"})
  public List<UbicacionGeografica> consultarParroquias(Integer codigoCanton)
  {
    return this.ubicacionGeograficaService.consultarParroquiasPorCanton(codigoCanton);
  }
}
