package obligatorioAraujoSolari.Obligatorio.dominio;

import obligatorioAraujoSolari.Obligatorio.excepciones.PeajeException;

public class EstadoHabilitado implements Estado {

    @Override
    public String getNombreEstado() {
        return "Habilitado";
    }

    @Override
    public String getDescripcion() {
        return "Es el estado por defecto de los propietarios cuando se dan de alta en el sistema. El propietario tiene todas las funcionalidades habilitadas.";
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
                throw new PeajeException("El propietario ya está en estado Habilitado.");
            case "Suspendido":
                return new EstadoSuspendido();
            case "Penalizado":
                return new EstadoPenalizado();
            case "Deshabilitado":
                return new EstadoDeshabilitado();
            default:
                throw new PeajeException("Estado inválido: " + nuevoEstado);
        }
    }
}