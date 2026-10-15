package principal;

import controlador.ControladorRetiro;
import javax.swing.SwingUtilities;
import modelo.CajeroAutomatico;
import modelo.Cliente;
import vista.VistaRetiro;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Cliente cliente = new Cliente("Juan Pérez López", "4152313398765432", 8500.00);
            CajeroAutomatico modelo = new CajeroAutomatico(cliente);
            VistaRetiro vista = new VistaRetiro();
            ControladorRetiro controlador = new ControladorRetiro(modelo, vista);
            controlador.iniciar();
        });
    }
}
