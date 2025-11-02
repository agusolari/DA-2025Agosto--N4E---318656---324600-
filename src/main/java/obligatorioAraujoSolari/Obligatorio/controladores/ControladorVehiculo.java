package obligatorioAraujoSolari.Obligatorio.controladores;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import obligatorioAraujoSolari.Obligatorio.dominio.Propietario;
import obligatorioAraujoSolari.Obligatorio.dominio.Vehiculo;
import obligatorioAraujoSolari.Obligatorio.excepciones.PeajeException;
import obligatorioAraujoSolari.Obligatorio.servicios.fachada.FachadaServicio;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/vehiculos")
public class ControladorVehiculo {
    @GetMapping("obtenerVehiculosPorPropietario")
    public List<Vehiculo> obtenerVehiculosPorPropietario(@RequestParam String cedulaPropietario) throws PeajeException {
        Propietario propietario = FachadaServicio.getInstancia().obtenerPropietarioPorCedula(cedulaPropietario);
        List<Vehiculo> vehiculos = FachadaServicio.getInstancia().obtenerVehiculosPorPropietario(propietario);
        
        return vehiculos;
    }
    
}
