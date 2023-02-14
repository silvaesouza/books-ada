package br.com.ada.silvaesouza.books.controller;

import br.com.ada.silvaesouza.books.model.dto.TokenDTO;
import br.com.ada.silvaesouza.books.model.dto.UsuarioLoginDTO;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.ada.silvaesouza.books.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
@Slf4j
public class UsuarioController extends BaseController<UsuarioLoginDTO, UsuarioService>  {
	
	public UsuarioController(UsuarioService service) {
		super(service);
	}
	
	@PostMapping("/auth")
	public ResponseEntity<TokenDTO> logar(
			@RequestBody @Valid UsuarioLoginDTO entidade) {
		try {
            return ResponseEntity.ok(service.logar(entidade));
        }catch(Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
	}

}
