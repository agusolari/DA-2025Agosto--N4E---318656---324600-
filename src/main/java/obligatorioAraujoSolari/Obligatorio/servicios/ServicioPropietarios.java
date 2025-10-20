package obligatorioAraujoSolari.Obligatorio.servicios;

import java.util.ArrayList;
import java.util.List;

import obligatorioAraujoSolari.Obligatorio.dominio.Propietario;

public class ServicioPropietarios {
    private List<Propietario> propietarios;

    public ServicioPropietarios() {
        this.propietarios = new ArrayList<>();
    }

    public Propietario login (String cedula, String contrasena) {
        for (Propietario p : propietarios) {
            if (p.getCedula().equals(cedula) && p.esContraseñaValida(contrasena)) {
                return p;
            }
        }
        return null; //lanzar exepcion
    } 

}
