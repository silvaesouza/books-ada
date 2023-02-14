package br.com.ada.silvaesouza.books.controller;

import br.com.ada.silvaesouza.books.model.dto.LivroDTO;
import br.com.ada.silvaesouza.books.service.LivroService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/livro")
@Slf4j
public class LivroController extends BaseController<LivroDTO, LivroService> {
    public LivroController(LivroService service) {
        super(service);
    }

    @GetMapping("nome/{nome}")
    public ResponseEntity<List<LivroDTO>> buscarPorNome(@PathVariable("nome") String nome) {
        try {
            return ResponseEntity.ok(service.buscarPorNome(nome));
        }catch(EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch(Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("isbn/{isbn}")
    public ResponseEntity<List<LivroDTO>> buscarPorIsbn(
            @PathVariable("isbn") String isbn)  {
        try {
            return ResponseEntity.ok(service.buscarPorIsbn(isbn));
        }catch(EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch(Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
