package br.com.ada.silvaesouza.books.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CategoriaLivrosDTO extends CategoriaDTO {

    private List<LivroDTO> livros;
}
