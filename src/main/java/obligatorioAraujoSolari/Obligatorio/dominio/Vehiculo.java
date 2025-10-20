package obligatorioAraujoSolari.Obligatorio.dominio;

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
    private Transito transito;

    //Revisar que pedir en constructor para creacion de vehiculo.

    public Vehiculo(String matricula, String color, String modelo, Propietario propietario,
            CategoriaVehiculo categoria, Transito transito) {
        this.matricula = matricula;
        this.color = color;
        this.modelo = modelo;
        this.propietario = propietario;
        this.categoria = categoria;
        this.transito = transito;
        }
    
    public Vehiculo() {}

}
