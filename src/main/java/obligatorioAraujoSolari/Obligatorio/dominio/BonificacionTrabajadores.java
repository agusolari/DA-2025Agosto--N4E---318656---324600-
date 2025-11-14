package obligatorioAraujoSolari.Obligatorio.dominio;

import java.time.LocalDate;

public class BonificacionTrabajadores extends Bonificacion {

    public BonificacionTrabajadores(Propietario propietario, PuestoPeaje puestoPeaje, LocalDate fechaAsignacion) {
        super("Trabajadores", "Tienen un 80% de descuento si el tránsito por el puesto asignado se realiza en un día de semana.", propietario, puestoPeaje, fechaAsignacion);
    }

    public BonificacionTrabajadores(Propietario propietario, PuestoPeaje puestoPeaje) {
        super("Trabajadores", "Tienen un 80% de descuento si el tránsito por el puesto asignado se realiza en un día de semana.", propietario, puestoPeaje, LocalDate.now());
    }

    @Override
    protected double calcularDescuento(Transito transito) {
        boolean esPuestoAsignado = transito.getPuestoPeaje().equals(getPuestoPeaje());
        boolean esDiaSemana = transito.esDiaSemana();

        if(esPuestoAsignado && esDiaSemana) {
            return 0.8;
        }
        return 0;
    }

}
