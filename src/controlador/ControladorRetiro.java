package controlador;

import javax.swing.event.ListSelectionEvent;
import modelo.CajeroAutomatico;
import modelo.EstadoCuenta;
import modelo.Recibo;
import vista.VistaRetiro;


public class ControladorRetiro {

    private final CajeroAutomatico modelo;
    private final VistaRetiro vista;

    public ControladorRetiro(CajeroAutomatico modelo, VistaRetiro vista) {
        this.modelo = modelo;
        this.vista = vista;
        this.vista.addMontoListener(this::alSeleccionarMonto);
        this.vista.addRetirarListener(e -> alRetirarEfectivo());
    }

    public void iniciar() {
        vista.mostrarMontos(modelo.obtenerMontosDisponibles());
        vista.setVisible(true);
    }

    public void alSeleccionarMonto(ListSelectionEvent ev) {
        if (ev.getValueIsAdjusting()) {
            return;
        }
        Double monto = vista.getMontoSeleccionado();
        if (monto == null) {
            return;
        }
        EstadoCuenta estado = modelo.generarEstadoCuenta(monto);
        vista.mostrarEstadoCuenta(estado);
    }

    public void alRetirarEfectivo() {
        Double monto = vista.getMontoSeleccionado();
        if (monto == null) {
            vista.mostrarError("Seleccione una cantidad.");
            return;
        }
        try {
            Recibo recibo = modelo.retirarEfectivo(monto);
            vista.mostrarRecibo(recibo);
        } catch (IllegalArgumentException ex) {
            vista.mostrarError(ex.getMessage());
        }
    }
}
