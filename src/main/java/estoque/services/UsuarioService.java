package rutke.julio.tarefas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import rutke.julio.tarefas.entities.Tarefa;
import rutke.julio.tarefas.entities.Usuario;
import rutke.julio.tarefas.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	
	private UsuarioRepository usuarioRepository;
	
	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	public Usuario criarUsuario(Usuario usuario) {
		usuarioRepository.save(usuario);
		return usuario;
	}
	
	public Usuario atualizarUsuario(Usuario usuario) {
		usuarioRepository.save(usuario);
		return usuario;
	}
	
	public List<Usuario> listarUsuarios(){
		return usuarioRepository.findAll();
	}
	
	public Optional<Usuario> listarUsuarioPorCodigo(Long codigo) { 
		Optional<Usuario> usuario = usuarioRepository.findById(codigo);
		return usuario;
	}
	
	public void excluirUsuario(Long codigo) {
		usuarioRepository.deleteById(codigo);
	}	

}
