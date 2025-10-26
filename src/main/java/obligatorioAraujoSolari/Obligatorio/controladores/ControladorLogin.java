package obligatorioAraujoSolari.Obligatorio.controladores;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import obligatorioAraujoSolari.Obligatorio.dominio.Administrador;
import obligatorioAraujoSolari.Obligatorio.dominio.Propietario;
import obligatorioAraujoSolari.Obligatorio.excepciones.PeajeException;
import obligatorioAraujoSolari.Obligatorio.servicios.fachada.FachadaServicio;
import obligatorioAraujoSolari.Obligatorio.utils.Respuesta;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("login")
public class ControladorLogin {

     @PostMapping("/loginPropietario")
    public List<Respuesta> login(HttpSession sesionHttp, @RequestParam String cedula, @RequestParam String contrasenia)
            throws PeajeException {
        Propietario usuarioLogueado = FachadaServicio.getInstancia().loginUsuarioPropietario(cedula, contrasenia);

        sesionHttp.setAttribute("usuarioLogueado", usuarioLogueado);
        return Respuesta.lista(new Respuesta("loginExitoso", "menuPropietario.html"));
        //FALTA CREAR LAS VISTAS DE LOS MENUS
    }

    @PostMapping("/loginAdministrador")
    public List<Respuesta> loginAdministrador(HttpSession sesionHttp, @RequestParam String cedula,
            @RequestParam String contrasenia) throws PeajeException {
        Administrador usuarioLogueado = FachadaServicio.getInstancia().loginUsuarioAdministrador(cedula,
                contrasenia);

        sesionHttp.setAttribute("usuarioLogueado", usuarioLogueado);
        return Respuesta.lista(new Respuesta("loginExitoso", "menuAdministrador.html"));
        //FALTA CREAR LAS VISTAS DE LOS MENUS
    }

}
