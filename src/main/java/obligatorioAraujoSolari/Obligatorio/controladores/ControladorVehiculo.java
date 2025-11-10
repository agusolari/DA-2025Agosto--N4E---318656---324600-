package obligatorioAraujoSolari.Obligatorio.controladores;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import obligatorioAraujoSolari.Obligatorio.dominio.Propietario;
import obligatorioAraujoSolari.Obligatorio.dominio.Transito;
import obligatorioAraujoSolari.Obligatorio.dominio.Vehiculo;
import obligatorioAraujoSolari.Obligatorio.excepciones.PeajeException;
import obligatorioAraujoSolari.Obligatorio.servicios.fachada.FachadaServicio;
import obligatorioAraujoSolari.Obligatorio.utils.Respuesta;

import java.util.ArrayList;
import java.util.List;

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

        //TODO: Es necesario pasar un id para mostrar en la vista?
        return Respuesta.lista(new Respuesta("vehiculos", vehiculos));
    }

    @PostMapping("/obtenerTransitosporVehiculo")
    public List<Respuesta> obtenerTransitosporVehiculos(@RequestParam List<String> matriculas) throws PeajeException {
        List<Transito> transitos = new ArrayList<>();
        for (String matricula : matriculas) {
            Vehiculo vehiculo = FachadaServicio.getInstancia().obtenerVehiculoPorMatricula(matricula);
            transitos.addAll(FachadaServicio.getInstancia().obtenerTransitosPorVehiculo(vehiculo));
        }
        return Respuesta.lista(new Respuesta("transitos", transitos));
    }

}
