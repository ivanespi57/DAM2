import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Ejer7_repaso: Procedimiento almacenado con parámetros IN.
 * Procedimiento en MySQL: aumentar_stock(IN p_id INT, IN p_cantidad INT)
 */
public class Ejer7_repaso {
    public static void main(String[] args) {
        String url = "jdbc:mysql://IP_VM:3306/tienda";
        String usuario = "root";
        String password = "";

        // Sintaxis JDBC para llamar a un procedimiento: {CALL nombre(?, ?)}
        String sql = "{CALL aumentar_stock(?, ?)}";

        try (
            Connection conexion = DriverManager.getConnection(url, usuario, password);
            // CallableStatement se crea con prepareCall
            CallableStatement sentencia = conexion.prepareCall(sql)
        ) {
            sentencia.setInt(1, 5);   // 1er ? (IN p_id)       -> producto con id 5
            sentencia.setInt(2, 10);  // 2º ? (IN p_cantidad)  -> sumar 10 unidades
            sentencia.execute();      // ejecuta el procedimiento
            System.out.println("Stock aumentado correctamente");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
