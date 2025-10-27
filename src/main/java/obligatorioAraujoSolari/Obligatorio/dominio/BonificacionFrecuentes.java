package obligatorioAraujoSolari.Obligatorio.dominio;

public class BonificacionFrecuentes extends Bonificacion {
    public BonificacionFrecuentes(Propietario propietario, PuestoPeaje puestoPeaje, Transito transito) {
        super("Frecuentes", "Tienen un 50% de descuento a partir del segundo transito realizado en el día por un puesto determinado con el mismo vehículo. En el primer tránsito del día (con cada vehículo) no tienen descuento.", propietario, puestoPeaje, transito);
    }

    @Override
    protected double calcularDescuento() {
        //TODO: Preguntar si deberíamos crear un método en Transito que llame al 
        //obtenerCantidadTransitosEnPuestoHoy y desde acá solo hacer getTransito().nombreDelMetodo()
        if(getTransito().getVehiculo().obtenerCantidadTransitosEnPuestoHoy(getPuestoPeaje()) > 1) {
            return 0.5;
        }
        return 0;
    }
}
