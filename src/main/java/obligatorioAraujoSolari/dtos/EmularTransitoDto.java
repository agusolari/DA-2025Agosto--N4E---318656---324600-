package obligatorioAraujoSolari.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class EmularTransitoDto {
    @Getter
    @Setter
    private String nombrePuesto;
    @Getter
    @Setter
    private String matricula;
    @Getter
    @Setter
    private LocalDateTime fechaTransito;

    public EmularTransitoDto(String nombrePuesto, String matricula, LocalDateTime fechaTransito) {
        this.nombrePuesto = nombrePuesto;
        this.matricula = matricula;
        this.fechaTransito = fechaTransito;
    }
}