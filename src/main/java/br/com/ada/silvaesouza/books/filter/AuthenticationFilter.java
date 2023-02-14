package br.com.ada.silvaesouza.books.filter;

import br.com.ada.silvaesouza.books.model.entity.Usuario;
import br.com.ada.silvaesouza.books.service.impl.AuthService;
import br.com.ada.silvaesouza.books.service.impl.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
public class AuthenticationFilter extends OncePerRequestFilter{
	
	@Autowired
	private JWTService jwtService;
	
	@Autowired
	private AuthService authService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String tokenString = request.getHeader("Authorization");
		
		if(tokenString!=null) {
			tokenString = tokenString.replace("Bearer ","");
			if(jwtService.validToken(tokenString)) {
				String username = jwtService.getUsernameByToken(tokenString);
				
				if(!username.isBlank() && SecurityContextHolder.getContext().getAuthentication()==null) {
					Usuario usuario = (Usuario) authService.loadUserByUsername(username);
					
					UsernamePasswordAuthenticationToken autentication = 
							new UsernamePasswordAuthenticationToken(usuario.getUsername(),null,usuario.getAuthorities());
					
					SecurityContextHolder.getContext().setAuthentication(autentication);
				}
			}
		}
		
		filterChain.doFilter(request, response);
		
	}

}
