import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Ejer2_repaso: INSERT, UPDATE y DELETE con Statement sobre la tabla alumno
 * (id, nombre, edad), consultando con executeQuery después de cada operación.
 */
public class Ejer2_repaso {

    // Recorre el ResultSet accediendo a las columnas POR NÚMERO (empiezan en 1)
    static void mostrarPorNumero(Statement sentencia, String sql) throws SQLException {
        ResultSet resultado = sentencia.executeQuery(sql);
        // next() avanza a la siguiente fila y devuelve false cuando no quedan más
        while (resultado.next()) {
            int id = resultado.getInt(1);            // columna 1 = id
            String nombre = resultado.getString(2);  // columna 2 = nombre
            int edad = resultado.getInt(3);          // columna 3 = edad
            System.out.println(nombre + ", con id " + id + " tiene " + edad + " años");
        }
    }

    // Recorre el ResultSet accediendo a las columnas POR NOMBRE
    static void mostrarPorNombre(Statement sentencia, String sql) throws SQLException {
        ResultSet resultado = sentencia.executeQuery(sql);
        while (resultado.next()) {
            int id = resultado.getInt("id");
            String nombre = resultado.getString("nombre");
            int edad = resultado.getInt("edad");
            System.out.println(nombre + ", con id " + id + " tiene " + edad + " años");
        }
    }

    public static void main(String[] args) {
        String url = "jdbc:mysql://IP_VM:3306/instituto";
        String usuario = "root";
        String password = "";

        // Conexión y Statement dentro del try-with-resources (se cierran solos)
        try (
            Connection conexion = DriverManager.getConnection(url, usuario, password);
            Statement sentencia = conexion.createStatement()
        ) {
            String consulta = "SELECT * FROM alumno WHERE nombre = 'Ana' OR nombre = 'Luis'";

            // a) INSERT de Ana y Luis (executeUpdate para todo lo que no sea SELECT)
            sentencia.executeUpdate("INSERT INTO alumno (nombre, edad) VALUES ('Ana', 20)");
            sentencia.executeUpdate("INSERT INTO alumno (nombre, edad) VALUES ('Luis', 22)");
            System.out.println("--- Tras los INSERT ---");
            mostrarPorNumero(sentencia, consulta);

            // b) UPDATE: la edad de Ana pasa a 21
            sentencia.executeUpdate("UPDATE alumno SET edad = 21 WHERE nombre = 'Ana'");
            System.out.println("--- Tras el UPDATE ---");
            mostrarPorNombre(sentencia, consulta);

            // c) DELETE: eliminamos a Luis
            sentencia.executeUpdate("DELETE FROM alumno WHERE nombre = 'Luis'");
            System.out.println("--- Tras el DELETE ---");
            mostrarPorNumero(sentencia, consulta);

        } catch (SQLException e) {
            e.printStackTrace(); // Muestra el error de la base de datos
        }
    }
}
