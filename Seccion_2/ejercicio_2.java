package Seccion_2;

class Cliente {

    private String nombre;
    private int edad;

    // Constructor que asigna los valores correctamente
    public Cliente(String nombre, int edad) {
        this.nombre = nombre; // "this" se refiere al atributo de la clase
        this.edad = edad;
    }

    public void mostrarInformacion() {
        System.out.println("Cliente: " + nombre + ", Edad: " + edad);
    }
}

public class ejercicio_2 {
    public static void main(String[] args) {
        Cliente cliente = new Cliente("Santiago", 21);
        cliente.mostrarInformacion();
    }
}
