package obligatorioAraujoSolari.Obligatorio.dominio;

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
    private Transito transito;
    @Getter
    @Setter
    private String descripcion;

    public Bonificacion(String nombre, String descripcion, Propietario propietario, PuestoPeaje puestoPeaje) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.propietario = propietario;
        this.puestoPeaje = puestoPeaje;
    }
    protected abstract double calcularDescuento();
}
