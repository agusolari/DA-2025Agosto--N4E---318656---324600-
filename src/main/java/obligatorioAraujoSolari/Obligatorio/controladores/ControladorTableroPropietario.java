package obligatorioAraujoSolari.Obligatorio.controladores;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import obligatorioAraujoSolari.Obligatorio.dominio.Propietario;
import obligatorioAraujoSolari.Obligatorio.utils.Respuesta;

@RestController
@RequestMapping("/tableroPropietario")
public class ControladorTableroPropietario {

    @PostMapping("/cargarVista")
    public List<Respuesta> inicializarVista(@SessionAttribute(name = "usuarioLogueado", required=false) Propietario usuario){
         if (usuario == null) {
             // Manejar el caso en que el usuario no está en la sesión pide redireccionar a la página de login
             return Respuesta.lista(new Respuesta("usuarioNoAutenticado", "loginPropietario.html"));
         }
         return Respuesta.lista(new Respuesta("cedula", usuario.getCedula()));

    }

}
