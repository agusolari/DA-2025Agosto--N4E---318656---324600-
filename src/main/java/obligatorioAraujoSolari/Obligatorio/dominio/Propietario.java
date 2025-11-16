package obligatorioAraujoSolari.Obligatorio.dominio;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import obligatorioAraujoSolari.Obligatorio.excepciones.PeajeException;
import obligatorioAraujoSolari.observer.Observable;
import obligatorioAraujoSolari.observer.Observador;

public class Propietario extends Usuario {

    // Chequear con Agustín y/o profesor si está bien aplicarlo como composición, para no perder la herencia de Usuario
    private Observable observable = new Observable() {};

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

    // Métodos Observable
    public void subscribir(Observador observador) {
        observable.subscribir(observador);
    }

    public void desubscribir(Observador observador) {
        observable.desubscribir(observador);
    }

    private void notificar(Object evento) {
        observable.notificar(evento);
    }
    ///
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
            notificar("NOTIFICACION_AGREGADA");
        }
    }

    public void agregarBonificacion(Bonificacion bonificacion) {
        this.bonificaciones.add(bonificacion);
        notificar("BONIFICACION_AGREGADA");
    }

    public void actualizarSaldo(double monto) {
        this.saldo += monto;
        notificar("SALDO_ACTUALIZADO");
    }

    public void cambiarEstado(String nuevoEstado) throws PeajeException {
        this.estado = this.estado.cambiarA(nuevoEstado);
        notificar("ESTADO_CAMBIADO");
    }
}
