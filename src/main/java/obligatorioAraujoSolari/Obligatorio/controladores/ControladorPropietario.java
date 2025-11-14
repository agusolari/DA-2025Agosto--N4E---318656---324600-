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
import obligatorioAraujoSolari.dtos.PropietarioDto;

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

