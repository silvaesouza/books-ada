package br.com.ada.silvaesouza.books.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LivroDTO {

    private Long id;
    private String nome;
    private CategoriaDTO categoria;
    private EditoraDTO editora;
    @NotEmpty()
    @Size(max = 13)
    private String isbn;

}
