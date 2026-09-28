package EjerciciosFicheros;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Ejer7 {
    public static void main(String[] args) {
        try (
            // Lector del CSV original: CSVReader usa ',' por defecto, que es
            // el delimitador que dice el enunciado para productos.csv
            FileReader fr = new FileReader("productos.csv");
            CSVReader lector = new CSVReader(fr);

            // Escritor del nuevo fichero
            FileWriter fw = new FileWriter("productos_puntoycoma.csv");
            // Usamos el constructor completo de CSVWriter para cambiar el
            // separador a ';' y para NO poner los campos entre comillas
            // (NO_QUOTE_CHARACTER), de modo que el resultado tenga
            // "exactamente los mismos datos" y solo cambie el delimitador
            CSVWriter escritor = new CSVWriter(
                fw,
                ';',
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END
            );
        ) {
            String[] datos;

            // Leemos cada registro del CSV original (array de Strings)...
            while ((datos = lector.readNext()) != null) {
                // ...y lo escribimos tal cual en el nuevo fichero. Es
                // el propio CSVWriter el que coloca el ';' entre campos,
                // sin concatenar nada con "+"
                escritor.writeNext(datos);
            }

            System.out.println("Fichero productos_puntoycoma.csv generado correctamente.");

        } catch (Exception e) {
            System.out.println("Error al convertir el fichero.");
        }
    }
}
