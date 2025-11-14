package obligatorioAraujoSolari.Obligatorio.controladores;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import obligatorioAraujoSolari.Obligatorio.dominio.Administrador;
import obligatorioAraujoSolari.Obligatorio.dominio.Propietario;
import obligatorioAraujoSolari.Obligatorio.excepciones.PeajeException;
import obligatorioAraujoSolari.Obligatorio.servicios.fachada.FachadaServicio;
import obligatorioAraujoSolari.Obligatorio.utils.Respuesta;

@RestController
@RequestMapping("estados")
public class ControladorEstados {
    
    @PostMapping("/cargarVistaCambioEstado")
    public List<Respuesta> cargarVistaCambioEstado(@SessionAttribute(name = "usuarioLogueado", required=false) Administrador usuario){
        if (usuario == null) {
            return Respuesta.lista(new Respuesta("usuarioNoAutenticado", "loginAdministrador.html"));
        }
        return Respuesta.lista(new Respuesta("vistaCambioEstado", "cambiarEstadoPropietarios.html"));
    }

    @PostMapping("/cambiarEstadoPropietario")
    public List<Respuesta> cambiarEstadoPropietario(
            @SessionAttribute(name = "usuarioLogueado", required=false) Administrador usuario,
            @RequestParam String cedula,
            @RequestParam String nuevoEstado,
            @RequestParam(required = false) String motivo) throws PeajeException {
        
        if (usuario == null) {
            throw new PeajeException("Usuario no autenticado");
        }
        
        Propietario propietario = FachadaServicio.getInstancia().obtenerPropietarioPorCedula(cedula);
        if (propietario == null) {
            throw new PeajeException("Propietario no encontrado");
        }
        
        // TODO: Implementar cambio de estado
        // FachadaServicio.getInstancia().cambiarEstadoPropietario(propietario, nuevoEstado);
        
        return Respuesta.lista(new Respuesta("estadoCambiado", "Estado del propietario cambiado exitosamente"));
    }
    
}
