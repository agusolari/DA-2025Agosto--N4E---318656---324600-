package obligatorioAraujoSolari.dtos;

import java.time.LocalDateTime;

import obligatorioAraujoSolari.Obligatorio.dominio.Transito;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransitoDto {
    private String vehiculoMatricula;
    private String puestoPeajeNombre;
    private Double tarifa;
    private Double descuento;
    private Double montoPagado;
    private LocalDateTime fechaTransito;
    private String bonificacion;

    public TransitoDto() {}

    public TransitoDto(Transito transito) {
        this.vehiculoMatricula = transito.getVehiculo() != null ? transito.getVehiculo().getMatricula() : null;
        this.puestoPeajeNombre = transito.getPuestoPeaje() != null ? transito.getPuestoPeaje().getNombre() : null;
        this.tarifa = transito.getTarifa() != null ? transito.getTarifa().getMonto() : 0.0;
        this.fechaTransito = transito.getFechaHora();
        
        // Usar el monto pagado guardado en el tránsito
        this.montoPagado = transito.getMontoPagado();
        this.descuento = this.tarifa - this.montoPagado;
        
        if (transito.getBonificacion() != null) {
            this.bonificacion = transito.getBonificacion().getNombre();
        } else {
            this.bonificacion = "Ninguna";
        }
    }
}
