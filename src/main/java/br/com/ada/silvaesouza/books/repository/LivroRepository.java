package br.com.ada.silvaesouza.books.repository;

import br.com.ada.silvaesouza.books.model.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<Livro,Long> {

}
