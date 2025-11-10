package obligatorioAraujoSolari.Obligatorio.dominio;

import java.time.LocalDate;

public class BonificacionExonerados extends Bonificacion {


    public BonificacionExonerados(Propietario propietario, PuestoPeaje puestoPeaje, LocalDate fechaAsignacion) {
        super("Exonerado", "No pagan el tránsito en un determinado puesto.", propietario, puestoPeaje, fechaAsignacion);
    }

    public BonificacionExonerados(Propietario propietario, PuestoPeaje puestoPeaje) {
        super("Exonerado", "No pagan el tránsito en un determinado puesto.", propietario, puestoPeaje, LocalDate.now());
    }

    @Override
    protected double calcularDescuento() {
        return 1;
    }

}
