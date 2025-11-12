package obligatorioAraujoSolari.dtos;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import obligatorioAraujoSolari.Obligatorio.dominio.Notificacion;

public class NotificacionDto {

    @Getter
    @Setter
    private LocalDateTime fechaHora;
    @Getter
    @Setter
    private String mensaje;

    public NotificacionDto(Notificacion notificacion) {
        this.fechaHora = notificacion.getFechaHora();
        this.mensaje = notificacion.getMensaje();
    }

}
