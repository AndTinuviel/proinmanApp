package proinman.gestion.solicitud.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import proinman.gestion.solicitud.entity.UbicacionGeografica;

@Stateless
@LocalBean
public class UbicacionGeograficaDao extends BaseDaoGenerico<UbicacionGeografica, Serializable> {
	private static final long serialVersionUID = 7037196763753039492L;

	public UbicacionGeograficaDao() {
		super(UbicacionGeografica.class);
	}

	public List<UbicacionGeografica> consultarUbicacionesPorNivelYPadre(Integer nivel, Integer codigoPadre) {
		String consulta = "select u from UbicacionGeografica u where u.nivel = :nivel and ubicacionGeograficaPadre.codigoUbicacionGeografica = :codigoPadre and estado = 'ACT' ";

		List<UbicacionGeografica> listaUbicacionGeografica = this.em.createQuery(consulta, UbicacionGeografica.class)
				.setParameter("nivel", nivel).setParameter("codigoPadre", codigoPadre).getResultList();
		return listaUbicacionGeografica;
	}

	public List<UbicacionGeografica> consultarRegiones(Integer nivel) {
		String consulta = "select u from UbicacionGeografica u where u.nivel = :nivel and ubicacionGeograficaPadre is null and estado = 'ACT' ";

		List<UbicacionGeografica> listaUbicacionGeografica = this.em.createQuery(consulta, UbicacionGeografica.class)
				.setParameter("nivel", nivel).getResultList();
		return listaUbicacionGeografica;
	}
}
