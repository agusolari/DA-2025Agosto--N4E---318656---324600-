package obligatorioAraujoSolari.Obligatorio.dominio;

import lombok.Getter;
import lombok.Setter;

public class Estado {
    @Getter
    @Setter
    private String nombre;
    @Getter
    @Setter
    private String descripcion;

    public Estado(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

}
