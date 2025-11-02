package obligatorioAraujoSolari.dtos;

import lombok.Getter;

public class PuestosPeajeDto {

    @Getter
    private String nombre;
    @Getter
    private String ubicacion;

    public PuestosPeajeDto(String nombre, String ubicacion) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
    }

}
