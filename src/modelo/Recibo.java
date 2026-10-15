package modelo;


public class Recibo {

    private final String ultimosCuatroDigitos;
    private final String nombre;
    private final double cantidadRetirada;
    private final double comision;

    public Recibo(String ultimosCuatroDigitos, String nombre,
                  double cantidadRetirada, double comision) {
        this.ultimosCuatroDigitos = ultimosCuatroDigitos;
        this.nombre = nombre;
        this.cantidadRetirada = cantidadRetirada;
        this.comision = comision;
    }

    public String getUltimosCuatroDigitos() {
        return ultimosCuatroDigitos;
    }

    public String getNombre() {
        return nombre;
    }

    public double getCantidadRetirada() {
        return cantidadRetirada;
    }

    public double getComision() {
        return comision;
    }
}
