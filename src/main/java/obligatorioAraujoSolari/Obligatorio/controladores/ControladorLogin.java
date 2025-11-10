package obligatorioAraujoSolari.Obligatorio.controladores;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import obligatorioAraujoSolari.Obligatorio.dominio.Sesion;
import obligatorioAraujoSolari.Obligatorio.excepciones.PeajeException;
import obligatorioAraujoSolari.Obligatorio.servicios.fachada.FachadaServicio;
import obligatorioAraujoSolari.Obligatorio.utils.Respuesta;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("acceso")
public class ControladorLogin {

     @PostMapping("/loginPropietario")
    public List<Respuesta> login(HttpSession sesionHttp, @RequestParam String cedula, @RequestParam String contrasenia)
            throws PeajeException {
        Sesion sesion = FachadaServicio.getInstancia().loginUsuarioPropietario(cedula, contrasenia);

        sesionHttp.setAttribute("usuarioLogueado", sesion.getUsuario());
        sesionHttp.setAttribute("sesionUsuario", sesion);
        return Respuesta.lista(new Respuesta("loginExitoso", "tableroPropietario.html"));
    }

    @PostMapping("/loginAdministrador")
    public List<Respuesta> loginAdministrador(HttpSession sesionHttp, @RequestParam String cedula,
            @RequestParam String contrasenia) throws PeajeException {
        Sesion usuarioLogueado = FachadaServicio.getInstancia().loginUsuarioAdministrador(cedula,
                contrasenia);

        sesionHttp.setAttribute("usuarioLogueado", usuarioLogueado.getUsuario());
        sesionHttp.setAttribute("sesionUsuario", usuarioLogueado);
        return Respuesta.lista(new Respuesta("loginExitoso", "tableroAdministrador.html"));
    }

    @PostMapping("/logout")
    public List<Respuesta> logout(HttpSession sesionHttp) {
        Sesion sesion = (Sesion) sesionHttp.getAttribute("sesionUsuario");
        if (sesion != null) {
            FachadaServicio.getInstancia().logout(sesion);
            sesionHttp.removeAttribute("usuarioLogueado");
            sesionHttp.removeAttribute("sesionUsuario");
            sesionHttp.invalidate();
        }
        return Respuesta.lista(new Respuesta("usuarioNoAutenticado", "index.html"));
    }

}
