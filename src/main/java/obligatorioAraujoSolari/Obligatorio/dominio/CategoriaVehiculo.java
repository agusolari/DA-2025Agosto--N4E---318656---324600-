package obligatorioAraujoSolari.Obligatorio.dominio;

import lombok.Getter;
import lombok.Setter;

public class CategoriaVehiculo {
    @Getter
    @Setter
    private String nombre;

    public CategoriaVehiculo(String nombre) {
        this.nombre = nombre;
    }

    public CategoriaVehiculo() {}
}
