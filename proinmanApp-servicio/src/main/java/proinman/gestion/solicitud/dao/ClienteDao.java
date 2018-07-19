package proinman.gestion.solicitud.dao;

import java.io.Serializable;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import proinman.gestion.solicitud.entity.Cliente;

@Stateless
@LocalBean
public class ClienteDao extends BaseDaoGenerico<Cliente, Serializable>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3317115641566401803L;
	
	public ClienteDao() {
		super(Cliente.class);
		// TODO Auto-generated constructor stub
	}

}
