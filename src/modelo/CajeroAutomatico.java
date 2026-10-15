package modelo;

import java.util.ArrayList;
import java.util.List;

public class CajeroAutomatico {


    public static final double DENOMINACION = 200.00;
    public static final double MONTO_MAXIMO = 4000.00;

    public static final double PORCENTAJE_COMISION = 0.015;

    private final Cliente cliente;

    public CajeroAutomatico(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<Double> obtenerMontosDisponibles() {
        List<Double> montos = new ArrayList<>();
        for (double m = DENOMINACION; m <= MONTO_MAXIMO; m += DENOMINACION) {
            montos.add(m);
        }
        return montos;
    }

    public double calcularComision(double monto) {
        return Math.round(monto * PORCENTAJE_COMISION * 100.0) / 100.0;
    }

    public EstadoCuenta generarEstadoCuenta(double monto) {
        validarMonto(monto);
        return new EstadoCuenta(cliente.getNombre(), cliente.getSaldo(),
                monto, calcularComision(monto));
    }

    public Recibo retirarEfectivo(double monto) {
        validarMonto(monto);
        double comision = calcularComision(monto);
        cliente.descontar(monto + comision);
        return new Recibo(cliente.getUltimosCuatroDigitos(), cliente.getNombre(),
                monto, comision);
    }

    private void validarMonto(double monto) {
        boolean multiplo = Math.abs(monto / DENOMINACION - Math.round(monto / DENOMINACION)) < 1e-9;
        if (monto < DENOMINACION || monto > MONTO_MAXIMO || !multiplo) {
            throw new IllegalArgumentException("Monto no disponible: " + monto);
        }
    }
}
