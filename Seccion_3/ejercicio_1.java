package Seccion_3;

import java.util.Scanner;

public class ejercicio_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Creamos un objeto Scanner para leer la entrada del usuario

        // Pedimos al usuario cuantos numeros desea ingresar
        System.out.print("¿Cuantos numeros vas a ingresar? ");
        int cantidad = scanner.nextInt();

        // Validamos que al menos se ingresen 2 numeros
        while (cantidad < 2) {
            System.out.println("Debes ingresar al menos 2 numeros.");
            System.out.print("Ingresa una cantidad valida: ");
            cantidad = scanner.nextInt();
        }

        // Creamos un arreglo para almacenar los numeros ingresados
        int[] numeros = new int[cantidad];

        // Pedimos al usuario que ingrese cada numero
        for (int i = 0; i < cantidad; i++) {
            System.out.print("Numero " + (i + 1) + ": ");
            numeros[i] = scanner.nextInt();
        }

        // Inicializamos mayor y segundoMayor con el valor más bajo posible
        int mayor = Integer.MIN_VALUE;
        int segundoMayor = Integer.MIN_VALUE;

        // Recorremos el arreglo para encontrar el mayor y el segundo mayor
        for (int i = 0; i < numeros.length; i++) {
            if (numeros[i] > mayor) {
                // Si encontramos un nuevo numero mayor, el anterior pasa a ser segundoMayor
                segundoMayor = mayor;
                mayor = numeros[i];
            } else if (numeros[i] > segundoMayor && numeros[i] != mayor) {
                // Si el numero es menor que el mayor pero mayor que el segundoMayor
                segundoMayor = numeros[i];
            }
        }

        // Verificamos si logramos encontrar un segundo mayor distinto
        if (segundoMayor == Integer.MIN_VALUE) {
            System.out.println("No se pudo encontrar un segundo numero más grande.");
        } else {
            System.out.println("El segundo numero más grande es: " + segundoMayor);
        }

        scanner.close();
    }
}
