package obligatorioAraujoSolari.Obligatorio.servicios;

import java.util.ArrayList;
import java.util.List;

import obligatorioAraujoSolari.Obligatorio.dominio.CategoriaVehiculo;
import obligatorioAraujoSolari.Obligatorio.dominio.Propietario;
import obligatorioAraujoSolari.Obligatorio.dominio.Vehiculo;
import obligatorioAraujoSolari.Obligatorio.excepciones.PeajeException;

public class ServicioVehiculos {
    List<CategoriaVehiculo> categoriasVehiculos;
    List<Vehiculo> vehiculos;

    public ServicioVehiculos() {
        this.categoriasVehiculos = new ArrayList<>();
        this.vehiculos = new ArrayList<>();
    }

    public void crearCategoriaVehiculo(CategoriaVehiculo categoria) {
        categoriasVehiculos.add(categoria);
    }

    public List<CategoriaVehiculo> getCategoriasVehiculos() {
        return categoriasVehiculos;
    }

    public void crearVehiculo(Vehiculo vehiculo) {
        vehiculos.add(vehiculo);
    }

    //Está bien crear este método acá o sería mejor en el servicio de usuarios?
    public List<Vehiculo> obtenerVehiculosPorPropietario(Propietario propietario) throws PeajeException {
    List<Vehiculo> vehiculosPorPropietario = new ArrayList<>();
    for (Vehiculo vehiculo : vehiculos) {
        if (vehiculo.getPropietario().equals(propietario)) {
            vehiculosPorPropietario.add(vehiculo);
        }
    }
    if (vehiculosPorPropietario.isEmpty()) {
        throw new PeajeException("El propietario no tiene vehículos registrados.");
        
    }
    return vehiculosPorPropietario;
}
}
