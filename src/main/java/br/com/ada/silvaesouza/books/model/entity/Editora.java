package br.com.ada.silvaesouza.books.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Editora")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Editora {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="descricao", nullable=false, unique=true)
    private String descricao ;

    @Column(name="nome", nullable=true, unique=true, length = 255)
    private String nome;

}
