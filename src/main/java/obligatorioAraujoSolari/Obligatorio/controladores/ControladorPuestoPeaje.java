package obligatorioAraujoSolari.Obligatorio.controladores;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import obligatorioAraujoSolari.Obligatorio.dominio.PuestoPeaje;
import obligatorioAraujoSolari.Obligatorio.dominio.Tarifa;
import obligatorioAraujoSolari.Obligatorio.excepciones.PeajeException;
import obligatorioAraujoSolari.Obligatorio.servicios.fachada.FachadaServicio;
import obligatorioAraujoSolari.Obligatorio.utils.Respuesta;
import obligatorioAraujoSolari.dtos.PuestosPeajeDto;
import obligatorioAraujoSolari.dtos.TarifaDto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("puestoPeaje")
public class ControladorPuestoPeaje {

    @PostMapping("/obtenerPuestos")
    public List<Respuesta> obtenerPuestos() throws PeajeException {
        return Respuesta.lista(new Respuesta("puestosPeaje", mapPuestoPeaje()));
    }

    @PostMapping("/obtenerTarifasPorPuesto")
    public List<Respuesta> obtenerTarifasPorPuesto(@RequestParam String nombrePuesto) throws PeajeException {
        return Respuesta.lista(new Respuesta("tarifas", mapTarifasPorPuesto(nombrePuesto)));
    }

    private Respuesta mapTarifasPorPuesto(String nombrePuesto) throws PeajeException {
        PuestoPeaje puestoEncontrado = FachadaServicio.getInstancia().getPuestoPeajePorNombre(nombrePuesto);

        List<Tarifa> tarifas = FachadaServicio.getInstancia().getTarifas();
        List<TarifaDto> listaTarifasDto = new ArrayList<>();
        for (Tarifa tarifa : tarifas) {
            if (tarifa.getPuestoPeaje().equals(puestoEncontrado)) {
                listaTarifasDto.add(new TarifaDto(tarifa.getMonto(), tarifa.getPuestoPeaje().getNombre(), tarifa.getCategoriasVehiculos()));
            }
        }
        return new Respuesta("tarifas", listaTarifasDto);
    }

    private Respuesta mapPuestoPeaje() {
        List<PuestoPeaje> puestos = FachadaServicio.getInstancia().getPuestosPeaje();
        List<PuestosPeajeDto> listaPuestosPeajeDto = new ArrayList<>();
        for (PuestoPeaje puesto : puestos) {
            listaPuestosPeajeDto.add(new PuestosPeajeDto(puesto.getNombre(), puesto.getUbicacion()));
        }
        return new Respuesta("puestosPeaje", listaPuestosPeajeDto);
    }
}
