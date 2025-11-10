package obligatorioAraujoSolari.Obligatorio.servicios;

import java.util.ArrayList;
import java.util.List;

import obligatorioAraujoSolari.Obligatorio.dominio.Bonificacion;
import obligatorioAraujoSolari.Obligatorio.dominio.Transito;
import obligatorioAraujoSolari.Obligatorio.dominio.Vehiculo;
import obligatorioAraujoSolari.Obligatorio.excepciones.PeajeException;

public class ServicioTransito {

    private List<Bonificacion> bonificaciones;
    private List<Transito> transitos;

    public ServicioTransito() {
        this.bonificaciones =  new ArrayList<>();
        this.transitos = new ArrayList<>();
    }

    public void agregarBonificacion(Bonificacion bonificacion) {
        bonificaciones.add(bonificacion);
    }

    public List<Bonificacion> getBonificaciones() {
        return bonificaciones;
    }

    public List<Transito> obtenerTransitosPorVehiculo(Vehiculo vehiculo) throws PeajeException {
        List<Transito> transitosDelVehiculo = new ArrayList<>();
        for (Transito transito : transitos) {
            if (transito.getVehiculo().equals(vehiculo)) {
                transitosDelVehiculo.add(transito);
            }
        }
        if (transitosDelVehiculo.isEmpty()) {
            throw new PeajeException("El vehículo no tiene tránsitos registrados.");
        }
        return transitosDelVehiculo;
    }
    
}
