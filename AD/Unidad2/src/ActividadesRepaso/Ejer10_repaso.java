import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Ejer10_repaso: Retirada de efectivo con un parámetro INOUT.
 *
 * PASO 1: introducir el procedimiento en la base de datos "banco" (por ejemplo desde
 * la pestaña SQL de phpMyAdmin):
 *
 *   DROP PROCEDURE IF EXISTS retirar_dinero;
 *   DELIMITER //
 *   CREATE PROCEDURE retirar_dinero(
 *       IN p_numero_cuenta VARCHAR(20),
 *       INOUT p_cantidad DECIMAL(12,2)
 *   )
 *   BEGIN
 *       UPDATE cuenta SET saldo = saldo - p_cantidad WHERE numero = p_numero_cuenta;
 *       SELECT saldo INTO p_cantidad FROM cuenta WHERE numero = p_numero_cuenta;
 *   END //
 *   DELIMITER ;
 *
 * NOTA: la columna que indica si la cuenta está activa la he llamado "activa".
 * Si en tu banco.sql se llama distinto, cambia el nombre en la consulta de abajo.
 */
public class Ejer10_repaso {
    public static void main(String[] args) {
        String url = "jdbc:mysql://IP_VM:3306/banco";
        String usuario = "root";
        String password = "1234";
        Scanner sc = new Scanner(System.in);

        try (Connection conexion = DriverManager.getConnection(url, usuario, password)) {

            // Pedimos el número de cuenta y la cantidad a retirar
            System.out.println("Número de cuenta:");
            String numero = sc.nextLine();
            System.out.println("Cantidad a retirar:");
            double cantidad = Double.parseDouble(sc.nextLine());

            // Consultamos el saldo y si está activa con un PreparedStatement
            String sqlConsulta = "SELECT saldo, activa FROM cuenta WHERE numero = ?";
            double saldo;
            try (PreparedStatement consulta = conexion.prepareStatement(sqlConsulta)) {
                consulta.setString(1, numero);
                ResultSet resultado = consulta.executeQuery();

                // a) ¿Existe la cuenta? Si next() es false, no hay ninguna fila
                if (!resultado.next()) {
                    System.out.println("La cuenta no existe.");
                    return; // termina la ejecución
                }

                // b) ¿Está activa? Si no, no se puede retirar y termina
                boolean activa = resultado.getBoolean("activa");
                if (!activa) {
                    System.out.println("La cuenta está inactiva. No se pueden realizar "
                            + "operaciones de retirada sobre cuentas inactivas.");
                    return; // termina la ejecución
                }

                saldo = resultado.getDouble("saldo"); // guardamos el saldo actual
            }

            // c) y d) Repetimos hasta que la cantidad sea positiva y no supere el saldo
            while (cantidad <= 0 || cantidad > saldo) {
                if (cantidad <= 0) {
                    System.out.println("La cantidad debe ser positiva.");
                } else {
                    System.out.println("Saldo insuficiente. Saldo actual: " + saldo);
                }
                System.out.println("Introduce una nueva cantidad:");
                cantidad = Double.parseDouble(sc.nextLine());
            }

            // e) Llamamos al procedimiento con CallableStatement
            String sqlRetirar = "{CALL retirar_dinero(?, ?)}";
            try (CallableStatement retirar = conexion.prepareCall(sqlRetirar)) {
                retirar.setString(1, numero);   // parámetro IN: número de cuenta
                retirar.setDouble(2, cantidad); // parámetro INOUT, como ENTRADA: cantidad a retirar
                // parámetro INOUT, como SALIDA: lo registramos indicando su tipo SQL
                retirar.registerOutParameter(2, java.sql.Types.DECIMAL);
                retirar.execute();

                // Después de ejecutar, el mismo parámetro contiene el saldo tras la operación
                double saldoFinal = retirar.getDouble(2);
                System.out.println("Retirada realizada. Saldo tras la operación: " + saldoFinal + " €");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Debes introducir un número válido.");
        }
    }
}
