package obligatorioAraujoSolari.Obligatorio.controladores;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import obligatorioAraujoSolari.Obligatorio.dominio.Administrador;
import obligatorioAraujoSolari.Obligatorio.dominio.Bonificacion;
import obligatorioAraujoSolari.Obligatorio.dominio.BonificacionTrabajadores;
import obligatorioAraujoSolari.Obligatorio.dominio.BonificacionFrecuentes;
import obligatorioAraujoSolari.Obligatorio.dominio.BonificacionExonerados;
import obligatorioAraujoSolari.Obligatorio.dominio.Propietario;
import obligatorioAraujoSolari.Obligatorio.dominio.PuestoPeaje;
import obligatorioAraujoSolari.Obligatorio.excepciones.PeajeException;
import obligatorioAraujoSolari.Obligatorio.servicios.fachada.FachadaServicio;
import obligatorioAraujoSolari.Obligatorio.utils.Respuesta;
import obligatorioAraujoSolari.dtos.BonificacionDto;

@RestController
@RequestMapping("bonificaciones")
public class ControladorBonificaciones {

    @PostMapping("/cargarVistaBonificaciones")
    public List<Respuesta> cargarVistaBonificaciones(@SessionAttribute(name = "usuarioLogueado", required=false) Administrador usuario){
        if (usuario == null) {
            return Respuesta.lista(new Respuesta("usuarioNoAutenticado", "loginAdministrador.html"));
        }
        return Respuesta.lista(new Respuesta("vistaBonificaciones", "asignarBonificaciones.html"));
    }

    @PostMapping("/obtenerBonificaciones")
    public List<Respuesta> obtenerBonificaciones(@SessionAttribute(name = "usuarioLogueado", required=false) Administrador usuario) throws PeajeException {
        if (usuario == null) {
            throw new PeajeException("Usuario no autenticado");
        }
        
        List<BonificacionDto> bonificaciones = new ArrayList<>();
        return Respuesta.lista(new Respuesta("bonificacionesActivas", bonificaciones));
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
        
        try {
            PuestoPeaje puesto = FachadaServicio.getInstancia().getPuestoPeajePorNombre(puestoPeaje);
            Propietario propietario = FachadaServicio.getInstancia().obtenerPropietarioPorCedula(cedula);
            
            if (propietario == null) {
                throw new PeajeException("Propietario no encontrado");
            }
            if (puesto == null) {
                throw new PeajeException("Puesto de peaje no encontrado");
            }
            
            if (!propietario.getEstado().puedeLoguear()) {
                throw new PeajeException("El propietario está deshabilitado. No se pueden asignar bonificaciones");
            }
            
            for (Bonificacion b : propietario.getBonificaciones()) {
                if (b.getPuestoPeaje().getNombre().equals(puesto.getNombre())) {
                    return Respuesta.lista(new Respuesta("bonificacionYaAsignada", 
                        "Ya tiene una bonificación para el puesto seleccionado"));
                }
            }
            
            // Crear la bonificación según el tipo
            Bonificacion bonificacion = crearBonificacionPorTipo(tipo, propietario, puesto);
            
            if (bonificacion == null) {
                throw new PeajeException("Tipo de bonificación no válido");
            }
            
            FachadaServicio.getInstancia().asignarBonificacionAPropietario(propietario, bonificacion, puesto);
            return Respuesta.lista(new Respuesta("bonificacionAsignada", "Bonificación asignada exitosamente"));
        } catch (Exception e) {
            throw new PeajeException("Error al asignar bonificación: " + e.getMessage());
        }
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

     private Bonificacion crearBonificacionPorTipo(String tipo, Propietario propietario, PuestoPeaje puesto) {
        switch (tipo) {
            case "Trabajadores":
                return new BonificacionTrabajadores(propietario, puesto);
            case "Frecuentes":
                return new BonificacionFrecuentes(propietario, puesto);
            case "Exonerado":
                return new BonificacionExonerados(propietario, puesto);
            default:
                return null;
        }
    }
}

