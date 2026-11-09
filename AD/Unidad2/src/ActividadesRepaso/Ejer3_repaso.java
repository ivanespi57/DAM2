import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Ejer3_repaso: Filas afectadas.
 * Se usa executeUpdate(String sql), que devuelve un int con el número de filas afectadas.
 */
public class Ejer3_repaso {
    public static void main(String[] args) {
        String url = "jdbc:mysql://IP_VM:3306/instituto";
        String usuario = "root";
        String password = "";

        try (
            Connection conexion = DriverManager.getConnection(url, usuario, password);
            Statement sentencia = conexion.createStatement()
        ) {
            // executeUpdate devuelve un int: nº de filas modificadas
            int filas = sentencia.executeUpdate("UPDATE alumno SET edad = edad + 5");
            System.out.println("Se han modificado " + filas + " filas");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
