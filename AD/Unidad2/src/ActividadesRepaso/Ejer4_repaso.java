import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Ejer4_repaso: PreparedStatement sobre la tabla alumno.
 * Los ? son marcadores de posición; los parámetros se numeran desde 1.
 */
public class Ejer4_repaso {
    public static void main(String[] args) {
        String url = "jdbc:mysql://IP_VM:3306/instituto";
        String usuario = "root";
        String password = "";
        Scanner sc = new Scanner(System.in);

        // Sentencias con ? en lugar de concatenar los datos del usuario
        String sqlInsert = "INSERT INTO alumno (nombre, edad) VALUES (?, ?)";
        String sqlSelect = "SELECT * FROM alumno WHERE nombre = ?";

        try (
            Connection conexion = DriverManager.getConnection(url, usuario, password);
            PreparedStatement insertar = conexion.prepareStatement(sqlInsert);
            PreparedStatement buscar = conexion.prepareStatement(sqlSelect)
        ) {
            // a) Pedimos los datos y hacemos el INSERT
            System.out.println("Nombre del alumno:");
            String nombre = sc.nextLine();
            System.out.println("Edad del alumno:");
            int edad = Integer.parseInt(sc.nextLine());

            insertar.setString(1, nombre); // 1er ? -> nombre (String)
            insertar.setInt(2, edad);      // 2º ? -> edad (int)
            int filas = insertar.executeUpdate(); // INSERT -> executeUpdate
            System.out.println("Insertadas " + filas + " filas");

            // b) Pedimos un nombre y mostramos los alumnos que se llamen así
            System.out.println("Nombre a buscar:");
            String nombreBuscado = sc.nextLine();
            buscar.setString(1, nombreBuscado);
            ResultSet resultado = buscar.executeQuery(); // SELECT -> executeQuery
            while (resultado.next()) {
                int id = resultado.getInt("id");
                String n = resultado.getString("nombre");
                int e = resultado.getInt("edad");
                System.out.println(n + ", con id " + id + " tiene " + e + " años");
            }
        } catch (SQLException e) {
            System.out.println("Error al trabajar con la base de datos");
        }
    }
}
