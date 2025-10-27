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

    public Bonificacion(String nombre, String descripcion, Propietario propietario, PuestoPeaje puestoPeaje, Transito transito) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.propietario = propietario;
        this.puestoPeaje = puestoPeaje;
        this.transito = transito;
    }
    public Bonificacion() {}

    protected abstract double calcularDescuento();

}
