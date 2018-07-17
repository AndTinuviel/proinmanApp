package proinman.gestion.solicitud.ws;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import proinman.gestion.solicitud.entity.UsuarioRol;
import proinman.gestion.solicitud.servicio.UsuarioRolService;
import proinman.gestion.solicitud.servicio.UsuarioService;

@Path("/gestionUsuario")
public class UsuarioRest
{
  @EJB
  private UsuarioService usuarioService;
  @EJB
  private UsuarioRolService usuarioRolService;
  
  @POST
  @Path("/loguear")
  @Produces({"application/json"})
  public boolean loguearUsuario(@QueryParam("username") String username, @QueryParam("password") String password)
  {
    return this.usuarioService.loguearUsuario(username, password);
  }
  
  @POST
  @Path("/asignarRolAUsuario")
  @Produces({"application/json"})
  @Consumes({"application/json"})
  public UsuarioRol asignarRolAUsuario(@QueryParam("codigoRol") Integer codigoRol, @QueryParam("codigoUsuario") Integer codigoUsuario)
  {
    return this.usuarioRolService.asignarRolAUsuario(codigoRol, codigoUsuario);
  }
}
