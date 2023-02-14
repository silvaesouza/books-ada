package br.com.ada.silvaesouza.books.service.impl;

import br.com.ada.silvaesouza.books.model.dto.LivroDTO;
import br.com.ada.silvaesouza.books.model.entity.Livro;
import br.com.ada.silvaesouza.books.model.mapper.LivroMapper;
import br.com.ada.silvaesouza.books.repository.LivroFilterRepository;
import br.com.ada.silvaesouza.books.repository.LivroRepository;
import br.com.ada.silvaesouza.books.service.LivroService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroServiceImpl implements LivroService {

    @Autowired
    private LivroRepository repository;

    @Autowired
    private LivroFilterRepository livroFilterRepository;

    @Autowired
    private LivroMapper mapper;

    @Override
    public List<LivroDTO> buscarTodos() {
        return mapper.parseListDTO(repository.findAll());
    }

    @Override
    public LivroDTO buscarPorId(Long id) {
        Optional<Livro> livroOp = repository.findById(id);
        if(livroOp.isPresent()) {
            Livro livro = livroOp.get();
            return mapper.parseDTO(livro);
        }

        throw new EntityNotFoundException();
    }

    @Override
    public LivroDTO criar(LivroDTO LivroDTO) {
        Livro livro = mapper.parseEntity(LivroDTO);

        livro.setNome(LivroDTO.getNome());

        livro.setId(null);
        repository.save(livro);
        return mapper.parseDTO(livro);
    }

    @Override
    public LivroDTO editar(Long id, LivroDTO LivroDTO) {
        if(repository.existsById(id)) {
            Livro livro = mapper.parseEntity(LivroDTO);
            livro.setId(id);
            livro = repository.save(livro);
            return mapper.parseDTO(livro);
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

    @Override
    public List<LivroDTO> buscarPorNome(String nome) {
        List<Livro> produtos = livroFilterRepository.buscarPorNome(nome);

        return mapper.parseListDTO(produtos);
    }

    @Override
    public List<LivroDTO> buscarPorIsbn(String isbn) {
        List<Livro> produtos = livroFilterRepository.buscarPorIsbn(isbn);

        return mapper.parseListDTO(produtos);
    }
}
