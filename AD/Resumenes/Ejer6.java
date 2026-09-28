package EjerciciosFicheros;

import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.util.Scanner;
public class Ejer6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try (
            // FileWriter abre (o crea) alumnos.csv para escribir en el
            FileWriter fw = new FileWriter("alumnos.csv");
            // Usamos el constructor completo de CSVWriter para que el fichero
            // quede como el ejemplo del enunciado (Ana,García,20,8.5):
            //  - ','  -> caracter separador
            //  - NO_QUOTE_CHARACTER -> no pone cada campo entre comillas
            //    (con el constructor simple todos los campos irian entre "")
            //  - los dos ultimos parametros son los valores por defecto
            CSVWriter escritor = new CSVWriter(
                fw,
                ',',
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END
            )
        ) {
            // Escribimos la linea de cabecera con el nombre de cada columna.
            // writeNext() recibe un array de Strings y escribe una linea completa
            escritor.writeNext(new String[]{"nombre", "apellido", "edad", "nota"});

            System.out.println("Escribe los datos del CSV");

            // Repetimos 3 veces para pedir los datos de 3 alumnos
            for (int i = 0; i < 3; i++) {
                System.out.println("Introduce el nombre: ");
                String nombre = scanner.nextLine();

                System.out.println("Introduce el apellido: ");
                String apellido = scanner.nextLine();

                System.out.println("Introduce la edad: ");
                String edad = scanner.nextLine();

                System.out.println("Introduce la nota: ");
                String nota = scanner.nextLine();

                // Escribimos el registro completo del alumno en el CSV
                escritor.writeNext(new String[]{nombre, apellido, edad, nota});

                // (Aqui había un scanner.nextLine() de mas: como todos los
                // datos ya se leen con nextLine(), no queda ningun salto de
                // linea pendiente y esa llamada se comia la siguiente respuesta)
            }
        } catch (Exception e) {
            // Capturamos cualquier error al escribir el fichero
            System.out.println("Error al escribir el fichero.");
        }
    }
}
