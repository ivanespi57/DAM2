package academia;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "expediente")
public class Expediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "numero_expediente", nullable = false, unique = true, length = 20)
    private String numeroExpediente;

    @Column(name = "fecha_alta", nullable = false)
    private LocalDate fechaAlta;

    // columnDefinition permite poner un valor por defecto en la columna (DEFAULT 0).
    // Además lo inicializamos en Java para que el INSERT no envíe null.
    @Column(name = "nota_media", columnDefinition = "DECIMAL(4,2) DEFAULT 0")
    private BigDecimal notaMedia = BigDecimal.ZERO;

    // Expediente es el lado PROPIETARIO: la tabla expediente tiene alumno_id,
    // no nulo y único (cada alumno tiene un único expediente).
    // No lleva cascade: se guarda a través del alumno.
    @OneToOne
    @JoinColumn(name = "alumno_id", nullable = false, unique = true)
    private Alumno alumno;

    public Expediente() {}

    public Expediente(String numeroExpediente, LocalDate fechaAlta) {
        this.numeroExpediente = numeroExpediente;
        this.fechaAlta = fechaAlta;
    }

    public int getId() { return id; }
    public String getNumeroExpediente() { return numeroExpediente; }
    public void setNumeroExpediente(String numeroExpediente) { this.numeroExpediente = numeroExpediente; }
    public LocalDate getFechaAlta() { return fechaAlta; }
    public void setFechaAlta(LocalDate fechaAlta) { this.fechaAlta = fechaAlta; }
    public BigDecimal getNotaMedia() { return notaMedia; }
    public void setNotaMedia(BigDecimal notaMedia) { this.notaMedia = notaMedia; }
    public Alumno getAlumno() { return alumno; }
    public void setAlumno(Alumno alumno) { this.alumno = alumno; }
}