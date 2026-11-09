import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Ejer5_repaso: Inyección SQL.
 *
 * a) Versión VULNERABLE (Statement + concatenación).
 *    Texto que podría introducir un atacante para sacar datos de la tabla profesor:
 *        ' UNION SELECT dni, nombre, email FROM profesor #
 *    La consulta queda: SELECT * FROM alumno WHERE nombre='' UNION SELECT dni, nombre, email FROM profesor #'
 *    (el # comenta el resto de la consulta).
 *
 * b) Versión SEGURA (PreparedStatement).
 *
 * c) Explicación: con Statement el texto del usuario pasa a formar parte del código SQL,
 *    así que puede cambiar el significado de la consulta. Con PreparedStatement, el valor
 *    que damos con setString() se trata como un DATO, no como parte de la sentencia, así
 *    que aunque lleve caracteres especiales (' UNION ... #) se interpreta como un simple
 *    texto a buscar y no como una nueva parte de la consulta.
 */
public class Ejer5_repaso {
    public static void main(String[] args) {
        String url = "jdbc:mysql://IP_VM:3306/instituto";
        String usuario = "root";
        String password = "";
        Scanner sc = new Scanner(System.in);

        try (Connection conexion = DriverManager.getConnection(url, usuario, password)) {
            System.out.println("Dime el nombre del alumno a buscar");
            String nombre = sc.nextLine();

            // a) VULNERABLE: concatenamos lo que escribe el usuario
            System.out.println("--- Versión con Statement (vulnerable) ---");
            String sql = "SELECT * FROM alumno WHERE nombre='" + nombre + "'";
            try (Statement sentencia = conexion.createStatement()) {
                ResultSet resultado = sentencia.executeQuery(sql);
                while (resultado.next()) {
                    // Con la inyección saldrían dni, nombre y email de profesores
                    System.out.println(resultado.getString(1) + " | "
                            + resultado.getString(2) + " | " + resultado.getString(3));
                }
            }

            // b) SEGURA: el ? se rellena con setString y se trata como dato
            System.out.println("--- Versión con PreparedStatement (segura) ---");
            String sqlSegura = "SELECT * FROM alumno WHERE nombre = ?";
            try (PreparedStatement preparada = conexion.prepareStatement(sqlSegura)) {
                preparada.setString(1, nombre);
                ResultSet resultado = preparada.executeQuery();
                while (resultado.next()) {
                    System.out.println(resultado.getString(1) + " | "
                            + resultado.getString(2) + " | " + resultado.getString(3));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
