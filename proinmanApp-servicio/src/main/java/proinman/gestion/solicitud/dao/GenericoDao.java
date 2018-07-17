package proinman.gestion.solicitud.dao;

import java.io.Serializable;
import java.util.List;
import proinman.gestion.solicitud.util.exception.EntidadNoEliminadaException;
import proinman.gestion.solicitud.util.exception.EntidadNoExisteException;
import proinman.gestion.solicitud.util.exception.EntidadNoGuardadaException;

public abstract interface GenericoDao<T, I extends Serializable>
{
  public abstract T guardarOActualizar(T paramT)
    throws EntidadNoGuardadaException;
  
  public abstract void guardar(T paramT)
    throws EntidadNoGuardadaException;
  
  public abstract T obtenerPorCodigo(I paramI)
    throws EntidadNoExisteException;
  
  public abstract void eliminar(T paramT)
    throws EntidadNoEliminadaException;
  
  public abstract List<T> obtenerTodos();
}
