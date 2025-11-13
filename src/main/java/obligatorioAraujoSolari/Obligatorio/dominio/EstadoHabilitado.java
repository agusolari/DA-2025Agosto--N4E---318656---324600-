package obligatorioAraujoSolari.Obligatorio.dominio;

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
    
}
