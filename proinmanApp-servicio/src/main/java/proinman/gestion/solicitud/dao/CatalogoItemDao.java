package proinman.gestion.solicitud.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import proinman.gestion.solicitud.entity.CatalogoItem;

@Stateless
@LocalBean
public class CatalogoItemDao extends BaseDaoGenerico<CatalogoItem, Serializable> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4089466035865047645L;

	
	public CatalogoItemDao() {
		super(CatalogoItem.class);
	}

	public List<CatalogoItem> obtenerCatalogoMateriales(){
		String consulta = "select c from CatalogoItem c where c.tipoItem = 'MATERIAL' and c.estado = 'ACT' ";
		List<CatalogoItem> listaCatalogoMateriales = this.em.createQuery(consulta, CatalogoItem.class).getResultList();
		return listaCatalogoMateriales;
	}
	

	public List<CatalogoItem> obtenerCatalogoManoDeObra(){
		String consulta = "select c from CatalogoItem c where c.tipoItem = 'MANO_DE_OBRA' and c.estado = 'ACT' ";
		List<CatalogoItem> listaCatalogoMateriales = this.em.createQuery(consulta, CatalogoItem.class).getResultList();
		return listaCatalogoMateriales;
	}

}
