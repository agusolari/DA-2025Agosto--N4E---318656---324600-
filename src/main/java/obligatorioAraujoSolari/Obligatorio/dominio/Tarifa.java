package obligatorioAraujoSolari.Obligatorio.dominio;

import java.util.List;

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
    private List<CategoriaVehiculo> categoriaVehiculo;

    public Tarifa(double monto, Transito transito, PuestoPeaje puestoPeaje, List<CategoriaVehiculo> categoriaVehiculo) {
        this.monto = monto;
        this.transito = transito;
        this.puestoPeaje = puestoPeaje;
        this.categoriaVehiculo = categoriaVehiculo;
    }

    public Tarifa() {}
}
