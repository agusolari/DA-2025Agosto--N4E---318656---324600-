package obligatorioAraujoSolari.Obligatorio.dominio;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class Vehiculo {

    //REVISAR SETTERS DE ATRIBUTOS INMUTABLES. 
    @Getter
    @Setter 
    private String matricula;
    @Getter
    @Setter
    private String color;
    @Getter
    @Setter
    private String modelo;
    @Getter
    @Setter
    private Propietario propietario;
    @Getter
    @Setter
    private CategoriaVehiculo categoria;
    @Getter 
    @Setter
    private List<Transito> transitos;

        //Revisar que pedir en constructor para creacion de vehiculo.

        public Vehiculo(String matricula, String color, String modelo, Propietario propietario,
                CategoriaVehiculo categoria) {
        this.matricula = matricula;
        this.color = color;
        this.modelo = modelo;
        this.propietario = propietario;
        this.categoria = categoria;
        this.transitos = new ArrayList<>();
        }
    
    public Vehiculo() {}

    public int obtenerCantidadTransitosEnPuestoHoy(PuestoPeaje puestoPeaje) {
        int cantidad = 0;
        for (Transito transito : transitos) {
            if (transito.getPuestoPeaje().equals(puestoPeaje) && transito.esHoy()) {
                cantidad++;
            }
        }
        return cantidad;
    }

}
