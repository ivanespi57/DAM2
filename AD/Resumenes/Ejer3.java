package EjerciciosFicheros;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Ejer3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Contador de incidencias que ya existen en el fichero
        int contInc = 0;

        // Primero LEEMOS el fichero (si existe) para saber cuantas
        // incidencias hay ya y poder calcular el numero de la siguiente
        try (
            FileReader fr = new FileReader("incidencias.txt");
            BufferedReader lector = new BufferedReader(fr);
        ) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                // Solo contamos las lineas que tienen texto, asi una linea
                // en blanco no altera el numero de incidencia
                if (!linea.isEmpty()) {
                    contInc++;
                }
            }
        } catch (IOException e) {
            // Si el fichero todavia no existe (primera ejecucion), no
            // hay incidencias previas: seguimos con contInc a 0
            System.out.println("No existe el fichero todavía, se creará uno nuevo.");
        }

        System.out.println("Introduce la descripción de la incidencia: ");
        String descripcion = scanner.nextLine();

        // Ahora ESCRIBIMOS la nueva incidencia al final del fichero
        try (
            // El segundo parametro "true" indica modo AÑADIR (append):
            // no borra las incidencias anteriores
            FileWriter fw = new FileWriter("incidencias.txt", true);
            BufferedWriter escritor = new BufferedWriter(fw);
        ) {
            // IMPORTANTE: el incidencias.txt del ejercicio NO termina con
            // salto de linea. Si escribieramos directamente, la nueva
            // incidencia quedaria pegada al final de la ultima
            // ("...detenidoIncidencia 4: ..."). Por eso, si ya hay
            // incidencias, saltamos de linea ANTES de escribir la nueva
            // (en lugar de saltar despues, como en el Ejer2)
            if (contInc > 0) {
                escritor.newLine();
            }
            // El numero de la nueva incidencia es el siguiente al contador
            escritor.write("Incidencia " + (contInc + 1) + ": " + descripcion);

            System.out.println("Incidencia registrada correctamente.");

        } catch (IOException e) {
            System.out.println("Error al escribir el fichero.");
        }
    }
}
