package obligatorioAraujoSolari.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransitoResultadoDto {
    private String propietarioNombre;
    private String propietarioEstado;
    private String vehiculoMatricula;
    private String vehiculoCategoria;
    private String bonificacionNombre;
    private Double tarifaOriginal;
    private Double tarifaConBonificacion;
    private Double saldoFinal;

    public TransitoResultadoDto() {}

    public TransitoResultadoDto(String propietarioNombre, String propietarioEstado, 
                                String vehiculoMatricula, String vehiculoCategoria,
                                String bonificacionNombre, Double tarifaOriginal, 
                                Double tarifaConBonificacion, Double saldoFinal) {
        this.propietarioNombre = propietarioNombre;
        this.propietarioEstado = propietarioEstado;
        this.vehiculoMatricula = vehiculoMatricula;
        this.vehiculoCategoria = vehiculoCategoria;
        this.bonificacionNombre = bonificacionNombre;
        this.tarifaOriginal = tarifaOriginal;
        this.tarifaConBonificacion = tarifaConBonificacion;
        this.saldoFinal = saldoFinal;
    }
}
