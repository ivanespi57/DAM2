package tienda_orm;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity 
public class FichaTecnica {

    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @Column (nullable = false, precision = 8, scale = 2)
    private BigDecimal peso;

    @Column (nullable = false, length = 50)
    private String material;

    @Column (nullable = false, columnDefinition = "INT DEFAULT 0")
    private int garantia;

    @OneToOne 
    //Como queremos que la clave ajena esté en fichatecnica, hacemos los siguiente
    @JoinColumn (
        name = "id_producto",
        unique = true,
        nullable = false
    )
    private Producto producto;

    public FichaTecnica(){}

    public FichaTecnica(BigDecimal peso, String material, int garantia, Producto prod) {
        this.peso = peso;
        this.material = material;
        this.garantia = garantia;

        this.producto = prod;
        prod.setFichaTecnica(this);
    }

    
}