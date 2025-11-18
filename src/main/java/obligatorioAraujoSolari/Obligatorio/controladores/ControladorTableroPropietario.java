package obligatorioAraujoSolari.Obligatorio.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import obligatorioAraujoSolari.Obligatorio.dominio.Propietario;
import obligatorioAraujoSolari.Obligatorio.utils.ConexionNavegador;
import obligatorioAraujoSolari.Obligatorio.utils.Respuesta;
import obligatorioAraujoSolari.observer.Observable;
import obligatorioAraujoSolari.observer.Observador;


@RestController
@Scope("session")
@RequestMapping("/tableroPropietario")
public class ControladorTableroPropietario implements Observador{

    private Propietario propietario;
    private final ConexionNavegador conexionNavegador;

    public ControladorTableroPropietario(@Autowired ConexionNavegador conexionNavegador) {
        this.conexionNavegador = conexionNavegador;
    }

    @PostMapping("/cargarVista")
    public List<Respuesta> inicializarVista(@SessionAttribute(name = "usuarioLogueado", required=false) Propietario usuario){
         if (usuario == null) {
             // Manejar el caso en que el usuario no está en la sesión pide redireccionar a la página de login
             return Respuesta.lista(new Respuesta("usuarioNoAutenticado", "loginPropietario.html"));
         }
         // Suscribimos el controlador a los cambios del usuario propietario
         propietario = usuario;
         usuario.subscribir(this);
         return Respuesta.lista(new Respuesta("cedula", usuario.getCedula()));
    }

    @PostMapping("/vistaDesconectada")
    public void vistaDesconectada() {
        if (propietario != null) {
            propietario.desubscribir(this);
            propietario = null;
        }
    }

    // Endpoint para registrar la conexión SSE, que permitirá enviar notificaciones en tiempo real al front
    @GetMapping(value = "registroSSE", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter registrarSSE() {
        this.conexionNavegador.conectarSSE();
        return this.conexionNavegador.getConexionSSE();
    }
    
    @Override
    public void actualizar(Observable origen, Object evento) {
        if(evento.equals(Observador.Evento.NOTIFICACION_AGREGADA) || 
           evento.equals(Observador.Evento.SALDO_ACTUALIZADO) || 
           evento.equals(Observador.Evento.BONIFICACION_ACTUALIZADA) || 
           evento.equals(Observador.Evento.ESTADO_ACTUALIZADO)) {

            conexionNavegador.enviarJSON(Respuesta.lista(new Respuesta("actualizarTablero", true)));
        }
    }
}
