package estoque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import estoque.entities.Estoque;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {

}
