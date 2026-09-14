import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ContBuscLetras {
 public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Introduce la letra que quieras contar");
    String letra = scanner.nextLine();

    try (
        FileReader fr = new FileReader("texto.txt");
        BufferedReader lector = new BufferedReader(fr);
    ) {
        int cont = 0;
        String linea;
        while ((linea = lector.readLine()) != null) {
            if (linea.contains(letra)){
                for (int i = 0; i < linea.length(); i++){
                    if (linea.charAt(i) == letra.charAt(0)){
                        cont++;
                    }
                }
            }
        }
        System.out.println("El archivo tiene " + cont + " letras "+ letra);
    } catch (IOException e) {
        System.out.println("Error al leer el fichero.");
    }
    }
}