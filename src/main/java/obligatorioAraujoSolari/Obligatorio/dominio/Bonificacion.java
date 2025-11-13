package obligatorioAraujoSolari.Obligatorio.dominio;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

public abstract class Bonificacion {

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
    private String descripcion;
    @Getter
    @Setter
    private LocalDate fechaAsignacion;

    public Bonificacion(String nombre, String descripcion, Propietario propietario, PuestoPeaje puestoPeaje) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.propietario = propietario;
        this.puestoPeaje = puestoPeaje;
        this.fechaAsignacion = LocalDate.now();
    }

    public Bonificacion(String nombre, String descripcion, Propietario propietario, PuestoPeaje puestoPeaje, LocalDate fechaAsignacion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.propietario = propietario;
        this.puestoPeaje = puestoPeaje;
        this.fechaAsignacion = fechaAsignacion;
    }
    protected abstract double calcularDescuento(Transito transito);
}
