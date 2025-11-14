package obligatorioAraujoSolari.Obligatorio.controladores;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import obligatorioAraujoSolari.Obligatorio.dominio.Bonificacion;
import obligatorioAraujoSolari.Obligatorio.dominio.Notificacion;
import obligatorioAraujoSolari.Obligatorio.dominio.Propietario;
import obligatorioAraujoSolari.Obligatorio.excepciones.PeajeException;
import obligatorioAraujoSolari.Obligatorio.servicios.fachada.FachadaServicio;
import obligatorioAraujoSolari.Obligatorio.utils.Respuesta;
import obligatorioAraujoSolari.dtos.PropietarioDto;
import obligatorioAraujoSolari.dtos.BonificacionDto;
import obligatorioAraujoSolari.dtos.NotificacionDto;

@RestController
@RequestMapping("propietario")
public class ControladorPropietario {
    
    @PostMapping("obtenerUsuario")
    public List<Respuesta> obtenerUsuario(@RequestParam String cedula) throws PeajeException {
        Propietario propietario = FachadaServicio.getInstancia().obtenerPropietarioPorCedula(cedula);
        if (propietario == null) {
            throw new PeajeException("Propietario no encontrado");
        }

        String estadoNombre = propietario.getEstado() != null ? propietario.getEstado().getNombreEstado() : "No disponible";
        PropietarioDto dto = new PropietarioDto(propietario.getNombreCompleto(), estadoNombre, propietario.getSaldo());
        return Respuesta.lista(new Respuesta("propietario", dto));
    }

    @PostMapping("obtenerBonificacionesPorCedula")
    public List<Respuesta> obtenerBonificacionesPorCedula(@RequestParam String cedula) throws PeajeException {
        Propietario propietario = FachadaServicio.getInstancia().obtenerPropietarioPorCedula(cedula);
        if (propietario == null) {
            throw new PeajeException("Propietario no encontrado");
        }
        List<Bonificacion> bonificaciones = propietario.getBonificaciones();
        List<BonificacionDto> bonificacionesDto = bonificaciones.stream()
            .map(BonificacionDto::new)
            .collect(Collectors.toList());
        return Respuesta.lista(new Respuesta("bonificaciones", bonificacionesDto));
    }

    @PostMapping("obtenerNotificacionesPorCedula")
    public List<Respuesta> obtenerNotificacionesPorCedula(@RequestParam String cedula) throws PeajeException {
        Propietario propietario = FachadaServicio.getInstancia().obtenerPropietarioPorCedula(cedula);
        if (propietario == null) {
            throw new PeajeException("Propietario no encontrado");
        }
        List<Notificacion> notificaciones = propietario.getNotificaciones();
        List<NotificacionDto> notificacionesDto = notificaciones.stream()
            .map(NotificacionDto::new)
            .collect(Collectors.toList());
        return Respuesta.lista(new Respuesta("notificaciones", notificacionesDto));
    }
}

