package obligatorioAraujoSolari.dtos;

import lombok.Getter;
import lombok.Setter;
import obligatorioAraujoSolari.Obligatorio.dominio.Bonificacion;

public class BonificacionDto {
    @Getter
    @Setter
    private String nombre;
    @Getter
    @Setter
    private String descripcion;
    @Getter
    @Setter
    private String puestoPeaje;
    @Getter
    @Setter
    private String fechaAsignacion;

    public BonificacionDto(Bonificacion bonificacion) {
        this.nombre = bonificacion.getNombre();
        this.descripcion = bonificacion.getDescripcion();
        this.puestoPeaje = bonificacion.getPuestoPeaje() != null ? bonificacion.getPuestoPeaje().getNombre() : null;
        this.fechaAsignacion = bonificacion.getFechaAsignacion() != null ? bonificacion.getFechaAsignacion().toString() : null;
    }

}
