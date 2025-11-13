package obligatorioAraujoSolari.Obligatorio.dominio;

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
}
