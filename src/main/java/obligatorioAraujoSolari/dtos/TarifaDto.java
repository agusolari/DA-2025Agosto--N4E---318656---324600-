package obligatorioAraujoSolari.dtos;

import java.util.List;

import lombok.Getter;
import obligatorioAraujoSolari.Obligatorio.dominio.CategoriaVehiculo;

public class TarifaDto {
    @Getter
    private double monto;
    @Getter
    private String puestoPeaje;
    @Getter
    private List<CategoriaVehiculo> categoriasVehiculos;

    public TarifaDto(double monto, String puestoPeaje, List<CategoriaVehiculo> categoriasVehiculos) {
        this.monto = monto;
        this.puestoPeaje = puestoPeaje;
        this.categoriasVehiculos = categoriasVehiculos;
    }
}
