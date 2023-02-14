package br.com.ada.silvaesouza.books.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioDTO{
	
		private Long id;
		private String nome;
		private String email;

}
