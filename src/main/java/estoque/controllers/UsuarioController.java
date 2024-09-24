package rutke.julio.tarefas.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rutke.julio.tarefas.entities.Tarefa;
import rutke.julio.tarefas.entities.Usuario;
import rutke.julio.tarefas.services.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	private UsuarioService usuarioService;
	
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@GetMapping("/buscar")
	public ResponseEntity<?> buscarUsuarios(){
		try {
			List<Usuario> usuarios = usuarioService.listarUsuarios();
			return ResponseEntity.ok(usuarios);
		} catch(Exception ex) {
			return new ResponseEntity("Erro de Consulta", HttpStatusCode.valueOf(504));
		}
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> criarUsuario(@RequestBody Usuario usuario){
		try {
			Usuario usuarioCriado = usuarioService.criarUsuario(usuario);
			return ResponseEntity.ok(usuarioCriado);
		}catch(Exception ex) {
			return new ResponseEntity("Erro de Consulta", HttpStatusCode.valueOf(504));
		}
	}
	
	@PutMapping("/alterar")
	public ResponseEntity<?> atualizarUsuario(@RequestBody Usuario usuario){
		try {
			usuario = usuarioService.atualizarUsuario(usuario);
			return ResponseEntity.ok(usuario);
		}catch(Exception ex) {
			return new ResponseEntity("Erro de Consulta", HttpStatusCode.valueOf(504));
		}
	}

	@DeleteMapping("/excluir/{codigo}")
	public ResponseEntity<?> excluirUsuario(@PathVariable Long codigo){
		try {
			usuarioService.excluirUsuario(codigo);
			return ResponseEntity.ok("Excluído com Sucesso");
		}catch(Exception ex) {
			return new ResponseEntity("Erro de Consulta", HttpStatusCode.valueOf(504));
		}
	}
	
	@GetMapping("/buscar/{codigo}")
	public ResponseEntity<?> buscarUsuarioPorCodigo(@PathVariable Long codigo){
		try {
			Optional<Usuario> usuario = usuarioService.listarUsuarioPorCodigo(codigo);
			if(Optional.ofNullable(usuario).isPresent())
				return ResponseEntity.ok(usuario.get());
			else
				return ResponseEntity.notFound().build();
			
		}catch(Exception ex) {
			return new ResponseEntity("Erro de Consulta", HttpStatusCode.valueOf(504));
		}
	}

}
