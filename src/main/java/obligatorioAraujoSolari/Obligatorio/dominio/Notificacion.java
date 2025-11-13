package obligatorioAraujoSolari.Obligatorio.dominio;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

public class Notificacion {

    @Getter
    @Setter
    private LocalDateTime fechaHora;
    @Getter
    @Setter
    private String mensaje;
    @Getter
    @Setter
    private Transito transito;
    @Getter
    @Setter
    private Propietario propietario;

    public Notificacion(LocalDateTime fechaHora, Transito transito, Propietario propietario) {
        this.fechaHora = fechaHora;
        this.transito = transito;
        this.propietario = propietario;
    }
    
    public Notificacion() {}
    
}
