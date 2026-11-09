import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Ejer9_repaso: Función almacenada precio_con_iva(p_precio, p_iva).
 *
 * a) Sintaxis: {? = CALL precio_con_iva(?, ?)}
 *      1er ? -> valor devuelto por la función (se registra como OUT)
 *      2º ?  -> p_precio (IN)
 *      3er ? -> p_iva    (IN)
 *
 * b) Cuando la función va DENTRO de un SELECT no hace falta CallableStatement:
 *    no es una llamada JDBC específica a la función, sino que la función forma
 *    parte de la consulta SQL, así que vale PreparedStatement.
 */
public class Ejer9_repaso {
    public static void main(String[] args) {
        String url = "jdbc:mysql://IP_VM:3306/tienda";
        String usuario = "root";
        String password = "";

        try (Connection conexion = DriverManager.getConnection(url, usuario, password)) {

            // ---------- a) Llamada directa a la función ----------
            String sqlFunction = "{? = CALL precio_con_iva(?, ?)}";
            try (CallableStatement sentencia = conexion.prepareCall(sqlFunction)) {
                sentencia.registerOutParameter(1, java.sql.Types.DECIMAL); // valor devuelto
                sentencia.setDouble(2, 100); // p_precio
                sentencia.setDouble(3, 21);  // p_iva
                sentencia.execute();
                double resultado = sentencia.getDouble(1); // leemos lo que devuelve
                System.out.println("Precio con IVA: " + resultado);
            }

            // ---------- b) Función dentro de un SELECT ----------
            String sql = "SELECT nombre, precio_con_iva(precio, 21) FROM producto";
            try (PreparedStatement preparada = conexion.prepareStatement(sql)) {
                ResultSet resultado = preparada.executeQuery();
                while (resultado.next()) {
                    String nombre = resultado.getString(1); // columna 1: nombre
                    double precio = resultado.getDouble(2); // columna 2: precio con IVA
                    System.out.println(nombre + ": " + precio + " €");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
