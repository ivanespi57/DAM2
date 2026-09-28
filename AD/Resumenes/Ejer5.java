package EjerciciosFicheros;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import java.io.FileReader;

public class Ejer5 {
    public static void main(String[] args) {

        // ---------- FICHERO 1: delimitador ',' ----------
        System.out.println("Datos de alumnos_comas.csv:");
        try (
            FileReader fr = new FileReader("alumnos_comas.csv");
            // La coma es el delimitador POR DEFECTO de OpenCSV, asi que
            // aqui basta con un CSVReader normal, sin parser personalizado
            CSVReader lector = new CSVReader(fr);
        ) {
            String[] datos;

            // readNext() devuelve un array con los campos de cada registro
            // (una linea) o null cuando llega al final del fichero
            while ((datos = lector.readNext()) != null) {
                for (String dato : datos) {
                    System.out.print(dato + " | ");
                }
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("Error al leer alumnos_comas.csv.");
        }

        System.out.println();

        // ---------- FICHERO 2: delimitador ';' ----------
        System.out.println("Datos de alumnos_puntoycoma.csv:");
        // Como ';' NO es el delimitador por defecto, creamos un parser
        // que use ';' como separador...
        CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
        try (
            FileReader fr = new FileReader("alumnos_puntoycoma.csv");
            // ...y se lo pasamos al CSVReaderBuilder para que construya un
            // CSVReader que separe los campos con ese parser
            CSVReader lector = new CSVReaderBuilder(fr).withCSVParser(parser).build();
        ) {
            String[] datos;

            // Aqui se ve la ventaja de OpenCSV frente a split(): la direccion
            // va entre comillas y contiene un ';' (p.ej. "Calle Valencia 12; 2ºB").
            // OpenCSV lo respeta y lo deja como UN solo campo
            while ((datos = lector.readNext()) != null) {
                for (String dato : datos) {
                    System.out.print(dato + " | ");
                }
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("Error al leer alumnos_puntoycoma.csv.");
        }
    }
}
