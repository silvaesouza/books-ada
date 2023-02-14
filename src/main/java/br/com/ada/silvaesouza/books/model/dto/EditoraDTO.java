package br.com.ada.silvaesouza.books.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EditoraDTO {

    private Long id;
    private String descricao;
    private String nome;

}
