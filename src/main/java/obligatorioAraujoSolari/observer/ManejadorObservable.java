package obligatorioAraujoSolari.observer;

/**
 * Clase auxiliar que extiende Observable para usar mediante composición.
 * Permite que clases que ya extienden de otra clase puedan usar el patrón Observer.
 */
public class ManejadorObservable extends Observable {
    // No necesita ningún método adicional
    // Hereda subscribir(), desubscribir() y notificar() de Observable
}
