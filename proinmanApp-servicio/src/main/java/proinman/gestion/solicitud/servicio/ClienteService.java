package proinman.gestion.solicitud.servicio;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import proinman.gestion.solicitud.dao.ClienteDao;
import proinman.gestion.solicitud.entity.Cliente;

@Stateless
@LocalBean
public class ClienteService {

	@EJB
	private ClienteDao clientreDao;
	
	public List<Cliente> consultarClientes(){
		return clientreDao.obtenerTodos();
	}
	
}
