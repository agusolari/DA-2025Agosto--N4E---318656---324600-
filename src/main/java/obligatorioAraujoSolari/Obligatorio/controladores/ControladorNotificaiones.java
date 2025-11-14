package obligatorioAraujoSolari.Obligatorio.controladores;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import obligatorioAraujoSolari.Obligatorio.dominio.Notificacion;
import obligatorioAraujoSolari.Obligatorio.dominio.Propietario;
import obligatorioAraujoSolari.Obligatorio.excepciones.PeajeException;
import obligatorioAraujoSolari.Obligatorio.servicios.fachada.FachadaServicio;
import obligatorioAraujoSolari.Obligatorio.utils.Respuesta;
import obligatorioAraujoSolari.dtos.NotificacionDto;

@RestController
@RequestMapping("notificaiones")
public class ControladorNotificaiones {

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
