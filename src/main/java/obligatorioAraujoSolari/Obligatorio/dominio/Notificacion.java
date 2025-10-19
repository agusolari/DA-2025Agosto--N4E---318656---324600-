package obligatorioAraujoSolari.Obligatorio.dominio;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

public class Notificacion {

    @Getter
    @Setter
    private Date fecha;
    @Getter
    @Setter
    private String mensaje;
    @Getter
    @Setter
    private Transito transito;
    @Getter
    @Setter
    private Propietario propietario;

    public Notificacion(Date fecha, String mensaje, Transito transito, Propietario propietario) {
        this.fecha = fecha;
        this.mensaje = mensaje;
        this.transito = transito;
        this.propietario = propietario;
    }
    
    public Notificacion() {}
    
}
