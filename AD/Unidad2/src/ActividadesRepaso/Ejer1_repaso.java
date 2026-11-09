import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Ejer1_repaso: Conexión a la base de datos "instituto".
 * Necesita el JAR mysql-connector-j en la carpeta lib del proyecto.
 */
public class Ejer1_repaso {
    public static void main(String[] args) {
        // URL: jdbc:mysql://IP_DE_LA_VM:puerto/nombre_base_de_datos
        String url = "jdbc:mysql://IP_VM:3306/instituto";
        String usuario = "root";
        String password = "";

        // try-with-resources: la conexión se cierra sola al terminar el bloque
        try (Connection conexion = DriverManager.getConnection(url, usuario, password)) {
            System.out.println("Conexión realizada correctamente");
        } catch (SQLException e) {
            // Si falla (IP mal, usuario incorrecto, MySQL apagado...) llegamos aquí
            System.out.println("Error al conectar con MySQL");
        }
    }
}
