

import java.math.BigDecimal;
import java.time.LocalDate;

import clinica.Animal;
import clinica.Consulta;
import clinica.Propietario;
import clinica.Veterinario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

/**
 * Ejer1_repaso: prueba de persistencia de la clínica veterinaria.
 * Antes de ejecutar: crear la BD clinica_veterinaria en phpMyAdmin y tener
 * persistence.xml en src/META-INF con la PU_CLINICA_VETERINARIA.
 * Hibernate creará las tablas solo (hbm2ddl.auto = update).
 */
public class Ejer1_repaso {
    public static void main(String[] args) {

        // La fábrica lee persistence.xml y crea la infraestructura de la unidad indicada
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("PU_CLINICA_VETERINARIA");
        // El EntityManager es quien realiza las operaciones con las entidades
        EntityManager em = emf.createEntityManager();
        // La transacción: las operaciones que modifican datos van dentro de una
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin(); // Inicio de la transacción

            // Creamos un objeto de cada entidad (estado Transient: Hibernate aún no los gestiona)
            Propietario propietario = new Propietario(
                    "Laura", "García Pérez", "12345678A", "600123456", "laura@correo.com");
            Animal animal = new Animal(
                    "Toby", "Perro", "Labrador", LocalDate.of(2020, 5, 14), new BigDecimal("28.50"));
            Veterinario veterinario = new Veterinario(
                    "Carlos", "Ruiz Sanz", "COL-0001", "Cirugía", new BigDecimal("2100.00"));
            Consulta consulta = new Consulta(
                    LocalDate.now(), "Revisión anual", "Todo correcto", new BigDecimal("45.00"));

            // persist() los incorpora al contexto de persistencia (estado Managed)
            em.persist(propietario);
            em.persist(animal);
            em.persist(veterinario);
            em.persist(consulta);

            tx.commit(); // Confirma: Hibernate ejecuta los INSERT
            System.out.println("Datos guardados correctamente.");

        } catch (Exception e) {
            // Si algo falla y la transacción sigue activa, deshacemos los cambios
            if (tx.isActive()) {
                tx.rollback();
            }
            System.err.println("Error al guardar los datos: " + e.getMessage());
        } finally {
            // Cerramos el EntityManager y la fábrica al terminar
            em.close();
            emf.close();
        }
    }
}