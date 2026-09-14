import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class LeerFichero {
 public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Introduce la letra que quieras contar");
    char let = scanner.nextLine().charAt(0);

    try (
        FileReader fr = new FileReader("texto.txt");
        BufferedReader lector = new BufferedReader(fr);
    ) {
        String linea;
        while ((linea = lector.readLine()) != null) {
            for ()
        }
    } catch (IOException e) {
        System.out.println("Error al leer el fichero.");
    }
    }
}