package obligatorioAraujoSolari.Obligatorio.dominio;

import lombok.Getter;

public abstract class Usuario {
    @Getter
    private String cedula;

    private String contrasenia;

    @Getter
    private String nombreCompleto;

    public Usuario(String cedula, String contrasenia, String nombreCompleto) {
        this.cedula = cedula;
        this.contrasenia = contrasenia;
        this.nombreCompleto = nombreCompleto;
    }

    public boolean esContraseniaValida(String contrasenia) {
        return this.contrasenia.equals(contrasenia);
    }
}
