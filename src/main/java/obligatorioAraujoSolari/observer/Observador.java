package obligatorioAraujoSolari.observer;

public interface Observador {

    public enum Evento {    
        TRANSITO_ACTUALIZADO,
        SESION_ACTUALIZADA 
    }

    void actualizar(Observable origen, Object evento);
}
