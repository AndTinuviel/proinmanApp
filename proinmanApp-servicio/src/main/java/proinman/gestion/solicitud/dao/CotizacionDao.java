package proinman.gestion.solicitud.dao;

import java.io.Serializable;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import proinman.gestion.solicitud.entity.Cotizacion;

@Stateless
@LocalBean
public class CotizacionDao extends BaseDaoGenerico<Cotizacion, Serializable>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7325449453514109787L;
	
	public CotizacionDao() {
		super(Cotizacion.class);
	}
	
	public void flush(){
		em.flush();
	}

	public Cotizacion consultarCotizacionPorCodigoSolicitud(Integer codigoSolicitud) {
			String consulta = "select c from Cotizacion c inner join c.solicitud s "
					+ " where s.codigoSolicitud = :codigoSolicitud and estado = 'ACT' ";

			Cotizacion cotizacion = this.em.createQuery(consulta, Cotizacion.class)
					.setParameter("codigoSolicitud", codigoSolicitud).getSingleResult();
			return cotizacion;
	}

}
