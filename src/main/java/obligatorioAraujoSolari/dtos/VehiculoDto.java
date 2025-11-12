package obligatorioAraujoSolari.dtos;

import lombok.Getter;
import lombok.Setter;
import obligatorioAraujoSolari.Obligatorio.dominio.Vehiculo;

public class VehiculoDto {
    @Getter
    @Setter
    private String matricula;
    @Getter
    @Setter
    private String color;
    @Getter
    @Setter
    private String modelo;
    @Getter
    @Setter
    private String categoria;
    @Getter
    @Setter
    private int transitos;

    public VehiculoDto(Vehiculo vehiculo) {
        this.matricula = vehiculo.getMatricula();
        this.color = vehiculo.getColor();
        this.modelo = vehiculo.getModelo();
        this.categoria = vehiculo.getCategoria() != null ? vehiculo.getCategoria().getNombre() : null;
        this.transitos = vehiculo.getTransitos() != null ? vehiculo.getTransitos().size() : 0;
    }

}
