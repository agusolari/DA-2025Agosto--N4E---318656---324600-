package obligatorioAraujoSolari.Obligatorio.dominio;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import obligatorioAraujoSolari.Obligatorio.excepciones.PeajeException;

public class Tarifa {
    @Getter
    @Setter
    private double monto;
    @Getter
    @Setter
    private PuestoPeaje puestoPeaje;
    @Getter
    @Setter
    private List<CategoriaVehiculo> categoriasVehiculos;

    public Tarifa(double monto, PuestoPeaje puestoPeaje, List<CategoriaVehiculo> categoriasVehiculos) {
        this.monto = monto;
        this.puestoPeaje = puestoPeaje;
        this.categoriasVehiculos = categoriasVehiculos;
    }

    public void agregarCategoriaVehiculo(CategoriaVehiculo categoriaVehiculo) throws PeajeException {
        if (!categoriasVehiculos.contains(categoriaVehiculo)) {
            categoriasVehiculos.add(categoriaVehiculo);
        }
        else {
            throw new PeajeException("La categoría de vehículo ya está asociada a la tarifa.");
        }
    }

    public Tarifa() {}
}
