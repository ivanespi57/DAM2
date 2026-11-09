package editorial;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

/**
 * Ejer2_repaso: libros y fichas bibliográficas (@OneToOne bidireccional).
 * Antes de ejecutar: crear la BD "editorial" (el enunciado no da nombre, he elegido ese)
 * y tener la PU_EDITORIAL en persistence.xml.
 */
public class Ejer2_repaso {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_EDITORIAL");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            // Objetos en memoria (Transient)
            Libro libro = new Libro("El nombre del viento", "Patrick Rothfuss", "Plaza & Janés");
            FichaBibliografica ficha = new FichaBibliografica("1234567890", 880, "Español");

            // IMPORTANTE: hay que enlazar los DOS lados de la relación en memoria.
            // El lado propietario (ficha.libro) es el que Hibernate usa para la clave foránea.
            libro.setFichaBibliografica(ficha);
            ficha.setLibro(libro);

            // Basta con hacer persist de uno: el cascade PERSIST guarda el otro.
            // (Se podría hacer em.persist(ficha) y funcionaría igual.)
            em.persist(libro);

            tx.commit();
            System.out.println("Libro y ficha bibliográfica guardados correctamente.");

        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            System.err.println("Error al guardar: " + e.getMessage());
        } finally {
            em.close();
            emf.close();
        }
    }
}