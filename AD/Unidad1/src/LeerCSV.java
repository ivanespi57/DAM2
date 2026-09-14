
import java.io.FileReader;
import java.io.IOException;
import com.opencsv.CSVReader;

public class LeerCSV {

    public static void main(String[] args) {
        try(
            FileReader fr = new FileReader("alumnos.csv");
            CSVReader lector = new CSVReader(fr);
        ){
            String datos[];

            while ((datos = lector.readNext()) != null) {
                String nombre = datos[0];
                String edad = datos[1];
                String nota = datos[2];

                int edadEntera = Integer.parseInt(edad);
                double notaDecimal = Double.parseDouble(nota);
                
                System.out.println("Nombre: "+ nombre + " | Edad: " + edadEntera + " | Nota: " + notaDecimal);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}