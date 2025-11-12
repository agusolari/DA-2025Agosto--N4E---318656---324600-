package obligatorioAraujoSolari.Obligatorio.dominio;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class PuestoPeaje {

    @Getter 
    @Setter
    private String nombre;
    @Getter
    @Setter
    private List<Transito> transitos;
    @Getter
    @Setter
    private String ubicacion;
    @Getter
    @Setter
    private List<Tarifa> tarifas;

    public PuestoPeaje(String nombre, String ubicacion) {
        this.nombre = nombre;
        this.transitos = new ArrayList<Transito>();
        this.ubicacion = ubicacion;
        this.tarifas = new ArrayList<Tarifa>();
    }

    public PuestoPeaje() {}

    public void agregarTarifas(Tarifa tarifa) {
        this.tarifas.add(tarifa);
    }
}
