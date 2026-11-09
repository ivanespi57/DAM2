package academia;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

/**
 * Ejer3_repaso: academia de formación.
 * Antes de ejecutar: crear la BD academia_formacion y tener la PU_ACADEMIA en persistence.xml.
 *
 * Colas:
 *  - cursosPendientes:      cursos creados que aún no tienen alumno
 *  - alumnosSinCurso:       alumnos creados que aún no tienen curso
 *  - alumnosSinExpediente:  alumnos creados que aún no tienen expediente
 *  - expedientesPendientes: expedientes creados que aún no tienen alumno
 */
public class Ejer3_repaso {

    static Queue<Curso> cursosPendientes = new LinkedList<>();
    static Queue<Alumno> alumnosSinCurso = new LinkedList<>();
    static Queue<Alumno> alumnosSinExpediente = new LinkedList<>();
    static Queue<Expediente> expedientesPendientes = new LinkedList<>();

    // Lista con todos los alumnos creados (son los que guardaremos; el cascade guarda el resto)
    static List<Alumno> alumnos = new ArrayList<>();

    // Crea un curso: si hay un alumno esperando curso se vincula; si no, queda en la cola
    static void crearCurso(String nombre, String codigo, int horas, BigDecimal precio) {
        Curso curso = new Curso(nombre, codigo, horas, precio);
        if (!alumnosSinCurso.isEmpty()) {
            Alumno alumno = alumnosSinCurso.poll(); // saca el primero de la cola
            alumno.setCurso(curso);
        } else {
            cursosPendientes.add(curso);
        }
    }

    // Crea un alumno: se vincula a un curso pendiente (si hay) y a un expediente pendiente (si hay)
    static void crearAlumno(String nombre, String apellidos, String dni, String email) {
        Alumno alumno = new Alumno(nombre, apellidos, dni, email);
        alumnos.add(alumno);

        // Curso
        if (!cursosPendientes.isEmpty()) {
            alumno.setCurso(cursosPendientes.poll());
        } else {
            alumnosSinCurso.add(alumno);
        }

        // Expediente: hay que enlazar los DOS lados de la relación
        if (!expedientesPendientes.isEmpty()) {
            Expediente expediente = expedientesPendientes.poll();
            expediente.setAlumno(alumno);      // lado propietario (clave foránea)
            alumno.setExpediente(expediente);  // lado inverso
        } else {
            alumnosSinExpediente.add(alumno);
        }
    }

    // Crea un expediente: se vincula al primer alumno sin expediente; si no hay, a la cola
    static void crearExpediente(String numero, LocalDate fechaAlta) {
        Expediente expediente = new Expediente(numero, fechaAlta);
        if (!alumnosSinExpediente.isEmpty()) {
            Alumno alumno = alumnosSinExpediente.poll();
            expediente.setAlumno(alumno);
            alumno.setExpediente(expediente);
        } else {
            expedientesPendientes.add(expediente);
        }
    }

    // Guarda todos los alumnos (y por cascade su curso y su expediente)
    static void guardarTodo(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            // Comprobaciones que la base de datos no puede hacer por sí sola:
            // un expediente sin alumno o un alumno sin expediente provocan rollback
            if (!expedientesPendientes.isEmpty()) {
                throw new IllegalStateException("Hay expedientes sin alumno asociado");
            }
            for (Alumno a : alumnos) {
                if (a.getExpediente() == null) {
                    throw new IllegalStateException("El alumno " + a.getDni() + " no tiene expediente");
                }
                // Un alumno sin curso falla solo: curso_id es nullable = false
                em.persist(a); // el cascade PERSIST guarda también curso y expediente
            }

            tx.commit();
            System.out.println("Datos guardados correctamente.");
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback(); // se deshace todo
            }
            System.err.println("Rollback realizado. Motivo: " + e.getMessage());
        } finally {
            em.close(); // tras un error se descarta este EntityManager
        }
    }

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_ACADEMIA");

        // Ejemplo correcto: se mezcla el orden de creación a propósito
        crearCurso("Programación Java", "JAVA-01", 120, new BigDecimal("450.00")); // curso a la cola
        crearAlumno("Ana", "López Gil", "11111111A", "ana@correo.com");           // coge el curso de la cola
        crearExpediente("EXP-0001", LocalDate.now());                              // coge a Ana
        guardarTodo(emf);

        // Prueba de ROLLBACK: alumno sin curso ni expediente (descomenta para probar)
        // alumnos.clear();
        // crearAlumno("Luis", "Mora Sanz", "22222222B", "luis@correo.com");
        // guardarTodo(emf);

        emf.close();
    }
}