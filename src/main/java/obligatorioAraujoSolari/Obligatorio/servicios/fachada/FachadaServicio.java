package obligatorioAraujoSolari.Obligatorio.servicios.fachada;

import java.util.List;

import obligatorioAraujoSolari.Obligatorio.dominio.Administrador;
import obligatorioAraujoSolari.Obligatorio.dominio.Bonificacion;
import obligatorioAraujoSolari.Obligatorio.dominio.CategoriaVehiculo;
import obligatorioAraujoSolari.Obligatorio.dominio.Estado;
import obligatorioAraujoSolari.Obligatorio.dominio.Propietario;
import obligatorioAraujoSolari.Obligatorio.dominio.PuestoPeaje;
import obligatorioAraujoSolari.Obligatorio.dominio.Sesion;
import obligatorioAraujoSolari.Obligatorio.dominio.Tarifa;
import obligatorioAraujoSolari.Obligatorio.dominio.Transito;
import obligatorioAraujoSolari.Obligatorio.dominio.Vehiculo;
import obligatorioAraujoSolari.Obligatorio.excepciones.PeajeException;
import obligatorioAraujoSolari.Obligatorio.servicios.ServicioPeajes;
import obligatorioAraujoSolari.Obligatorio.servicios.ServicioTransito;
import obligatorioAraujoSolari.Obligatorio.servicios.ServicioUsuarios;
import obligatorioAraujoSolari.Obligatorio.servicios.ServicioVehiculos;

public class FachadaServicio {

    private static FachadaServicio instancia;
    private ServicioUsuarios servicioUsuarios;
    private ServicioPeajes servicioPeajes;
    private ServicioVehiculos servicioVehiculos;
    private ServicioTransito servicioTransito;

    private FachadaServicio() {
        servicioUsuarios = new ServicioUsuarios();
        servicioPeajes = new ServicioPeajes();
        servicioVehiculos = new ServicioVehiculos();
        servicioTransito = new ServicioTransito();
    }

    public static FachadaServicio getInstancia() {
        if (instancia == null) {
            instancia = new FachadaServicio();
        }
        return instancia;
    }

    /* <--------- Servicios de Usuarios ---------> */
    public Sesion loginUsuarioPropietario(String cedula, String contrasenia) throws PeajeException{
        return servicioUsuarios.loginUsuarioPropietario(cedula, contrasenia);
    }

    public Sesion loginUsuarioAdministrador(String cedula, String contrasenia) throws PeajeException{
        return servicioUsuarios.loginUsuarioAdministrador(cedula, contrasenia);
    }

    public void logout(Sesion sesion) {
        servicioUsuarios.logout(sesion);
    }

    public void registrarPropietario(Propietario propietario) throws PeajeException {
        servicioUsuarios.registrarPropietario(propietario);
    }

    public void registrarAdministrador(Administrador administrador) throws PeajeException {
        servicioUsuarios.registrarAdministrador(administrador);
    }

    public boolean existePropietario(String cedula) {
        return servicioUsuarios.existePropietario(cedula);
    }

    public boolean existeAdministrador(String cedula) {
        return servicioUsuarios.existeAdministrador(cedula);
    }

    public boolean existeUsuario(String cedula) {
        return existePropietario(cedula) || existeAdministrador(cedula);
    }

    public Propietario obtenerPropietarioPorCedula(String cedula) throws PeajeException {
        return servicioUsuarios.obtenerPropietarioPorCedula(cedula);
    }

    public List<Estado> getEstados() {
        return servicioUsuarios.getEstados();
    }

    public void agregarEstado(Estado estado) {
        servicioUsuarios.agregarEstado(estado);
    }

    /* <--------- Servicios de Peajes ---------> */

    public void agregarPuestoPeaje(PuestoPeaje puesto) {
        servicioPeajes.agregarPuesto(puesto);
    }

    public List<PuestoPeaje> getPuestosPeaje() {
        return servicioPeajes.getPuestos();
    }

    public PuestoPeaje getPuestoPeajePorNombre(String nombre) throws PeajeException {
        return servicioPeajes.getPuestoPeajePorNombre(nombre);
    }

    /* <--------- Servicios de Vehiculos ---------> */

    public void crearCategoriaVehiculo(CategoriaVehiculo categoria) {
        servicioVehiculos.crearCategoriaVehiculo(categoria);
    }

    public List<CategoriaVehiculo> getCategoriasVehiculos() {
        return servicioVehiculos.getCategoriasVehiculos();
    }


    /* <--------- Servicios de Tarifas ---------> */

    
    public void agregarTarifa(Tarifa tarifa) {
        servicioPeajes.agregarTarifa(tarifa);
    }
    
    public List<Tarifa> getTarifas() {
        return servicioPeajes.getTarifas();
    }
    
    /* <--------- Servicios de Vehiculos ---------> */
    
    public void crearVehiculo(Vehiculo vehiculo) {
        servicioVehiculos.crearVehiculo(vehiculo);
    }
    
    public List<Vehiculo> obtenerVehiculosPorPropietario(Propietario propietario) throws PeajeException {
        return servicioVehiculos.obtenerVehiculosPorPropietario(propietario);
    }
    
    public Vehiculo obtenerVehiculoPorMatricula(String matricula) throws PeajeException {
        return servicioVehiculos.obtenerVehiculoPorMatricula(matricula);
    }
    
    /* <--------- Servicios de transito ---------> */
    

    public void agregarBonificacion(Bonificacion bonificacion) {
        servicioTransito.agregarBonificacion(bonificacion);
    }
    
    public List<Bonificacion> getBonificaciones() {
        return servicioTransito.getBonificaciones();
    }
    
    public List<Transito> obtenerTransitosPorVehiculo(Vehiculo vehiculo) throws PeajeException {
        return servicioTransito.obtenerTransitosPorVehiculo(vehiculo);
    }
}
