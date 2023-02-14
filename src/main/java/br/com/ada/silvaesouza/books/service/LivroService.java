package br.com.ada.silvaesouza.books.service;

import br.com.ada.silvaesouza.books.model.dto.LivroDTO;

import java.util.List;

public interface LivroService extends BaseService<LivroDTO>{

    public List<LivroDTO> buscarPorNome(String nome);

    public List<LivroDTO> buscarPorIsbn(String nome);
}
