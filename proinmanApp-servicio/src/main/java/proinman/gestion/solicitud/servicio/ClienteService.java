package proinman.gestion.solicitud.servicio;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import proinman.gestion.solicitud.dao.ClienteDao;
import proinman.gestion.solicitud.entity.Cliente;
import proinman.gestion.solicitud.util.exception.EntidadNoGuardadaException;

@Stateless
@LocalBean
public class ClienteService {

	@EJB
	private ClienteDao clientreDao;
	
	public List<Cliente> consultarClientes(){
		return clientreDao.obtenerTodos();
	}
	
	public Cliente consultarClientePorID(Integer codigoCliente){
		return clientreDao.obtenerPorCodigo(codigoCliente);
	}
	
	public void desactivarCliente(Cliente clienteAEliminar) throws EntidadNoGuardadaException {
		clienteAEliminar.setEstado("INA");
		actualizarCliente(clienteAEliminar);
	}
	
	public void actualizarCliente(Cliente clienteActualizar) throws EntidadNoGuardadaException {
		clientreDao.guardarOActualizar(clienteActualizar);
	}
	
	public Cliente crearCliente(Cliente cliente) throws EntidadNoGuardadaException {
		cliente.setEstado("ACT");
		clientreDao.guardar(cliente);
		return cliente;
	}
}
