package br.com.ada.silvaesouza.books.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenDTO {

	private UsuarioDTO user;
	private String type;
	private String token;

}
