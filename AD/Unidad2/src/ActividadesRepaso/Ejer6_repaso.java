import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Ejer6_repaso: Transferencia de 500 € entre dos cuentas (tabla cuenta: id, saldo)
 * usando una transacción: o se hacen las dos operaciones o no se hace ninguna.
 */
public class Ejer6_repaso {
    public static void main(String[] args) {
        String url = "jdbc:mysql://IP_VM:3306/banco";
        String usuario = "root";
        String password = "1234";
        Scanner sc = new Scanner(System.in);

        System.out.println("Id de la cuenta origen:");
        int idOrigen = Integer.parseInt(sc.nextLine());
        System.out.println("Id de la cuenta destino:");
        int idDestino = Integer.parseInt(sc.nextLine());

        try (Connection conexion = DriverManager.getConnection(url, usuario, password)) {

            // Desactivamos el autocommit: los cambios no se confirman solos
            conexion.setAutoCommit(false);

            try {
                String sqlRestar = "UPDATE cuenta SET saldo = saldo - 500 WHERE id = ?";
                String sqlSumar = "UPDATE cuenta SET saldo = saldo + 500 WHERE id = ?";

                try (
                    PreparedStatement restar = conexion.prepareStatement(sqlRestar);
                    PreparedStatement sumar = conexion.prepareStatement(sqlSumar)
                ) {
                    restar.setInt(1, idOrigen);   // solo hay un ? -> posición 1
                    restar.executeUpdate();       // quita 500 € a la cuenta origen

                    sumar.setInt(1, idDestino);
                    sumar.executeUpdate();        // añade 500 € a la cuenta destino
                }

                // Si llegamos aquí, todo ha ido bien -> confirmamos los cambios
                conexion.commit();
                System.out.println("Transferencia realizada correctamente.");

            } catch (SQLException e) {
                // Si algo falla, deshacemos todo lo hecho desde el último commit
                conexion.rollback();
                System.out.println("Error en la transferencia. Cambios deshechos.");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Error de conexión o del propio rollback
        }
    }
}
