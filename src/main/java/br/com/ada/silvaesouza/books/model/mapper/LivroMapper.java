package br.com.ada.silvaesouza.books.model.mapper;

import br.com.ada.silvaesouza.books.model.dto.LivroDTO;
import br.com.ada.silvaesouza.books.model.entity.Livro;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LivroMapper {
    List<LivroDTO> parseListDTO(List<Livro> Livros);
    List<Livro> parseListEntity(List<LivroDTO> Livros);
    LivroDTO parseDTO(Livro Livro);
    Livro parseEntity(LivroDTO Livro);

}
