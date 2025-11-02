package obligatorioAraujoSolari.Obligatorio.servicios;

import java.util.ArrayList;
import java.util.List;

import obligatorioAraujoSolari.Obligatorio.dominio.PuestoPeaje;
import obligatorioAraujoSolari.Obligatorio.dominio.Tarifa;
import obligatorioAraujoSolari.Obligatorio.excepciones.PeajeException;

public class ServicioPeajes {
    private List<PuestoPeaje> puestos;
    private List<Tarifa> tarifas;

    public ServicioPeajes() {
        puestos = new ArrayList<>();
        tarifas = new ArrayList<>();
    }

    public void agregarPuesto(PuestoPeaje puesto) {
        puestos.add(puesto);
    }

    public List<PuestoPeaje> getPuestos() {
        return puestos;
    }

    public PuestoPeaje getPuestoPeajePorNombre(String nombre) throws PeajeException {
        for (PuestoPeaje puesto : puestos) {
            if (puesto.getNombre().equals(nombre)) {
                return puesto;
            }
        }
        throw new PeajeException(nombre + " no es un puesto de peaje válido.");
    }

    public void agregarTarifa(Tarifa tarifa) {
        tarifas.add(tarifa);
    }

    public List<Tarifa> getTarifas() {
        return tarifas;
    }
}
