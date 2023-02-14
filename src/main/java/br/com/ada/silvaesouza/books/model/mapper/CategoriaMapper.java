package br.com.ada.silvaesouza.books.model.mapper;

import br.com.ada.silvaesouza.books.model.dto.CategoriaDTO;
import br.com.ada.silvaesouza.books.model.entity.Categoria;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {
    List<CategoriaDTO> parseListDTO(List<Categoria> Categorias);
    List<Categoria> parseListEntity(List<CategoriaDTO> Categorias);
    CategoriaDTO parseDTO(Categoria Categoria);
    Categoria parseEntity(CategoriaDTO Categoria);
}
