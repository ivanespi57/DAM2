package EjerciciosFicheros;

import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.util.Scanner;
public class Ejer6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try (
            FileWriter fw = new FileWriter("alumnos.csv");
            CSVWriter escritor = new CSVWriter(fw)
        ) {
            escritor.writeNext(new String[]{"nombre", "apellido", "edad", "nota"});

            System.out.println("Escribe los datos del CSV");

            for (int i = 0; i < 3; i++) {
                System.out.println("Introduce el nombre: ");
                String nombre = scanner.nextLine();

                System.out.println("Introduce el apellido: ");
                String apellido = scanner.nextLine();

                System.out.println("Introduce la edad: ");
                String edad = scanner.nextLine();

                System.out.println("Introduce la nota: ");
                String nota = scanner.nextLine();

                escritor.writeNext(new String[]{nombre, apellido, edad, nota});
                
                scanner.nextLine();
            }
        } catch (Exception e) {
            System.out.println("Error al escribir el fichero.");
        }
    }
}