package obligatorioAraujoSolari.Obligatorio.dominio;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

public class Transito {
    @Getter 
    @Setter
    private Vehiculo vehiculo;
    @Getter
    @Setter
    private PuestoPeaje puestoPeaje;
    @Getter
    @Setter
    private Tarifa tarifa;
    @Getter
    @Setter
    private Notificacion notificacion;
    @Getter
    @Setter
    private Bonificacion bonificacion;
    @Getter
    @Setter
    private LocalDateTime fechaHora; //maneja mejor la fecha y hora 
    
    //constructo 
    public Transito(Vehiculo vehiculo, PuestoPeaje puestoPeaje, Tarifa tarifa, Notificacion notificacion,
            Bonificacion bonificacion) {
        this.vehiculo = vehiculo;
        this.puestoPeaje = puestoPeaje;
        this.tarifa = tarifa;
        this.notificacion = notificacion;
        this.bonificacion = bonificacion;
        //fechaHora seteara al momento de crear el transito
    }

    public Transito() {}

}
