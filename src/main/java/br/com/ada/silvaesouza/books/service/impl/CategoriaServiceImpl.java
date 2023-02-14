package br.com.ada.silvaesouza.books.service.impl;

import br.com.ada.silvaesouza.books.model.dto.CategoriaDTO;
import br.com.ada.silvaesouza.books.model.dto.CategoriaLivrosDTO;
import br.com.ada.silvaesouza.books.model.entity.Categoria;
import br.com.ada.silvaesouza.books.model.entity.Livro;
import br.com.ada.silvaesouza.books.model.mapper.CategoriaMapper;
import br.com.ada.silvaesouza.books.model.mapper.LivroMapper;
import br.com.ada.silvaesouza.books.repository.CategoriaRepository;
import br.com.ada.silvaesouza.books.repository.LivroFilterRepository;
import br.com.ada.silvaesouza.books.service.CategoriaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    @Autowired
    private LivroFilterRepository livroFilterRepository;

    @Autowired
    private CategoriaMapper mapper;

    @Autowired
    private LivroMapper livroMapper;
    
    @Override
    public List<CategoriaDTO> buscarTodos() {
        return mapper.parseListDTO(repository.findAll());
    }

    @Override
    public CategoriaDTO buscarPorId(Long id) {
        Optional<Categoria> categoriaOp = repository.findById(id);
        if(categoriaOp.isPresent()) {
            Categoria categoria = categoriaOp.get();
            return mapper.parseDTO(categoria);
        }

        throw new EntityNotFoundException();
    }

    @Override
    public CategoriaLivrosDTO buscarLivrosPorCategoria(Long id){
        CategoriaDTO categoriaDTO = buscarPorId(id);
        List<Livro> livros = livroFilterRepository.filtrar(id);
        CategoriaLivrosDTO categoriaLivrosDTO = new CategoriaLivrosDTO();
        categoriaLivrosDTO.setId(categoriaDTO.getId());
        categoriaLivrosDTO.setNome(categoriaDTO.getNome());
        categoriaLivrosDTO.setLivros(livroMapper.parseListDTO(livros));
        return categoriaLivrosDTO;
    }

    @Override
    public CategoriaDTO criar(CategoriaDTO categoriaDTO) {
        Categoria categoria = mapper.parseEntity(categoriaDTO);

        categoria.setNome(categoriaDTO.getNome());

        categoria.setId(null);
        repository.save(categoria);
        return mapper.parseDTO(categoria);
    }

    @Override
    public CategoriaDTO editar(Long id, CategoriaDTO categoriaDTO) {
        if(repository.existsById(id)) {
            Categoria categoria = mapper.parseEntity(categoriaDTO);
            categoria.setId(id);
            categoria = repository.save(categoria);
            return mapper.parseDTO(categoria);
        }

        throw new EntityNotFoundException();
    }

    @Override
    public void excluir(Long id) {
        if(!repository.existsById(id)) {
            throw new EntityNotFoundException();
        }

        repository.deleteById(id);
    }
}
