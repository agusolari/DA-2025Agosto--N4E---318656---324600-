package obligatorioAraujoSolari.Obligatorio.dominio;

public class EstadoPenalizado implements Estado {

    @Override
    public String getNombreEstado() {
        return "Penalizado";
    }

    @Override
    public String getDescripcion() {
        return "El usuario puede ingresar al sistema, pero no se le registran notificaciones. Puede realizar tránsitos, pero no aplican las bonificaciones que tenga asignadas. ";
    }

    @Override
    public boolean puedeLoguear() {
        return true;
    }

    @Override
    public boolean puedeRegistrarTransito() {
        return true;
    }

    @Override
    public boolean aplicaBonificacion() {
        return false;
    }

    @Override
    public boolean puedeRegistrarNotificacion() {
        return false;
    }
    
}
