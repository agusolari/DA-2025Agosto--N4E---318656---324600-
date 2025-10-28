package obligatorioAraujoSolari.Obligatorio.dominio;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class Propietario extends Usuario {

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
    private Estado estado;
    @Getter
    @Setter
    private double saldo;
    @Getter
    @Setter
    private double saldoMinimoAlerta;

    public Propietario(String cedula, String contrasenia, String nombreCompleto) {
        super(cedula, contrasenia, nombreCompleto);
    }

    public Propietario(String nombreCompleto, String contrasenia, String cedula, double saldo) {
        super(cedula, contrasenia, nombreCompleto);
        this.vehiculos = new ArrayList<Vehiculo>();
        this.notificaciones = new ArrayList<Notificacion>();
        this.bonificaciones = new ArrayList<Bonificacion>();
        this.estado = new Estado("HABILITADO", "Es el estado por defecto de los propietarios cuando se dan de alta en el\n" + //
                        "sistema. El propietario tiene todas las funcionalidades habilitadas.");
        this.saldo = saldo;
        this.saldoMinimoAlerta = 500;
    }

}
