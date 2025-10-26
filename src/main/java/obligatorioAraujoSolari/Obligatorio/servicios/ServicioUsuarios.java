package obligatorioAraujoSolari.Obligatorio.servicios;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import obligatorioAraujoSolari.Obligatorio.dominio.Administrador;
import obligatorioAraujoSolari.Obligatorio.dominio.Propietario;
import obligatorioAraujoSolari.Obligatorio.dominio.Sesion;
import obligatorioAraujoSolari.Obligatorio.dominio.Usuario;
import obligatorioAraujoSolari.Obligatorio.excepciones.PeajeException;

public class ServicioUsuarios {
    private List<Propietario> propietarios;
    private List<Administrador> administradores;
    private List<Sesion> sesionesActivas = new ArrayList<>();

    public ServicioUsuarios() {
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



}
