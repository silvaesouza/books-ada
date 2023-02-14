package br.com.ada.silvaesouza.books.service;

import br.com.ada.silvaesouza.books.model.dto.TokenDTO;
import br.com.ada.silvaesouza.books.model.dto.UsuarioLoginDTO;

public interface UsuarioService extends BaseService<UsuarioLoginDTO>{
	TokenDTO logar(UsuarioLoginDTO usuarioDTO);
}
