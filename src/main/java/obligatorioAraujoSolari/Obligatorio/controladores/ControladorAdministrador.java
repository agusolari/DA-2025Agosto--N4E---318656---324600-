package obligatorioAraujoSolari.Obligatorio.controladores;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.ArrayList;
import java.util.List;

import obligatorioAraujoSolari.Obligatorio.dominio.Administrador;
import obligatorioAraujoSolari.Obligatorio.dominio.Bonificacion;
import obligatorioAraujoSolari.Obligatorio.dominio.Propietario;
import obligatorioAraujoSolari.Obligatorio.excepciones.PeajeException;
import obligatorioAraujoSolari.Obligatorio.servicios.fachada.FachadaServicio;
import obligatorioAraujoSolari.Obligatorio.utils.Respuesta;
import obligatorioAraujoSolari.dtos.BonificacionDto;

@RestController
@RequestMapping("administrador")
public class ControladorAdministrador {

    @PostMapping("/cargarVistaBonificaciones")
    public List<Respuesta> cargarVistaBonificaciones(@SessionAttribute(name = "usuarioLogueado", required=false) Administrador usuario){
        if (usuario == null) {
            return Respuesta.lista(new Respuesta("usuarioNoAutenticado", "loginAdministrador.html"));
        }
        return Respuesta.lista(new Respuesta("vistaBonificaciones", "asignarBonificaciones.html"));
    }

    @PostMapping("/cargarVistaCambioEstado")
    public List<Respuesta> cargarVistaCambioEstado(@SessionAttribute(name = "usuarioLogueado", required=false) Administrador usuario){
        if (usuario == null) {
            return Respuesta.lista(new Respuesta("usuarioNoAutenticado", "loginAdministrador.html"));
        }
        return Respuesta.lista(new Respuesta("vistaCambioEstado", "cambiarEstadoPropietarios.html"));
    }

    @PostMapping("/obtenerBonificaciones")
    public List<Respuesta> obtenerBonificaciones(@SessionAttribute(name = "usuarioLogueado", required=false) Administrador usuario) throws PeajeException {
        if (usuario == null) {
            throw new PeajeException("Usuario no autenticado");
        }
        
        // TODO: Implementar obtención de bonificaciones desde base de datos
        List<BonificacionDto> bonificaciones = new ArrayList<>();
        return Respuesta.lista(new Respuesta("bonificacionesActivas", bonificaciones));
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

    @PostMapping("/asignarBonificacion")
    public List<Respuesta> asignarBonificacion(
            @SessionAttribute(name = "usuarioLogueado", required=false) Administrador usuario,
            @RequestParam String cedula,
            @RequestParam String tipo,
            @RequestParam String puestoPeaje) throws PeajeException {
        
        if (usuario == null) {
            throw new PeajeException("Usuario no autenticado");
        }
        
        // TODO: Implementar lógica de asignación de bonificación
        return Respuesta.lista(new Respuesta("bonificacionAsignada", "Bonificación asignada exitosamente"));
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

    @PostMapping("/eliminarBonificacion")
    public List<Respuesta> eliminarBonificacion(
            @SessionAttribute(name = "usuarioLogueado", required=false) Administrador usuario,
            @RequestParam String id) throws PeajeException {
        
        if (usuario == null) {
            throw new PeajeException("Usuario no autenticado");
        }
        
        // TODO: Implementar eliminación de bonificación
        return Respuesta.lista(new Respuesta("bonificacionEliminada", "Bonificación eliminada exitosamente"));
    }
}
