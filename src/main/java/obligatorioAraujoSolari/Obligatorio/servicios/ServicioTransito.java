package obligatorioAraujoSolari.Obligatorio.servicios;

import java.util.ArrayList;
import java.util.List;

import obligatorioAraujoSolari.Obligatorio.dominio.Bonificacion;
import obligatorioAraujoSolari.Obligatorio.dominio.Propietario;
import obligatorioAraujoSolari.Obligatorio.dominio.PuestoPeaje;
import obligatorioAraujoSolari.Obligatorio.dominio.Tarifa;
import obligatorioAraujoSolari.Obligatorio.dominio.Transito;
import obligatorioAraujoSolari.Obligatorio.dominio.Vehiculo;
import obligatorioAraujoSolari.Obligatorio.excepciones.PeajeException;
import obligatorioAraujoSolari.Obligatorio.servicios.fachada.FachadaServicio;

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
        return transitosDelVehiculo;
    }

    public void registrarTransito(Transito transito) throws PeajeException {
        if (transito == null) {
            throw new PeajeException("No se puede registrar un tránsito nulo.");
        }
        transito.getVehiculo().agregarTransito(transito);
        transitos.add(transito);
    }

    public Bonificacion obtenerBonificacionAplicable(Vehiculo vehiculo, PuestoPeaje puestoPeaje) {
        for (Bonificacion bonificacion : bonificaciones) {
            if (bonificacion.getPropietario().equals(vehiculo.getPropietario()) &&
                bonificacion.getPuestoPeaje().equals(puestoPeaje)) {
                return bonificacion;
            }
        }
        return null;
    }

    public void asignarBonificacionAPropietario (Propietario propietario, Bonificacion bonificacion, PuestoPeaje puestoPeaje) throws PeajeException {
        bonificaciones.add(bonificacion);
        propietario.agregarBonificacion(bonificacion);
    }

    public Bonificacion obtenerBonificacionPorNombre(String nombre) throws PeajeException {
        for (Bonificacion bonificacion : bonificaciones) {
            if (bonificacion.getNombre().equalsIgnoreCase(nombre)) {
                return bonificacion;
            }
        }
        throw new PeajeException("Bonificación no encontrada");
    }

}
