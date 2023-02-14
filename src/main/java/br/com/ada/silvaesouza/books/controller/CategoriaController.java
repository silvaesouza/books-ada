package br.com.ada.silvaesouza.books.controller;

import br.com.ada.silvaesouza.books.model.dto.CategoriaDTO;
import br.com.ada.silvaesouza.books.model.dto.CategoriaLivrosDTO;
import br.com.ada.silvaesouza.books.service.CategoriaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categoria")
@Slf4j
public class CategoriaController extends BaseController<CategoriaDTO, CategoriaService> {
    public CategoriaController(CategoriaService service) {
        super(service);
    }

    @GetMapping("/{categoria}/livros")
    public ResponseEntity<CategoriaLivrosDTO> buscarLivrosPorCategoria(
            @PathVariable("categoria") Long categoria) {
        try {
            return ResponseEntity.ok(service.buscarLivrosPorCategoria(categoria));
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
