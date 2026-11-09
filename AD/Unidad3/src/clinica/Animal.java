package clinica;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "animal")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "especie", nullable = false, length = 30)
    private String especie;

    @Column(name = "raza", length = 50)
    private String raza;

    // El atributo Java es fechaNacimiento, pero la columna se llama fecha_nacimiento
    // nullable = true es el valor por defecto: puede ser nulo
    @Column(name = "fecha_nacimiento", nullable = true)
    private LocalDate fechaNacimiento;

    // precision = nº total de dígitos, scale = nº de decimales
    @Column(name = "peso", precision = 6, scale = 2)
    private BigDecimal peso;

    public Animal() {}

    public Animal(String nombre, String especie, String raza, LocalDate fechaNacimiento, BigDecimal peso) {
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
        this.fechaNacimiento = fechaNacimiento;
        this.peso = peso;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getEspecie() { return especie; }
    public void setEspecie(String especie) { this.especie = especie; }
    public String getRaza() { return raza; }
    public void setRaza(String raza) { this.raza = raza; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
    public BigDecimal getPeso() { return peso; }
    public void setPeso(BigDecimal peso) { this.peso = peso; }
}