package obligatorioAraujoSolari.Obligatorio.dominio;

public class EstadoSuspendido implements Estado {

    @Override
    public String getNombreEstado() {
        return "Suspendido";
    }

    @Override
    public String getDescripcion() {
        return "El usuario puede ingresar al sistema, pero no puede realizar tránsitos.";
    }

    @Override
    public boolean puedeLoguear() {
        return true;
    }

    @Override
    public boolean puedeRegistrarTransito() {
        return false;
    }

    @Override
    public boolean aplicaBonificacion() {
        return true;
    }

    @Override
    public boolean puedeRegistrarNotificacion() {
        return true;
    }
    
}
