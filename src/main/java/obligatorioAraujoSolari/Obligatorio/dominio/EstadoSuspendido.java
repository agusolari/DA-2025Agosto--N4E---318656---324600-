package obligatorioAraujoSolari.Obligatorio.dominio;

import obligatorioAraujoSolari.Obligatorio.excepciones.PeajeException;

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

    @Override
    public Estado cambiarA(String nuevoEstado) throws PeajeException {
        switch (nuevoEstado) {
            case "Habilitado":
                return new EstadoHabilitado();
            case "Suspendido":
                throw new PeajeException("El propietario ya está en estado Suspendido.");
            case "Penalizado":
                return new EstadoPenalizado();
            case "Deshabilitado":
                return new EstadoDeshabilitado();
            default:
                throw new PeajeException("Estado inválido: " + nuevoEstado);
        }
    }
}