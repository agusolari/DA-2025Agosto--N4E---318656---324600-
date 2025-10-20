package obligatorioAraujoSolari.Obligatorio.dominio;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class Propietario {

    @Getter 
    @Setter
    private String nombreCompleto;
    @Getter 
    private String cedula;  
    @Getter 
    @Setter
    private String contrasena;
    @Getter 
    @Setter
    private List<Vehiculo> vehiculos;
    @Getter
    @Setter
    private List<Notificacion> notificaciones;
    @Getter
    @Setter
    private List<Bonificacion> bonificaciones;
    @Getter
    @Setter
    private String estado;
    @Getter
    @Setter
    private double saldo;

    public Propietario(String nombreCompleto, String contrasena, String cedula, double saldo) {
        this.nombreCompleto = nombreCompleto;
        this.cedula = cedula;
        this.contrasena = contrasena;
        this.vehiculos = new ArrayList<Vehiculo>();
        this.notificaciones = new ArrayList<Notificacion>();
        this.bonificaciones = new ArrayList<Bonificacion>();
        this.estado = "Habilitado";
        this.saldo = saldo;
    }

    public Propietario() {}

    public boolean esContraseñaValida(String contrasenaIngresada) {
        return this.contrasena.equals(contrasenaIngresada);
    }

}
