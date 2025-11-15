package obligatorioAraujoSolari.Obligatorio.dominio;

import obligatorioAraujoSolari.Obligatorio.excepciones.PeajeException;

public class EstadoDeshabilitado implements Estado {

    @Override
    public String getNombreEstado() {
        return "Deshabilitado";
    }

    @Override
    public String getDescripcion() {
        return "El usuario no puede ingresar al sistema ni puede realizar tránsitos. Tampoco se le pueden asignar bonificaciones.";
    }

    @Override
    public boolean puedeLoguear() {
        return false;
    }

    @Override
    public boolean puedeRegistrarTransito() {
        return false;
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
                return new EstadoPenalizado();
            case "Deshabilitado":
                throw new PeajeException("El propietario ya está en estado Deshabilitado.");
            default:
                throw new PeajeException("Estado inválido: " + nuevoEstado);
        }
    }
}
