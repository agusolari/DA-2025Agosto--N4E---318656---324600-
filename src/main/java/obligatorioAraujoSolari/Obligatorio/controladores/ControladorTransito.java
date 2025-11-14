package obligatorioAraujoSolari.Obligatorio.controladores;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
import obligatorioAraujoSolari.dtos.TransitoDto;
import obligatorioAraujoSolari.dtos.TransitoResultadoDto;

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
    public List<Respuesta> emularTransito(@SessionAttribute(name = "usuarioLogueado", required=false) Administrador usuario,@RequestParam String nombrePuesto, @RequestParam String matricula, @RequestParam String fechaTransito) throws PeajeException {
        
        if (usuario == null) {
             // Manejar el caso en que el usuario no está en la sesión pide redireccionar a la página de login
             return Respuesta.lista(new Respuesta("usuarioNoAutenticado", "loginAdministrador.html"));
         }

        try {
            Vehiculo vehiculo = FachadaServicio.getInstancia().obtenerVehiculoPorMatricula(matricula);
            PuestoPeaje puestoPeaje = FachadaServicio.getInstancia().getPuestoPeajePorNombre(nombrePuesto);
            Tarifa tarifa = FachadaServicio.getInstancia().getTarifaPorCategoriaYPuesto(vehiculo.getCategoria(), puestoPeaje);
            Bonificacion bonificacion = FachadaServicio.getInstancia().obtenerBonificacionAplicable(vehiculo, puestoPeaje);
            
            Transito nuevoTransito = new Transito(vehiculo, puestoPeaje, tarifa, bonificacion);
            FachadaServicio.getInstancia().registrarTransito(nuevoTransito);

            LocalDateTime fechaTransitoDT = LocalDateTime.parse(fechaTransito.replace(" ", "T"));
            Notificacion notificacion = new Notificacion(fechaTransitoDT, nuevoTransito, vehiculo.getPropietario());
            vehiculo.getPropietario().agregarNotificacion(notificacion);

            String propietarioNombre = vehiculo.getPropietario().getNombreCompleto();
            String propietarioEstado = vehiculo.getPropietario().getEstado().getNombreEstado();
            String vehiculoMatricula = vehiculo.getMatricula();
            String vehiculoCategoria = vehiculo.getCategoria().getNombre();
            String bonificacionNombre = bonificacion != null ? bonificacion.getNombre() : "Ninguna";
            Double tarifaOriginal = tarifa.getMonto();
            Double tarifaConBonificacion = nuevoTransito.calcularTarifaFinal();
            vehiculo.getPropietario().actualizarSaldo(-tarifaConBonificacion);
            Double saldoFinal = vehiculo.getPropietario().getSaldo();

            TransitoResultadoDto dto = new TransitoResultadoDto(
                propietarioNombre, propietarioEstado, vehiculoMatricula, vehiculoCategoria,
                bonificacionNombre, tarifaOriginal, tarifaConBonificacion, saldoFinal
            );

            return Respuesta.lista(new Respuesta("emularTransito", dto));
        } catch (Exception e) {
            throw new PeajeException("Error al emular tránsito: " + e.getMessage());
        }
    }

    @PostMapping("/obtenerTransitosporVehiculo")
    public List<Respuesta> obtenerTransitosporVehiculos(@RequestParam List<String> matriculas) throws PeajeException {
        List<TransitoDto> transitosDto = new ArrayList<>();
        for (String matricula : matriculas) {
            Vehiculo vehiculo = FachadaServicio.getInstancia().obtenerVehiculoPorMatricula(matricula);
            List<Transito> transitos = FachadaServicio.getInstancia().obtenerTransitosPorVehiculo(vehiculo);
            
            for (Transito transito : transitos) {
                transitosDto.add(new TransitoDto(transito));
            }
        }
        return Respuesta.lista(new Respuesta("transitos", transitosDto));
    }

    //TODO: VER COMO MOSTRAR EN LA VISTA EL RESULTADO DEL EMULAR TRANSITO, CREAR VISTA QUE CONSUMA ESTE BACK
    // CONTEMPLAR CURSOS ALTERNATIVOS PARA MOSTRAR MENSAJES DE ERROR.
}