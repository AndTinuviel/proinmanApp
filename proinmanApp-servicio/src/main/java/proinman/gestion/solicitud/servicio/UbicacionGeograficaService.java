package proinman.gestion.solicitud.servicio;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import proinman.gestion.solicitud.dao.UbicacionGeograficaDao;
import proinman.gestion.solicitud.entity.UbicacionGeografica;

@Stateless
public class UbicacionGeograficaService
{
  @EJB
  private UbicacionGeograficaDao ubicacionGeograficaDao;
  
  public List<UbicacionGeografica> consultarRegiones()
  {
    return this.ubicacionGeograficaDao.consultarRegiones(Integer.valueOf(1));
  }
  
  public List<UbicacionGeografica> consultarProvinciasPorRegion(Integer codigoRegion)
  {
    return this.ubicacionGeograficaDao.consultarUbicacionesPorNivelYPadre(Integer.valueOf(2), codigoRegion);
  }
  
  public List<UbicacionGeografica> consultarCantonesPorProvincia(Integer codigoProvincia)
  {
    return this.ubicacionGeograficaDao.consultarUbicacionesPorNivelYPadre(Integer.valueOf(3), codigoProvincia);
  }
  
  public List<UbicacionGeografica> consultarParroquiasPorCanton(Integer codigoCanton)
  {
    return this.ubicacionGeograficaDao.consultarUbicacionesPorNivelYPadre(Integer.valueOf(4), null);
  }
}
