package obligatorioAraujoSolari.dtos;

import lombok.Getter;
import lombok.Setter;

public class EstadoDto {
    @Getter
    @Setter
    private String nombreEstado;

    public EstadoDto(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }
}
