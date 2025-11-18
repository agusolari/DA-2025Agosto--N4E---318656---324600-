package obligatorioAraujoSolari.observer;

public interface Observador {

    public enum Evento {    
        TRANSITO_ACTUALIZADO,
        BONIFICACION_ACTUALIZADA,
        SALDO_ACTUALIZADO,
        ESTADO_ACTUALIZADO,
        NOTIFICACION_AGREGADA
    }

    void actualizar(Observable origen, Object evento);
}
