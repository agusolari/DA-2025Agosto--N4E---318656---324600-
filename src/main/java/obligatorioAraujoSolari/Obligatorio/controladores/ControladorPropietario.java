package obligatorioAraujoSolari.Obligatorio.controladores;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import obligatorioAraujoSolari.Obligatorio.dominio.Propietario;
import obligatorioAraujoSolari.dtos.PropietarioDto;

@RestController
@RequestMapping("propietario")
public class ControladorPropietario {
    
    @GetMapping("obtenerUsuario")
    public List<Respuesta> obtenerUsuario(@RequestParam String cedula) throws PeajeException {
        Propietario propietario = FachadaServicio.getInstancia().getPropietarioPorCedula(cedula);
        if (propietario == null) {
            throw new PeajeException("Propietario no encontrado");
        }

        return Respuesta.lista(new Respuesta(mapPropietario(propietario)));
    }

    @GetMapping("obtenerBonificacionesPorCedula")
    public List<Respuesta> obtenerBonificacionesPorCedula(@RequestParam String cedula) throws PeajeException {
        Propietario propietario = FachadaServicio.getInstancia().getPropietarioPorCedula(cedula);
        if (propietario == null) {
            throw new PeajeException("Propietario no encontrado");
        }
        //TODO: Es necesario crear un DTO para bonificaciones?
        return Respuesta.lista(new Respuesta("bonificaciones", propietario.getBonificaciones()));
    }

    @GetMapping("obtenerNotificacionesPorCedula")
    public List<Respuesta> obtenerNotificacionesPorCedula(@RequestParam String cedula) throws PeajeException {
        Propietario propietario = FachadaServicio.getInstancia().getPropietarioPorCedula(cedula);
        if (propietario == null) {
            throw new PeajeException("Propietario no encontrado");
        }
        return Respuesta.lista(new Respuesta("notificaciones", propietario.getNotificaciones()));
    }
    
}

private Respuesta mapPropietario(Propietario propietario) {
    return new Respuesta("propietario", new PropietarioDto(propietario.getNombreCompleto(), propietario.getEstado(), propietario.getSaldo()));
}
