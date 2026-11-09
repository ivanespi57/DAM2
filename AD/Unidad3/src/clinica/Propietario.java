package clinica;

import jakarta.persistence.*;

@Entity                       // Esta clase es una entidad persistente
@Table(name = "propietario")  // Nombre de la tabla en MySQL
public class Propietario {

    @Id                                                  // Clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // AUTO_INCREMENT de MySQL
    private int id;

    @Column(name = "nombre", nullable = false, length = 60)     // Obligatorio, máx. 60
    private String nombre;

    @Column(name = "apellidos", nullable = false, length = 100) // Obligatorio, máx. 100
    private String apellidos;

    @Column(name = "dni", nullable = false, unique = true, length = 9) // Obligatorio, único, máx. 9
    private String dni;

    @Column(name = "telefono", length = 15)                     // Opcional, máx. 15
    private String telefono;

    @Column(name = "email", nullable = false, length = 100)     // Obligatorio, máx. 100
    private String email;

    // JPA necesita un constructor sin argumentos
    public Propietario() {}

    public Propietario(String nombre, String apellidos, String dni, String telefono, String email) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.telefono = telefono;
        this.email = email;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}