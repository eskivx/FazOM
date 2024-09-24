package rutke.julio.tarefas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import rutke.julio.tarefas.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
