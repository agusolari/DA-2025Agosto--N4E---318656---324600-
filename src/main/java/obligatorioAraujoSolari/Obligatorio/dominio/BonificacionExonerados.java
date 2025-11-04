package obligatorioAraujoSolari.Obligatorio.dominio;

public class BonificacionExonerados extends Bonificacion {


    public BonificacionExonerados(Propietario propietario, PuestoPeaje puestoPeaje) {
        super("Exonerado", "No pagan el tránsito en un determinado puesto.", propietario, puestoPeaje);
    }

    @Override
    protected double calcularDescuento() {
        return 1;
    }

}
