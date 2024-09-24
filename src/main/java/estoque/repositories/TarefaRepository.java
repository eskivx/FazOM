package rutke.julio.tarefas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import rutke.julio.tarefas.entities.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

}
