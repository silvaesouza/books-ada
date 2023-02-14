package br.com.ada.silvaesouza.books.model.mapper;

import br.com.ada.silvaesouza.books.model.dto.EditoraDTO;
import br.com.ada.silvaesouza.books.model.entity.Editora;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EditoraMapper 
{
    List<EditoraDTO> parseListDTO(List<Editora> Editoras);
    List<Editora> parseListEntity(List<EditoraDTO> Editoras);
    EditoraDTO parseDTO(Editora Editora);
    Editora parseEntity(EditoraDTO Editora);

}
