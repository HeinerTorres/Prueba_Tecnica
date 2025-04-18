package Seccion_3;

import java.util.Random;
import java.util.Scanner;

public class ejercicio_2 {

    // Metodo que recibe la matriz de asientos y devuelve si es posible hacer una
    // reserva
    public static boolean verificar(int[][] asientos) {
        // Recorremos todas las filas de la matriz
        for (int i = 0; i < asientos.length; i++) {
            // Recorremos cada fila en busca de al menos dos asientos libres consecutivos
            for (int j = 0; j < asientos[i].length - 1; j++) {
                if (asientos[i][j] == 0 && asientos[i][j + 1] == 0) {
                    // Si encontramos dos asientos libres consecutivos, devolvemos true
                    return true;
                }
            }
        }
        // Si no encontramos dos asientos libres consecutivos, devolvemos false
        return false;
    }

    // Metodo para llenar la matriz aleatoriamente
    public static int[][] llenarMatriz(int filas, int columnas) {
        Random random = new Random();
        int[][] matriz = new int[filas][columnas];

        // Llenamos la matriz con valores aleatorios (0 o 1)
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matriz[i][j] = random.nextInt(2); // Genera un valor aleatorio 0 o 1
            }
        }

        return matriz;
    }

    // Metodo para imprimir la matriz de asientos
    public static void imprimirMatriz(int[][] asientos) {
        for (int i = 0; i < asientos.length; i++) {
            for (int j = 0; j < asientos[i].length; j++) {
                System.out.print(asientos[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Creamos un objeto Scanner para leer la entrada del usuario

        // Solicitar al usuario el tamaño de la matriz
        System.out.print("¿Cuantas filas tendra la matriz de asientos? ");
        int filas = scanner.nextInt();
        System.out.print("¿Cuantas columnas tendra la matriz de asientos? ");
        int columnas = scanner.nextInt();

        // Llenar la matriz con valores aleatorios
        int[][] asientos = llenarMatriz(filas, columnas);

        // Imprimir la matriz generada
        System.out.println("Matriz de asientos");
        imprimirMatriz(asientos);

        // Llamar al método para verificar si se puede hacer una reserva
        boolean resultado = verificar(asientos);

        if (resultado) {
            System.out.println("¡Reserva disponible!");
        } else {
            System.out.println("No hay asientos disponibles para la reserva.");
        }

        scanner.close();
    }
}
