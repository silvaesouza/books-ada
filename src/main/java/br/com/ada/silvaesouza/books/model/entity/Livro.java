package br.com.ada.silvaesouza.books.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="livros")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Livro {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="nome", nullable=false, unique=true)
    private String nome ;

    @ManyToOne()
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    @ManyToOne()
    @JoinColumn(name = "editora_id", nullable = false)
    private Editora editora;

    @Column(name="isbn", nullable=false, unique=true, length = 13)
    private String isbn;

}
