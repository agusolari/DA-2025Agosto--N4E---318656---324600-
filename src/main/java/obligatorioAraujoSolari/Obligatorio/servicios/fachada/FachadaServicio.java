package obligatorioAraujoSolari.Obligatorio.servicios.fachada;

import obligatorioAraujoSolari.Obligatorio.dominio.Administrador;
import obligatorioAraujoSolari.Obligatorio.dominio.Propietario;
import obligatorioAraujoSolari.Obligatorio.excepciones.PeajeException;
import obligatorioAraujoSolari.Obligatorio.servicios.ServicioUsuarios;

public class FachadaServicio {

    private static FachadaServicio instancia;
    private ServicioUsuarios servicioUsuarios;

    private FachadaServicio() {
        servicioUsuarios = new ServicioUsuarios();
    }

    public static FachadaServicio getInstancia() {
        if (instancia == null) {
            instancia = new FachadaServicio();
        }
        return instancia;
    }

    public Propietario loginUsuarioPropietario(String cedula, String contrasenia) throws PeajeException{
        return servicioUsuarios.loginUsuarioPropietario(cedula, contrasenia);
    }

    public Administrador loginUsuarioAdministrador(String cedula, String contrasenia) throws PeajeException{
        return servicioUsuarios.loginUsuarioAdministrador(cedula, contrasenia);
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

    

}
