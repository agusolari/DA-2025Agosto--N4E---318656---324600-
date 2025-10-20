package obligatorioAraujoSolari.Obligatorio.dominio;

import lombok.Getter;
import lombok.Setter;

public class CategoriaVehiculo {
    @Getter
    @Setter
    private String nombre;
    @Getter
    @Setter
    private double monto;
    //Ver si no hay una lista de vehiculos por categoria.

    public CategoriaVehiculo(String nombre, double monto) {
        this.nombre = nombre;
        this.monto = monto;
    }
    public CategoriaVehiculo() {}
}
