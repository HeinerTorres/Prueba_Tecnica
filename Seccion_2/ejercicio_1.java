package Seccion_2;

class Producto {
    private int id;
    private String nombre;
    private double precio;
    private int cantidadEnStock;

    // Constructor de la clase Producto para inicializar los atributos
    public Producto(int id, String nombre, double precio, int cantidadEnStock) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidadEnStock = cantidadEnStock;
    }

    // Métodos getter y setter para acceder y modificar
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidadEnStock() {
        return cantidadEnStock;
    }

    public void setCantidadEnStock(int cantidadEnStock) {
        this.cantidadEnStock = cantidadEnStock;
    }

    public double calcularValorStock() {
        return precio * cantidadEnStock;
    }
}

public class ejercicio_1 {
    public static void main(String[] args) {
        // Creamos un objeto Producto con valores iniciales
        Producto producto = new Producto(
                101,
                "Portatil ASUS",
                79.99,
                15);

        System.out.println("Producto: " + producto.getNombre());
        System.out.println("Valor total en stock: $" + producto.calcularValorStock());
    }
}
