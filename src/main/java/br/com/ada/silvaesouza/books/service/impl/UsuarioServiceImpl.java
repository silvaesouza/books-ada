package br.com.ada.silvaesouza.books.service.impl;

import br.com.ada.silvaesouza.books.model.dto.TokenDTO;
import br.com.ada.silvaesouza.books.model.dto.UsuarioDTO;
import br.com.ada.silvaesouza.books.model.dto.UsuarioLoginDTO;
import br.com.ada.silvaesouza.books.model.entity.Usuario;
import br.com.ada.silvaesouza.books.model.mapper.UsuarioMapper;
import br.com.ada.silvaesouza.books.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import br.com.ada.silvaesouza.books.service.UsuarioService;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private UsuarioMapper mapper;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private JWTService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	public List<UsuarioLoginDTO> buscarTodos() {
		return mapper.parseListDTO(repository.findAll());
	}
	
	public UsuarioLoginDTO buscarPorId(Long id) {
		Optional<Usuario> usuarioOp = repository.findById(id);
		if(usuarioOp.isPresent()) {
			Usuario usuario = usuarioOp.get();
			return mapper.parseDTO(usuario);
		}
		
		throw new EntityNotFoundException();
	}
	
	public UsuarioLoginDTO criar(UsuarioLoginDTO clienteDTO) {
		Usuario usuario = mapper.parseEntity(clienteDTO);
		usuario.setPassword(encoder.encode(usuario.getPassword()));
		usuario.setId(null);
		repository.save(usuario);
		return mapper.parseDTO(usuario);
	}
	
	public UsuarioLoginDTO editar(Long id, UsuarioLoginDTO usuarioDTO) {
		
		Optional<Usuario> usuarioOp = repository.findById(id);
		
		if(usuarioOp.isPresent()) {
			Usuario usuario = usuarioOp.get();
			usuario.setNome(usuarioDTO.getNome());
			usuario.setEmail(usuarioDTO.getEmail());
			usuario.setId(id);
			usuario = repository.save(usuario);
			return mapper.parseDTO(usuario);
		}
		
		throw new EntityNotFoundException();
	}
	
	public void excluir(Long id) {
		if(!repository.existsById(id)) {
			throw new EntityNotFoundException();
		}
		
		repository.deleteById(id);
	}
	
	public TokenDTO logar(UsuarioLoginDTO usuarioLoginDTO) throws AuthenticationException,EntityNotFoundException {
		UsernamePasswordAuthenticationToken autentication = 
				new UsernamePasswordAuthenticationToken(usuarioLoginDTO.getUsername(),usuarioLoginDTO.getPassword());
		
		authenticationManager.authenticate(autentication);
		
		Optional<Usuario> usuarioOp = repository.findByUsername(usuarioLoginDTO.getUsername());
		
		if(usuarioOp.isPresent()) {
		
			String token = jwtService.generateToken(usuarioLoginDTO.getUsername());
			
			Usuario usuario = usuarioOp.get();
			
			UsuarioDTO usuarioDTO = new UsuarioDTO();
			usuario.setNome(usuario.getNome());
			usuario.setEmail(usuario.getEmail());	
			
			return TokenDTO.builder()
					.token(token)
					.type("Bearer")
					.user(usuarioDTO)
					.build();
		}
		
		throw new EntityNotFoundException();
	}
}
