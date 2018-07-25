package proinman.gestion.solicitud.servicio;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import proinman.gestion.solicitud.dao.CatalogoItemDao;
import proinman.gestion.solicitud.entity.CatalogoItem;

@Stateless
@LocalBean
public class CatalogoItemService {

	@EJB
	private CatalogoItemDao catalogoItemDao;
	
	public List<CatalogoItem> buscarTodosCatalogoItems(){
		return catalogoItemDao.obtenerTodos();
	}
	
	public List<CatalogoItem> buscarMateriales(){
		return catalogoItemDao.obtenerCatalogoMateriales();
	}
	
	public List<CatalogoItem> buscarManoDeObra(){
		return catalogoItemDao.obtenerCatalogoManoDeObra();
	}
	
	
}
