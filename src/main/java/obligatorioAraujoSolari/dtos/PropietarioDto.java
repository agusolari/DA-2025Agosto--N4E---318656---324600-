package obligatorioAraujoSolari.dtos;

public class PropietarioDto {
    private String nombreCompleto;
    private String estadoNombre;
    private double saldo;

    public PropietarioDto(String nombreCompleto, String estadoNombre, double saldo) {
        this.nombreCompleto = nombreCompleto;
        this.estadoNombre = estadoNombre;
        this.saldo = saldo;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getEstadoNombre() {
        return estadoNombre;
    }

    public double getSaldo() {
        return saldo;
    }
}
