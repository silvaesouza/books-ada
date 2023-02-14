package br.com.ada.silvaesouza.books.service;

import br.com.ada.silvaesouza.books.model.dto.CategoriaLivrosDTO;
import br.com.ada.silvaesouza.books.model.dto.EditoraDTO;

public interface EditoraService extends BaseService<EditoraDTO> {

    public CategoriaLivrosDTO buscarLivrosPorEditora(Long id);
}
