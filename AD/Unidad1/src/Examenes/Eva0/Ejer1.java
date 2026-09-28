package Examenes.Eva0;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Ejer1{
    public static void main(String[] args) {

        CSVParser parser = new CSVParserBuilder().withSeparator(';').build();

        try (
            FileReader fr = new FileReader("expresiones.csv");
            FileWriter fw = new FileWriter("diccionario.txt", false);
            BufferedWriter escritor = new BufferedWriter(fw);
            CSVReader lector = new CSVReaderBuilder(fr).withCSVParser(parser).build();
        ) {
            String[] datos;

            while ((datos = lector.readNext()) != null) {
                String expresion = datos[0];
                String significado = datos[1];
                String categoria = datos[2];
                String ejemplo = datos[3];
                
                escritor.write("=== "+ expresion.toUpperCase() + " ===\n");
                escritor.write("Significado: "+ significado + "\n");
                escritor.write("Categoría: "+ categoria + "\n");
                escritor.write("Ejemplo: "+ ejemplo+ "\n\n");
            }

            System.out.println("Archivo creado/modificado");

        } catch (Exception e) {
            System.out.println("Error al leer expresiones.csv.");
        }
    }
}


