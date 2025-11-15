package obligatorioAraujoSolari.Obligatorio.dominio;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import obligatorioAraujoSolari.Obligatorio.excepciones.PeajeException;

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
        this.estado = new EstadoHabilitado();
        this.saldo = saldo;
        this.saldoMinimoAlerta = 500;
    }

    public void agregarVehiculo(Vehiculo vehiculo) {
        this.vehiculos.add(vehiculo);
    }

    public void agregarNotificacion(Notificacion notificacion) {
        if (estado.puedeRegistrarNotificacion()) {
            if (saldo > saldoMinimoAlerta) {
                notificacion.setMensaje(notificacion.getFechaHora().toString() + " Pasaste por el puesto " + notificacion.getTransito().getPuestoPeaje().getNombre() + " con el vehículo " + notificacion.getTransito().getVehiculo().getMatricula() + ".");
                this.notificaciones.add(notificacion);
            } else {
                notificacion.setMensaje(notificacion.getFechaHora().toString() + " Tu saldo actual es de $ " + this.saldo + " Te recomendamos hacer una recarga.");
                this.notificaciones.add(notificacion);
            }
        }
    }

    public void agregarBonificacion(Bonificacion bonificacion) {
        this.bonificaciones.add(bonificacion);
    }

    public void actualizarSaldo(double monto) {
        this.saldo += monto;
    }

    public void cambiarEstado(String nuevoEstado) throws PeajeException {
        this.estado = this.estado.cambiarA(nuevoEstado);
    }
}
