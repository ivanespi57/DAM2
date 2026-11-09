package editorial;

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
@Table(name = "ficha_bibliografica")
public class FichaBibliografica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "isbn", nullable = false, unique = true)
    private String isbn;

    @Column(name = "numero_paginas", nullable = false)
    private int numeroPaginas;

    @Column(name = "idioma", nullable = false)
    private String idioma;

    // Lado PROPIETARIO: aquí está la clave foránea libro_id.
    // unique = true garantiza que un libro no tenga más de una ficha.
    // cascade PERSIST: al hacer persist de la ficha se guarda también el libro.
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "libro_id", nullable = false, unique = true)
    private Libro libro;

    public FichaBibliografica() {}

    public FichaBibliografica(String isbn, int numeroPaginas, String idioma) {
        this.isbn = isbn;
        this.numeroPaginas = numeroPaginas;
        this.idioma = idioma;
    }

    public Long getId() { return id; }
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public int getNumeroPaginas() { return numeroPaginas; }
    public void setNumeroPaginas(int numeroPaginas) { this.numeroPaginas = numeroPaginas; }
    public String getIdioma() { return idioma; }
    public void setIdioma(String idioma) { this.idioma = idioma; }
    public Libro getLibro() { return libro; }
    public void setLibro(Libro libro) { this.libro = libro; }
}