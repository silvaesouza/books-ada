package br.com.ada.silvaesouza.books.service;

import java.util.List;

public interface BaseService<T> {
	
	List<T> buscarTodos();
	T buscarPorId(Long id);
	T criar(T clienteDTO);
	T editar(Long id, T clienteDTO);
	void excluir(Long id);
}
