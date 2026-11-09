import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Ejer8_repaso: Parámetros OUT e INOUT.
 *
 * a) obtener_stock(IN p_id INT, OUT p_stock INT)
 *    Antes de ejecutar hay que hacer DOS cosas:
 *      1) dar valor al parámetro IN (setInt)
 *      2) registrar el parámetro OUT indicando su tipo SQL (registerOutParameter)
 *    El valor de salida se lee DESPUÉS de execute() con getInt().
 *
 * b) incrementar(INOUT p_valor INT)
 *    Diferencia con OUT: el mismo ? se usa de entrada Y de salida, así que
 *    se hace setInt(1, valor) Y registerOutParameter(1, tipo) sobre la misma posición.
 */
public class Ejer8_repaso {
    public static void main(String[] args) {
        String url = "jdbc:mysql://IP_VM:3306/tienda";
        String usuario = "root";
        String password = "";

        try (Connection conexion = DriverManager.getConnection(url, usuario, password)) {

            // ---------- a) Parámetro OUT ----------
            String sqlStock = "{CALL obtener_stock(?, ?)}";
            try (CallableStatement stock = conexion.prepareCall(sqlStock)) {
                stock.setInt(1, 5); // IN: id del producto
                // OUT: registramos el 2º ? indicando el tipo SQL que esperamos
                stock.registerOutParameter(2, java.sql.Types.INTEGER);
                stock.execute();
                int valorStock = stock.getInt(2); // leemos el OUT con getInt (tras execute)
                System.out.println("Stock: " + valorStock);
            }

            // ---------- b) Parámetro INOUT ----------
            String sqlIncrementar = "{CALL incrementar(?)}";
            try (CallableStatement incrementar = conexion.prepareCall(sqlIncrementar)) {
                incrementar.setInt(1, 50); // como ENTRADA: el valor inicial es 50
                // como SALIDA: registramos ese mismo parámetro
                incrementar.registerOutParameter(1, java.sql.Types.INTEGER);
                incrementar.execute();
                int valor = incrementar.getInt(1); // valor modificado (60)
                System.out.println("Valor: " + valor);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
