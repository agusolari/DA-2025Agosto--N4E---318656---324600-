package obligatorioAraujoSolari.Obligatorio.dominio;

public class BonificacionTrabajadores extends Bonificacion {

    public BonificacionTrabajadores(Propietario propietario, PuestoPeaje puestoPeaje) {
        super("Trabajadores", "Tienen un 80% de descuento si el tránsito por el puesto asignado se realiza en un día de semana.", propietario, puestoPeaje);
    }

    @Override
    protected double calcularDescuento() {
        // Verificar si el puesto de peaje del tránsito coincide con el puesto asignado al propietario
        boolean esPuestoAsignado = getTransito().getPuestoPeaje().equals(getPuestoPeaje());
        // Verificar si el día del tránsito es un día de semana (lunes a viernes)
        boolean esDiaSemana = getTransito().esDiaSemana();

        if(esPuestoAsignado && esDiaSemana) {
            return 0.8;
        }
        return 0;
    }

}
