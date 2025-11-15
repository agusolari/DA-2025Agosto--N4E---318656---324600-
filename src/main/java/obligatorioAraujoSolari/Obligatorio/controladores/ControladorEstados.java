package obligatorioAraujoSolari.Obligatorio.controladores;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import obligatorioAraujoSolari.Obligatorio.dominio.Administrador;
import obligatorioAraujoSolari.Obligatorio.dominio.Estado;
import obligatorioAraujoSolari.Obligatorio.dominio.Propietario;
import obligatorioAraujoSolari.Obligatorio.excepciones.PeajeException;
import obligatorioAraujoSolari.Obligatorio.servicios.fachada.FachadaServicio;
import obligatorioAraujoSolari.Obligatorio.utils.Respuesta;
import obligatorioAraujoSolari.dtos.EstadoDto;

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

    @PostMapping("/obtenerEstados")
    public List<Respuesta> obtenerEstados(@SessionAttribute(name = "usuarioLogueado", required=false) Administrador usuario) throws PeajeException {
        if (usuario == null) {
            throw new PeajeException("Usuario no autenticado");
        }
        
        List<Estado> estados = FachadaServicio.getInstancia().getEstados();
        List<EstadoDto> estadosDto = new ArrayList<>();
        
        for (Estado estado : estados) {
            estadosDto.add(new EstadoDto(estado.getNombreEstado()));
        }
        
        return Respuesta.lista(new Respuesta("estadosDisponibles", estadosDto));
    }

    @PostMapping("/cambiarEstadoPropietario")
    public List<Respuesta> cambiarEstadoPropietario(
            @SessionAttribute(name = "usuarioLogueado", required=false) Administrador usuario,
            @RequestParam String cedula,
            @RequestParam String nuevoEstado) throws PeajeException {
        
        if (usuario == null) {
            throw new PeajeException("Usuario no autenticado");
        }
        
        Propietario propietario = FachadaServicio.getInstancia().obtenerPropietarioPorCedula(cedula);
        
        // Validar que el estado nuevo sea diferente al actual
        if (propietario.getEstado().getNombreEstado().equals(nuevoEstado)) {
            return Respuesta.lista(new Respuesta("estadoYaAsignado", "El propietario ya está en estado " + nuevoEstado));
        }
        
        // Cambiar el estado del propietario
        FachadaServicio.getInstancia().cambiarEstadoPropietario(propietario, nuevoEstado);
        
        return Respuesta.lista(new Respuesta("estadoCambiado", "Estado del propietario cambiado exitosamente"));
    }
    
}
