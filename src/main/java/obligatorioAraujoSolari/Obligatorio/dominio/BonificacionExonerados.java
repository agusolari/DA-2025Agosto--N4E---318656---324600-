package obligatorioAraujoSolari.Obligatorio.dominio;

public class BonificacionExonerados extends Bonificacion {

    public BonificacionExonerados(Propietario propietario, PuestoPeaje puestoPeaje, Transito transito) {
        super("Exonerados", "No pagan el tránsito en un determinado puesto.", propietario, puestoPeaje, transito);
    }

    @Override
    protected double calcularDescuento() {
        return 1;
    }

}
