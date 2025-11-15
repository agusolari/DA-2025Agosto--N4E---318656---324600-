package obligatorioAraujoSolari.Obligatorio.dominio;

import obligatorioAraujoSolari.Obligatorio.excepciones.PeajeException;

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

    @Override
    public Estado cambiarA(String nuevoEstado) throws PeajeException {
        switch (nuevoEstado) {
            case "Habilitado":
                return new EstadoHabilitado();
            case "Suspendido":
                return new EstadoSuspendido();
            case "Penalizado":
                throw new PeajeException("El propietario ya está en estado Penalizado.");
            case "Deshabilitado":
                return new EstadoDeshabilitado();
            default:
                throw new PeajeException("Estado inválido: " + nuevoEstado);
        }
    }
}