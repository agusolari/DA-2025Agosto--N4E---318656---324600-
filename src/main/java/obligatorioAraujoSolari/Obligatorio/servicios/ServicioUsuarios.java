package obligatorioAraujoSolari.Obligatorio.servicios;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import obligatorioAraujoSolari.Obligatorio.dominio.Administrador;
import obligatorioAraujoSolari.Obligatorio.dominio.Propietario;
import obligatorioAraujoSolari.Obligatorio.dominio.Sesion;
import obligatorioAraujoSolari.Obligatorio.dominio.Usuario;
import obligatorioAraujoSolari.Obligatorio.excepciones.PeajeException;
import obligatorioAraujoSolari.Obligatorio.servicios.fachada.FachadaServicio;

public class ServicioUsuarios {
    private List<Propietario> propietarios;
    private List<Administrador> administradores;
    private List<Sesion> sesionesActivas = new ArrayList<>();

    public ServicioUsuarios() {
        //TODO: Preguntar si es mejor que haya 2 listas o una única de usuarios.
        this.propietarios = new ArrayList<>();
        this.administradores = new ArrayList<>();
    }

    public Propietario loginUsuarioPropietario(String cedula, String contrasenia) throws PeajeException {
        Propietario usuario = (Propietario) login(cedula, contrasenia, propietarios, "Usuario de propietario y/o contraseña incorrectos");
        Sesion sesion = new Sesion(usuario);
        sesion.setFechaInicio(new Date());
        sesionesActivas.add(sesion);
        return usuario;
    }

    public Administrador loginUsuarioAdministrador(String cedula, String contrasenia) throws PeajeException {
        return (Administrador) login(cedula, contrasenia, administradores, "Usuario administrador y/o contraseña incorrectos");
    }

    private Usuario login(String cedula, String contrasenia, List<? extends Usuario> usuarios, String mensajeLoginIncorrecto) throws PeajeException {
        for(Usuario usuario : usuarios) {
            if(usuario.getCedula().equals(cedula) && usuario.esContraseniaValida(contrasenia)) {
                return usuario;
            }
        }
        throw new PeajeException(mensajeLoginIncorrecto);
    }

    public void registrarPropietario(Propietario propietario) throws PeajeException {
        if(FachadaServicio.getInstancia().existeUsuario(propietario.getCedula())) {
            throw new PeajeException("Ya existe un usuario con la cédula indicada.");
        }
        propietarios.add(propietario);
    }
    //TODO: Preguntar si está bien que los dos métodos sean parecidos y no incumplen el DRY. (código repetido)
    public void registrarAdministrador(Administrador administrador) throws PeajeException {
        if(FachadaServicio.getInstancia().existeUsuario(administrador.getCedula())) {
            throw new PeajeException("Ya existe un usuario con la cédula indicada.");
        }
        administradores.add(administrador);
    }

    public boolean existePropietario(String cedula) {
        for(Propietario propietario : propietarios) {
            if(propietario.getCedula().equals(cedula)) {
                return true;
            }
        }
        return false;
    }

    public boolean existeAdministrador(String cedula) {
        for(Administrador administrador : administradores) {
            if(administrador.getCedula().equals(cedula)) {
                return true;
            }
        }
        return false;
    }

    public Propietario obtenerPropietarioPorCedula(String cedula) throws PeajeException {
        for(Propietario propietario : propietarios) {
            if(propietario.getCedula().equals(cedula)) {
                return propietario;
            }
        }
        throw new PeajeException("No se encontró un propietario con la cédula indicada.");
    }

}
