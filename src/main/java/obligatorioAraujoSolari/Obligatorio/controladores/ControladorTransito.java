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
import obligatorioAraujoSolari.Obligatorio.dominio.PuestoPeaje;
import obligatorioAraujoSolari.Obligatorio.dominio.Tarifa;
import obligatorioAraujoSolari.Obligatorio.dominio.Transito;
import obligatorioAraujoSolari.Obligatorio.dominio.Vehiculo;
import obligatorioAraujoSolari.Obligatorio.excepciones.PeajeException;
import obligatorioAraujoSolari.Obligatorio.servicios.fachada.FachadaServicio;
import obligatorioAraujoSolari.Obligatorio.utils.Respuesta;
import obligatorioAraujoSolari.dtos.EmularTransitoDto;

@RestController
@RequestMapping("transito")
public class ControladorTransito {

    @PostMapping("/cargarVista")
    public List<Respuesta> cargarVista(@SessionAttribute(name = "usuarioLogueado", required=false) Administrador usuario){
         if (usuario == null) {
             // Manejar el caso en que el usuario no está en la sesión pide redireccionar a la página de login
             return Respuesta.lista(new Respuesta("usuarioNoAutenticado", "loginAdministrador.html"));
         }
        return Respuesta.lista(new Respuesta("emularTransito", "emularTransito.html"));
    }

    @PostMapping("/emularTransito")
    public List<Respuesta> emularTransito(@RequestParam EmularTransitoDto emularTransitoDto) throws PeajeException {
        try {
            Vehiculo vehiculo = FachadaServicio.getInstancia().obtenerVehiculoPorMatricula(emularTransitoDto.getMatricula());
            PuestoPeaje puestoPeaje = FachadaServicio.getInstancia().getPuestoPeajePorNombre(emularTransitoDto.getNombrePuesto());
            Tarifa tarifa = FachadaServicio.getInstancia().getTarifaPorCategoriaYPuesto(vehiculo.getCategoria(), puestoPeaje);
            Bonificacion bonificacion = FachadaServicio.getInstancia().obtenerBonificacionAplicable(vehiculo, puestoPeaje);
            
            Transito nuevoTransito = new Transito(vehiculo, puestoPeaje, tarifa, bonificacion);
            FachadaServicio.getInstancia().registrarTransito(nuevoTransito);

            Notificacion notificacion = new Notificacion(emularTransitoDto.getFechaTransito(), nuevoTransito ,vehiculo.getPropietario());
            vehiculo.getPropietario().agregarNotificacion(notificacion);

            return Respuesta.lista(new Respuesta("transitoEmulado", nuevoTransito));
        } catch (Exception e) {
            throw new PeajeException("Error al emular tránsito: " + e.getMessage());
        }
    }

    //TODO: VER COMO MOSTRAR EN LA VISTA EL RESULTADO DEL EMULAR TRANSITO, CREAR VISTA QUE CONSUMA ESTE BACK
    // CONTEMPLAR CURSOS ALTERNATIVOS PARA MOSTRAR MENSAJES DE ERROR.
}