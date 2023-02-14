package br.com.ada.silvaesouza.books;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class BooksApplication {

	public static void main(String[] args) {

		SpringApplication.run(BooksApplication.class, args);
	}

}
