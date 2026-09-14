import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Ejer1 {
 public static void main(String[] args) {

    try (
        FileReader fr = new FileReader("alumnos.txt");
        BufferedReader lector = new BufferedReader(fr);
    ) {
        int contLin = 0;
        int contCaract = 0;
        String linea;

        while ((linea = lector.readLine()) != null) {
            System.out.println(linea);
            contLin++;
            contCaract += linea.length();            
        }
        
        System.out.println("Tiene "+ contLin + " lineas y " + contCaract + " caracteres");

    } catch (IOException e) {
        System.out.println("Error al leer el fichero.");
    }
    }
}