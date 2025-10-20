package obligatorioAraujoSolari.Obligatorio.servicios.fachada;

import obligatorioAraujoSolari.Obligatorio.dominio.Propietario;
import obligatorioAraujoSolari.Obligatorio.servicios.ServicioPropietarios;

public class FachadaServicio {

    private static FachadaServicio instancia;
    private ServicioPropietarios servicioPropietarios;

    private FachadaServicio() {
        servicioPropietarios = new ServicioPropietarios();
    }

    public static FachadaServicio getInstancia() {
        if (instancia == null) {
            instancia = new FachadaServicio();
        }
        return instancia;
    }

    public Propietario login(String cedula, String contrasena) {
        return servicioPropietarios.login(cedula, contrasena);
    }

    
}
