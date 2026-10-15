package modelo;


public class Cliente {

    private final String nombre;
    private final String numeroCuenta;
    private double saldo;

    public Cliente(String nombre, String numeroCuenta, double saldo) {
        this.nombre = nombre;
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getUltimosCuatroDigitos() {
        int n = numeroCuenta.length();
        return numeroCuenta.substring(Math.max(0, n - 4));
    }

    public void descontar(double total) {
        if (total > saldo) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }
        saldo -= total;
    }
}
