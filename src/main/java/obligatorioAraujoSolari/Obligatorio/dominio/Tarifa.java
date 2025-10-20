package obligatorioAraujoSolari.Obligatorio.dominio;

import lombok.Getter;
import lombok.Setter;

public class Tarifa {
    @Getter
    @Setter
    private double monto;
    @Getter
    @Setter
    private Transito transito;
    @Getter
    @Setter
    private PuestoPeaje puestoPeaje;
    @Getter
    @Setter
    private CategoriaVehiculo categoriaVehiculo;
    //Arreglar al UML porque no hay relacion entre categoria y tarifa y en la  letra lo pide. 

    public Tarifa(double monto, Transito transito, PuestoPeaje puestoPeaje, CategoriaVehiculo categoriaVehiculo) {
        this.monto = monto;
        this.transito = transito;
        this.puestoPeaje = puestoPeaje;
        this.categoriaVehiculo = categoriaVehiculo;
    }

    public Tarifa() {}
}
