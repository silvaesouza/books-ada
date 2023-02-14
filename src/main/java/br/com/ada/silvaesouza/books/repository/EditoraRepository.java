package br.com.ada.silvaesouza.books.repository;

import br.com.ada.silvaesouza.books.model.entity.Editora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditoraRepository extends JpaRepository<Editora,Long> {
}
