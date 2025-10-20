package obligatorioAraujoSolari.Obligatorio.dominio;

import lombok.Getter;
import lombok.Setter;

public class Bonificacion {

    @Getter
    @Setter
    private String nombre;
    @Getter
    @Setter
    private Propietario propietario;
    @Setter
    @Getter
    private PuestoPeaje puestoPeaje;
    @Getter
    @Setter
    private Transito transito;

    public Bonificacion(String nombre, Propietario propietario, PuestoPeaje puestoPeaje, Transito transito) {
        this.nombre = nombre;
        this.propietario = propietario;
        this.puestoPeaje = puestoPeaje;
        this.transito = transito;
    }
    public Bonificacion() {}

    // Hasta el momento hay 3 bonificaciones definidas en el sistema, aunque en el futuro
    // podrían definirse más. Las bonificaciones actualmente definidas son:
    // Exonerados: No pagan el tránsito en un determinado puesto.
    // Frecuentes: Tienen un 50% de descuento a partir del segundo transito realizado en el día
    // por un puesto determinado con el mismo vehículo. En el primer tránsito del día (con
    // cada vehículo) no tienen descuento.
    // Trabajadores: Tienen un 80% de descuento si el tránsito por el puesto asignado se realiza
    // en un día de semana.

}
