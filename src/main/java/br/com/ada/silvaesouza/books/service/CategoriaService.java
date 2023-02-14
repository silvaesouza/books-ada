package br.com.ada.silvaesouza.books.service;

import br.com.ada.silvaesouza.books.model.dto.CategoriaDTO;
import br.com.ada.silvaesouza.books.model.dto.CategoriaLivrosDTO;

public interface CategoriaService extends BaseService<CategoriaDTO>{

    public CategoriaLivrosDTO buscarLivrosPorCategoria(Long id);

}
