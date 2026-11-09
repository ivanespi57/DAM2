package academia;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "alumno")
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre", nullable = false, length = 60)
    private String nombre;

    @Column(name = "apellidos", nullable = false, length = 100)
    private String apellidos;

    @Column(name = "dni", nullable = false, unique = true, length = 9)
    private String dni;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    // Relación con Expediente (bidireccional): Alumno es el lado NO propietario.
    // mappedBy = "alumno": la clave foránea está en el atributo "alumno" de Expediente.
    // cascade PERSIST: al guardar el alumno se guarda automáticamente su expediente.
    @OneToOne(mappedBy = "alumno", cascade = CascadeType.PERSIST)
    private Expediente expediente;

    // Relación con Curso (unidireccional): Alumno es el lado PROPIETARIO.
    // La tabla alumno tendrá curso_id, no nulo y único (un curso -> un solo alumno).
    // nullable = false hace que insertar un alumno sin curso falle y se haga rollback.
    // cascade PERSIST: al guardar el alumno se guarda automáticamente su curso.
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "curso_id", nullable = false, unique = true)
    private Curso curso;

    public Alumno() {}

    public Alumno(String nombre, String apellidos, String dni, String email) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.email = email;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Expediente getExpediente() { return expediente; }
    public void setExpediente(Expediente expediente) { this.expediente = expediente; }
    public Curso getCurso() { return curso; }
    public void setCurso(Curso curso) { this.curso = curso; }
}