package obligatorioAraujoSolari.Obligatorio.dominio;

import obligatorioAraujoSolari.Obligatorio.excepciones.PeajeException;

public interface Estado {
    String getNombreEstado();
    String getDescripcion();
    boolean puedeLoguear();
    boolean puedeRegistrarTransito();
    boolean aplicaBonificacion();
    boolean puedeRegistrarNotificacion();
    Estado cambiarA(String nuevoEstado) throws PeajeException;
}