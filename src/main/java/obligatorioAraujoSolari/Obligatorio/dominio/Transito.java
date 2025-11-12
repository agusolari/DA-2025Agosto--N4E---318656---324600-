package obligatorioAraujoSolari.Obligatorio.dominio;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

public class Transito {
    @Getter 
    @Setter
    private Vehiculo vehiculo;
    @Getter
    @Setter
    private PuestoPeaje puestoPeaje;
    @Getter
    @Setter
    private Tarifa tarifa;
    @Getter
    @Setter
    private Notificacion notificacion;
    @Getter
    @Setter
    private Bonificacion bonificacion;
    @Getter
    @Setter
    private LocalDateTime fechaHora;
    
    public Transito(Vehiculo vehiculo, PuestoPeaje puestoPeaje, Tarifa tarifa, Notificacion notificacion,
            Bonificacion bonificacion) {
        this.vehiculo = vehiculo;
        this.puestoPeaje = puestoPeaje;
        this.tarifa = tarifa;
        this.notificacion = notificacion;
        this.bonificacion = bonificacion;
        this.fechaHora = LocalDateTime.now();
    }

    public Transito() {}

    // Creamos este método acá, porque desde el patrón experto, es la clase que tiene la información necesaria para responder si el tránsito es hoy.
    public boolean esHoy() {
        LocalDateTime ahora = LocalDateTime.now();
        return (this.fechaHora.getDayOfYear() == ahora.getDayOfYear() && this.fechaHora.getYear() == ahora.getYear());
    }

    public boolean esDiaSemana() {
        switch (this.fechaHora.getDayOfWeek()) {
            case MONDAY:
            case TUESDAY:
            case WEDNESDAY:
            case THURSDAY:
            case FRIDAY:
                return true;
            default:
                return false;
        }
    }

    public double calcularTarifaFinal() {
        double costoBase = this.tarifa.getMonto();
        double descuento = 0.0;
        if (this.bonificacion != null) {
            descuento = this.bonificacion.calcularDescuento();
        }
        return costoBase - (costoBase * descuento);
    }
    
    //tenemos que crear el metodo para crear un transito desde esta clase 
}
