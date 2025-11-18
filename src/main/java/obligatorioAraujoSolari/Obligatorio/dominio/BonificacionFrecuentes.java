package obligatorioAraujoSolari.Obligatorio.dominio;

import java.time.LocalDate;

public class BonificacionFrecuentes extends Bonificacion {
    public BonificacionFrecuentes(Propietario propietario, PuestoPeaje puestoPeaje, LocalDate fechaCreacion) {
        super("Frecuentes", "Tienen un 50% de descuento a partir del segundo transito realizado en el día por un puesto determinado con el mismo vehículo. En el primer tránsito del día (con cada vehículo) no tienen descuento.", propietario, puestoPeaje, fechaCreacion);
    }

    public BonificacionFrecuentes(Propietario propietario, PuestoPeaje puestoPeaje) {
        super("Frecuentes", "Tienen un 50% de descuento a partir del segundo transito realizado en el día por un puesto determinado con el mismo vehículo. En el primer tránsito del día (con cada vehículo) no tienen descuento.", propietario, puestoPeaje, LocalDate.now());
    }

    @Override
    protected double calcularDescuento(Transito transito) {
        if(transito.getVehiculo().obtenerCantidadTransitosEnPuestoHoy(getPuestoPeaje()) > 0) {
            return 0.5;
        }
        return 0;
    }
}
