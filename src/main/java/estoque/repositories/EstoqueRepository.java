package rutke.julio.tarefas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import rutke.julio.tarefas.entities.Estoque;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {

}
