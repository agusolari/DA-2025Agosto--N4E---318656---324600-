package obligatorioAraujoSolari.Obligatorio.controladores;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import obligatorioAraujoSolari.Obligatorio.dominio.Administrador;
import obligatorioAraujoSolari.Obligatorio.dominio.Bonificacion;
import obligatorioAraujoSolari.Obligatorio.dominio.Notificacion;
import obligatorioAraujoSolari.Obligatorio.dominio.Propietario;
import obligatorioAraujoSolari.Obligatorio.excepciones.PeajeException;
import obligatorioAraujoSolari.Obligatorio.servicios.fachada.FachadaServicio;
import obligatorioAraujoSolari.Obligatorio.utils.Respuesta;
import obligatorioAraujoSolari.dtos.BonificacionDto;
import obligatorioAraujoSolari.dtos.NotificacionDto;
import obligatorioAraujoSolari.dtos.PropietarioDto;

import java.util.stream.Collectors;

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

    @PostMapping("/obtenerPropietarios")
    public List<Respuesta> obtenerPropietarios(@SessionAttribute(name = "usuarioLogueado", required=false) Administrador usuario) throws PeajeException {
        if (usuario == null) {
            throw new PeajeException("Usuario no autenticado");
        }
        
        List<Propietario> propietarios = FachadaServicio.getInstancia().obtenerPropietarios();
        // TODO: Mapear a PropietarioDto para evitar referencias circulares
        return Respuesta.lista(new Respuesta("propietariosLista", propietarios));
    }
}

