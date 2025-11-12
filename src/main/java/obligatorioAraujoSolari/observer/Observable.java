package obligatorioAraujoSolari.observer;
import java.util.ArrayList;
import java.util.List;

public abstract class Observable {
    private List<Observador> subscriptos;

    public Observable() {
        subscriptos = new ArrayList<Observador>();
    }

    public void subscribir(Observador observador) {
        if (!subscriptos.contains(observador))
            subscriptos.add(observador);
    }

    public void desubscribir(Observador observador) {
        subscriptos.remove(observador);
    }

    public void notificar(Object evento) {
        for (Observador observador : subscriptos) {
            observador.actualizar(this, evento);
        }
    }
}
