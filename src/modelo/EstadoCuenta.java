package modelo;

public class EstadoCuenta {

    private final String nombre;
    private final double montoActual;
    private final double cantidadRetirar;
    private final double comision;
    private final double montoFinal;

    public EstadoCuenta(String nombre, double montoActual, double cantidadRetirar,
                        double comision) {
        this.nombre = nombre;
        this.montoActual = montoActual;
        this.cantidadRetirar = cantidadRetirar;
        this.comision = comision;
        this.montoFinal = montoActual - cantidadRetirar - comision;
    }

    public String getNombre() {
        return nombre;
    }

    public double getMontoActual() {
        return montoActual;
    }

    public double getCantidadRetirar() {
        return cantidadRetirar;
    }

    public double getComision() {
        return comision;
    }

    public double getMontoFinal() {
        return montoFinal;
    }

    public boolean esSuficiente() {
        return montoFinal >= 0;
    }
}
