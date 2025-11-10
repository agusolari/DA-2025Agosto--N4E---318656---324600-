package obligatorioAraujoSolari.Obligatorio.controladores;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import obligatorioAraujoSolari.Obligatorio.dominio.Bonificacion;
import obligatorioAraujoSolari.Obligatorio.dominio.Propietario;
import obligatorioAraujoSolari.Obligatorio.excepciones.PeajeException;
import obligatorioAraujoSolari.Obligatorio.servicios.fachada.FachadaServicio;
import obligatorioAraujoSolari.Obligatorio.utils.Respuesta;
import obligatorioAraujoSolari.dtos.PropietarioDto;

@RestController
@RequestMapping("propietario")
public class ControladorPropietario {
    
    @GetMapping("obtenerUsuario")
    public List<Respuesta> obtenerUsuario(@RequestParam String cedula) throws PeajeException {
        Propietario propietario = FachadaServicio.getInstancia().obtenerPropietarioPorCedula(cedula);
        if (propietario == null) {
            throw new PeajeException("Propietario no encontrado");
        }

        return Respuesta.lista(new Respuesta("propietario",mapPropietario(propietario)));
    }

    @GetMapping("obtenerBonificacionesPorCedula")
    public List<Respuesta> obtenerBonificacionesPorCedula(@RequestParam String cedula) throws PeajeException {
        Propietario propietario = FachadaServicio.getInstancia().obtenerPropietarioPorCedula(cedula);
        if (propietario == null) {
            throw new PeajeException("Propietario no encontrado");
        }
        //TODO: Es necesario crear un DTO para bonificaciones?
        List<Bonificacion> bonificaciones = propietario.getBonificaciones();
        return Respuesta.lista(new Respuesta("bonificaciones", bonificaciones));
    }

    @GetMapping("obtenerNotificacionesPorCedula")
    public List<Respuesta> obtenerNotificacionesPorCedula(@RequestParam String cedula) throws PeajeException {
        Propietario propietario = FachadaServicio.getInstancia().obtenerPropietarioPorCedula(cedula);
        if (propietario == null) {
            throw new PeajeException("Propietario no encontrado");
        }
        return Respuesta.lista(new Respuesta("notificaciones", propietario.getNotificaciones()));
    }
    
    private Respuesta mapPropietario(Propietario propietario) throws PeajeException {
        if (propietario == null) {
            throw new PeajeException("Propietario no encontrado");
        }
        return new Respuesta("propietario", new PropietarioDto(propietario.getNombreCompleto(), propietario.getEstado(), propietario.getSaldo()));
    }
}

