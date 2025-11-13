package obligatorioAraujoSolari.Obligatorio.dominio;

public interface Estado {
    String getNombreEstado();
    String getDescripcion();
    boolean puedeLoguear();
    boolean puedeRegistrarTransito();
    boolean aplicaBonificacion();
    boolean puedeRegistrarNotificacion();
}