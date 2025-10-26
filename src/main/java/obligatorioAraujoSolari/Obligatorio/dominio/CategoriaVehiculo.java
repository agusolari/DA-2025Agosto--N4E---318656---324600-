package obligatorioAraujoSolari.Obligatorio.dominio;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class CategoriaVehiculo {
    @Getter
    @Setter
    private String nombre;

    @Getter
    @Setter
    private List<Tarifa> tarifas;

    public CategoriaVehiculo(String nombre) {
        this.nombre = nombre;
        this.tarifas = new ArrayList<>();
    }

    public CategoriaVehiculo(String nombre, List<Tarifa> tarifas) {
        this.nombre = nombre;
        this.tarifas = tarifas;
    }
    public CategoriaVehiculo() {
        this.tarifas = new ArrayList<>();
    }
}
