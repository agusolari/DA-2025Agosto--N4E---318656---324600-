package obligatorioAraujoSolari.dtos;

import obligatorioAraujoSolari.Obligatorio.dominio.Estado;

public class PropietarioDto {
    private String nombreCompleto;
    private Estado estado;
    private double saldo;

    public PropietarioDto(String nombreCompleto, Estado estado, double saldo) {
        this.nombreCompleto = nombreCompleto;
        this.estado = estado;
        this.saldo = saldo;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public Estado getEstado() {
        return estado;
    }

    public double getSaldo() {
        return saldo;
    }
}
