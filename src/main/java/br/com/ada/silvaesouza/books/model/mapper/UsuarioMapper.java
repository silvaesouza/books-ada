package br.com.ada.silvaesouza.books.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import br.com.ada.silvaesouza.books.model.dto.UsuarioLoginDTO;
import br.com.ada.silvaesouza.books.model.entity.Usuario;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
	List<UsuarioLoginDTO> parseListDTO(List<Usuario> clientes);
	List<Usuario> parseListEntity(List<UsuarioLoginDTO> clientes);
	@Mapping(target = "password", ignore=true)
	UsuarioLoginDTO parseDTO(Usuario cliente);
	@Mapping(target="authorities",ignore=true)
	Usuario parseEntity(UsuarioLoginDTO cliente);

}
