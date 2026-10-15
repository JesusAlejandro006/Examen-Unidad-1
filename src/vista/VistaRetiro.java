package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import modelo.EstadoCuenta;
import modelo.Recibo;


public class VistaRetiro extends JFrame {

    private final DefaultListModel<Double> modeloLista = new DefaultListModel<>();
    private final JList<Double> listaMontos = new JList<>(modeloLista);
    private final JTextArea areaDerecha = new JTextArea();
    private final JLabel lblTituloDerecha = new JLabel("Estado de cuenta");
    private final JLabel lblMensaje = new JLabel(" ");
    private final JButton btnRetirar = new JButton("Retirar efectivo");

    public VistaRetiro() {
        super("Cajero automático - Retirar efectivo");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        construirInterfaz();
        pack();
        setLocationRelativeTo(null);
    }

    private void construirInterfaz() {
        JPanel raiz = new JPanel(new BorderLayout(12, 12));
        raiz.setBorder(BorderFactory.createEmptyBorder(14, 14, 14, 14));
        raiz.setBackground(new Color(0x0B3C5D));

        JLabel titulo = new JLabel("Retirar efectivo", JLabel.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 22));
        titulo.setForeground(Color.WHITE);
        raiz.add(titulo, BorderLayout.NORTH);

        JPanel izquierda = new JPanel(new BorderLayout(6, 6));
        izquierda.setOpaque(false);
        JLabel lblIzq = new JLabel("Seleccione la cantidad weon");
        lblIzq.setForeground(Color.WHITE);
        lblIzq.setFont(new Font("SansSerif", Font.BOLD, 14));
        izquierda.add(lblIzq, BorderLayout.NORTH);
        listaMontos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaMontos.setFont(new Font("Monospaced", Font.BOLD, 16));
        listaMontos.setFixedCellHeight(30);
        listaMontos.setCellRenderer(new javax.swing.DefaultListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(JList<?> l, Object v,
                    int i, boolean sel, boolean foc) {
                return super.getListCellRendererComponent(l, formatoMoneda((Double) v),
                        i, sel, foc);
            }
        });
        JScrollPane scrollLista = new JScrollPane(listaMontos);
        scrollLista.setPreferredSize(new Dimension(190, 380));
        izquierda.add(scrollLista, BorderLayout.CENTER);
        raiz.add(izquierda, BorderLayout.WEST);

        JPanel derecha = new JPanel(new BorderLayout(6, 6));
        derecha.setOpaque(false);
        lblTituloDerecha.setForeground(Color.WHITE);
        lblTituloDerecha.setFont(new Font("SansSerif", Font.BOLD, 14));
        derecha.add(lblTituloDerecha, BorderLayout.NORTH);
        areaDerecha.setEditable(false);
        areaDerecha.setFont(new Font("Monospaced", Font.PLAIN, 14));
        areaDerecha.setMargin(new java.awt.Insets(10, 12, 10, 12));
        areaDerecha.setText("\n  Seleccione una cantidad\n  para ver su estado de cuenta.");
        JScrollPane scrollDer = new JScrollPane(areaDerecha);
        scrollDer.setPreferredSize(new Dimension(380, 380));
        derecha.add(scrollDer, BorderLayout.CENTER);
        raiz.add(derecha, BorderLayout.CENTER);

        // Parte inferior: mensaje y botón
        JPanel sur = new JPanel(new BorderLayout(6, 6));
        sur.setOpaque(false);
        lblMensaje.setForeground(new Color(0xFFD166));
        lblMensaje.setFont(new Font("SansSerif", Font.BOLD, 13));
        sur.add(lblMensaje, BorderLayout.CENTER);
        btnRetirar.setEnabled(false);
        btnRetirar.setFont(new Font("SansSerif", Font.BOLD, 14));
        sur.add(btnRetirar, BorderLayout.EAST);
        raiz.add(sur, BorderLayout.SOUTH);

        setContentPane(raiz);
    }


    public void addMontoListener(ListSelectionListener l) {
        listaMontos.addListSelectionListener(l);
    }

    public void addRetirarListener(ActionListener l) {
        btnRetirar.addActionListener(l);
    }


    public Double getMontoSeleccionado() {
        return listaMontos.getSelectedValue();
    }



    public void mostrarMontos(List<Double> montos) {
        modeloLista.clear();
        for (Double m : montos) {
            modeloLista.addElement(m);
        }
    }

    public void seleccionarMonto(double monto) {
        listaMontos.setSelectedValue(monto, true);
    }

    public void mostrarEstadoCuenta(EstadoCuenta e) {
        lblTituloDerecha.setText("Estado de cuenta");
        lblMensaje.setText(e.esSuficiente() ? " " : "Saldo insuficiente para este monto.");
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append(String.format("  Cliente:            %s%n", e.getNombre()));
        sb.append(String.format("  Monto actual:       %s%n", formatoMoneda(e.getMontoActual())));
        sb.append(String.format("  Cantidad a retirar: %s%n", formatoMoneda(e.getCantidadRetirar())));
        sb.append(String.format("  Comisión:           %s%n", formatoMoneda(e.getComision())));
        sb.append("  ----------------------------------\n");
        sb.append(String.format("  Monto final:        %s%n", formatoMoneda(e.getMontoFinal())));
        areaDerecha.setText(sb.toString());
        btnRetirar.setEnabled(e.esSuficiente());
    }

    public void mostrarRecibo(Recibo r) {
        lblTituloDerecha.setText("Recibo de retiro");
        lblMensaje.setText("Retiro realizado. Tome su efectivo.");
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("        RECIBO DE RETIRO\n");
        sb.append("  ----------------------------------\n");
        sb.append(String.format("  Cuenta:             ****%s%n", r.getUltimosCuatroDigitos()));
        sb.append(String.format("  Nombre:             %s%n", r.getNombre()));
        sb.append(String.format("  Cantidad retirada:  %s%n", formatoMoneda(r.getCantidadRetirada())));
        sb.append(String.format("  Cobro por comisión: %s%n", formatoMoneda(r.getComision())));
        sb.append("  ----------------------------------\n");
        sb.append("      Gracias por su preferencia\n");
        areaDerecha.setText(sb.toString());
        btnRetirar.setEnabled(false);
    }

    public void mostrarError(String mensaje) {
        lblMensaje.setText(mensaje);
    }

    public static String formatoMoneda(double valor) {
        String signo = valor < 0 ? "-" : "";
        return signo + String.format(Locale.US, "$%,.2f", Math.abs(valor));
    }
}
