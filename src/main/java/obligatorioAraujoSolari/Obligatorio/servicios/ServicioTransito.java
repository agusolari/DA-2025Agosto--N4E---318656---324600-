package obligatorioAraujoSolari.Obligatorio.servicios;

import java.util.ArrayList;
import java.util.List;

import obligatorioAraujoSolari.Obligatorio.dominio.Bonificacion;

public class ServicioTransito {

    private List<Bonificacion> bonificaciones;

    public void ServicioTransito() {
        bonificaciones =  new ArrayList<>();
    }

    public void agregarBonificacion(Bonificacion bonificacion) {
        bonificaciones.add(bonificacion);
    }

    public List<Bonificacion> getBonificaciones() {
        return bonificaciones;
    }
    
}
