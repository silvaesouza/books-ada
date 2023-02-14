package br.com.ada.silvaesouza.books.service.impl;

import br.com.ada.silvaesouza.books.model.dto.CategoriaLivrosDTO;
import br.com.ada.silvaesouza.books.model.dto.EditoraDTO;
import br.com.ada.silvaesouza.books.model.entity.Editora;
import br.com.ada.silvaesouza.books.model.entity.Livro;
import br.com.ada.silvaesouza.books.model.mapper.EditoraMapper;
import br.com.ada.silvaesouza.books.model.mapper.LivroMapper;
import br.com.ada.silvaesouza.books.repository.EditoraRepository;
import br.com.ada.silvaesouza.books.repository.LivroFilterRepository;
import br.com.ada.silvaesouza.books.service.EditoraService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EditoraServiceImpl implements EditoraService {

    @Autowired
    private EditoraRepository repository;

    @Autowired
    private LivroFilterRepository livroFilterRepository;

    @Autowired
    private EditoraMapper mapper;

    @Autowired
    private LivroMapper livroMapper;

    @Override
    public List<EditoraDTO> buscarTodos() {
        return mapper.parseListDTO(repository.findAll());
    }

    @Override
    public EditoraDTO buscarPorId(Long id) {
        Optional<Editora> EditoraOp = repository.findById(id);
        if(EditoraOp.isPresent()) {
            Editora Editora = EditoraOp.get();
            return mapper.parseDTO(Editora);
        }

        throw new EntityNotFoundException();
    }

    @Override
    public CategoriaLivrosDTO buscarLivrosPorEditora(Long id){
        EditoraDTO editoraDTO = buscarPorId(id);
        List<Livro> livros = livroFilterRepository.filtrar(id);
        CategoriaLivrosDTO categoriaLivrosDTO = new CategoriaLivrosDTO();
        categoriaLivrosDTO.setId(editoraDTO.getId());
        categoriaLivrosDTO.setNome(editoraDTO.getNome());
        categoriaLivrosDTO.setLivros(livroMapper.parseListDTO(livros));
        return categoriaLivrosDTO;
    }

    @Override
    public EditoraDTO criar(EditoraDTO EditoraDTO) {
        Editora Editora = mapper.parseEntity(EditoraDTO);

        Editora.setNome(EditoraDTO.getNome());

        Editora.setId(null);
        repository.save(Editora);
        return mapper.parseDTO(Editora);
    }

    @Override
    public EditoraDTO editar(Long id, EditoraDTO EditoraDTO) {
        if(repository.existsById(id)) {
            Editora Editora = mapper.parseEntity(EditoraDTO);
            Editora.setId(id);
            Editora = repository.save(Editora);
            return mapper.parseDTO(Editora);
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
