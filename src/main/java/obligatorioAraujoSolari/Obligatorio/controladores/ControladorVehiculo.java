package obligatorioAraujoSolari.Obligatorio.controladores;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import obligatorioAraujoSolari.Obligatorio.dominio.Propietario;
import obligatorioAraujoSolari.Obligatorio.dominio.Transito;
import obligatorioAraujoSolari.Obligatorio.dominio.Vehiculo;
import obligatorioAraujoSolari.Obligatorio.excepciones.PeajeException;
import obligatorioAraujoSolari.Obligatorio.servicios.fachada.FachadaServicio;
import obligatorioAraujoSolari.Obligatorio.utils.Respuesta;
import obligatorioAraujoSolari.dtos.TransitoDto;
import obligatorioAraujoSolari.dtos.VehiculoDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/vehiculos")
public class ControladorVehiculo {

    //TODO: Preguntar si está bien tener un controlador separado para cada entidad o si debería ir todo en el controlador del propietario
    @PostMapping("/obtenerVehiculosPorPropietario")
    public List<Respuesta> obtenerVehiculosPorPropietario(@RequestParam String cedulaPropietario) throws PeajeException {
        Propietario propietario = FachadaServicio.getInstancia().obtenerPropietarioPorCedula(cedulaPropietario);
        List<Vehiculo> vehiculos = FachadaServicio.getInstancia().obtenerVehiculosPorPropietario(propietario);
        
        List<VehiculoDto> vehiculosDto = vehiculos.stream()
            .map(VehiculoDto::new)
            .collect(Collectors.toList());

        //TODO: Es necesario pasar un id para mostrar en la vista?
        return Respuesta.lista(new Respuesta("vehiculos", vehiculosDto));
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

}
