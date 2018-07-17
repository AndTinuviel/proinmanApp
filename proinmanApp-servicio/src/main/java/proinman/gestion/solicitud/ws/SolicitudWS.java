package proinman.gestion.solicitud.ws;

import javax.jws.WebService;

@WebService
public class SolicitudWS
{
  public String sayHello(String name)
  {
    return "Hola mundo :)";
  }
}
