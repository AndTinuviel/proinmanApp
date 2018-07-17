package proinman.gestion.solicitud.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TransactionRequiredException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;
import org.apache.log4j.Logger;
import proinman.gestion.solicitud.util.exception.EntidadNoEliminadaException;
import proinman.gestion.solicitud.util.exception.EntidadNoExisteException;
import proinman.gestion.solicitud.util.exception.EntidadNoGuardadaException;

public class BaseDaoGenerico<T, I extends Serializable> implements GenericoDao<T, I>, Serializable {

	private static final long serialVersionUID = -2637710737398286994L;

	@PersistenceContext(unitName = "proinmanPU")
	protected EntityManager em;

	private final Class<T> type;
	static final Logger log = Logger.getLogger(BaseDaoGenerico.class);

	public BaseDaoGenerico(Class<T> type) {
		this.type = type;
	}

	public T guardarOActualizar(T o) throws EntidadNoGuardadaException {
		try {
			return (T) this.em.merge(o);
		} catch (IllegalStateException e) {
			throw new EntidadNoGuardadaException(e);
		} catch (IllegalArgumentException e) {
			throw new EntidadNoGuardadaException(e);
		} catch (TransactionRequiredException e) {
			throw new EntidadNoGuardadaException(e);
		}
	}

	public void guardar(T o) throws EntidadNoGuardadaException {
		try {
			this.em.persist(o);
		} catch (EntityExistsException e) {
			throw new EntidadNoGuardadaException(e);
		} catch (IllegalStateException e) {
			throw new EntidadNoGuardadaException(e);
		} catch (IllegalArgumentException e) {
			throw new EntidadNoGuardadaException(e);
		} catch (TransactionRequiredException e) {
			throw new EntidadNoGuardadaException(e);
		}
	}

	public T obtenerPorCodigo(I id) throws EntidadNoExisteException {
		try {
			T o = this.em.find(this.type, id);
			if (o == null) {
				throw new EntidadNoExisteException(
						"No existe ".concat(this.type.getSimpleName()).concat(" con codigo: " + id));
			}
			return o;
		} catch (IllegalStateException e) {
			throw new EntidadNoExisteException(e);
		} catch (IllegalArgumentException e) {
			throw new EntidadNoExisteException(e);
		}
	}

	public void eliminar(T entidad) throws EntidadNoEliminadaException {
		try {
			T entidadEliminar = this.em.merge(entidad);
			this.em.remove(entidadEliminar);
		} catch (IllegalStateException e) {
			throw new EntidadNoEliminadaException(e);
		} catch (IllegalArgumentException e) {
			throw new EntidadNoEliminadaException(e);
		} catch (TransactionRequiredException e) {
			throw new EntidadNoEliminadaException(e);
		}
	}

	public List<T> obtenerTodos() {
		System.out.println("********************** BaseDaoGenerico ");
		CriteriaBuilder cb = this.em.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(this.type);
		Root<T> t = cq.from(this.type);
		cq.select(t);
		TypedQuery<T> tq = this.em.createQuery(cq);
		return tq.getResultList();
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public <T> List<T> obtenerTodos(Class<T> entityClass, SingularAttribute atributoOrdenamiento) {
		CriteriaBuilder cb = this.em.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(entityClass);
		Root<T> t = cq.from(entityClass);
		cq.select(t);
		if (atributoOrdenamiento != null) {
			cq.orderBy(new Order[] { cb.asc(t.get(atributoOrdenamiento)) });
		}
		TypedQuery<T> tq = this.em.createQuery(cq);
		return tq.getResultList();
	}
}
