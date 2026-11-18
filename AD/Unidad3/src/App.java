import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.*;
import tienda_orm.FichaTecnica;
import tienda_orm.Producto;;

public class App {
    public static void main(String[] args) throws Exception {
        
        //Factoría
        EntityManagerFactory emf  = Persistence.createEntityManagerFactory("TIENDA_ORM_PU");
        EntityManager em = emf.createEntityManager();
        //EntityTransaction tx = em.getTransaction();

        Producto p = em.createQuery("SQL", Producto.class).getSingleResult();

        String sql = "SELECT p FROM Producto p WHERE p.precio = :precio";
        List<Producto> listaP = em.createNativeQuery(sql, Producto.class)
        .setParameter("precio", 25.49)
        .getResultList();

        String sql2 = "SELECT * FROM producto WHERE precio = precio";
        List<Producto> lista = em.createNativeQuery(sql2, Producto.class)
        .setParameter("precio", 25.49)
        .getResultList();

        em.close();
        
        try {
            tx.begin();

            //Producto p1 = new Producto("Teclado", new BigDecimal(29.95), 15);
            //Producto p1 = em.find(Producto.class,1);
            //FichaTecnica f1 = new FichaTecnica(new BigDecimal(0.47), "Chapa", 2, p1);

            //p1.setNombre("Teclado logitech");
            //em.persist(p1); // Añade el contexto de persistencia
            //em.persist(f1);
            
            tx.commit();
            System.out.println("Se ha insertado correctamente");
        }catch(Exception e){
            if(tx.isActive()){
                tx.rollback();
            }
            e.printStackTrace();
        }

    }
}
