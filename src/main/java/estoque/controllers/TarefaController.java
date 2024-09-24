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
import rutke.julio.tarefas.entities.dtos.CriarTarefaDTO;
import rutke.julio.tarefas.services.TarefaService;

@RestController
@RequestMapping("/tarefa")
public class TarefaController {
	
	private TarefaService tarefaService;
	
	public TarefaController(TarefaService tarefaService) {
		this.tarefaService = tarefaService;
	}
	
	@GetMapping("/buscar")
	public ResponseEntity<?> buscarTarefas(){
		try {
			List<Tarefa> tarefas = tarefaService.listarTarefas();
			return ResponseEntity.ok(tarefas);
		} catch(Exception ex) {
			return new ResponseEntity("Erro de Consulta", HttpStatusCode.valueOf(504));
		}
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> criarTarefa(@RequestBody CriarTarefaDTO tarefa){
		try {
			Tarefa tarefaCriada = tarefaService.criarTarefa(tarefa);
			return ResponseEntity.ok(tarefa);
		}catch(Exception ex) {
			return new ResponseEntity("Erro de Consulta", HttpStatusCode.valueOf(504));
		}
	}
	
	@PutMapping("/alterar")
	public ResponseEntity<?> atualizarTarefa(@RequestBody Tarefa tarefa){
		try {
			Tarefa tarefaAtualizada = tarefaService.atualizarTarefa(tarefa);
			return ResponseEntity.ok(tarefa);
		}catch(Exception ex) {
			return new ResponseEntity("Erro de Consulta", HttpStatusCode.valueOf(504));
		}
	}
	
	@PatchMapping("/status/{codigo}")
	public ResponseEntity<?> atualizarStatusTarefa(@PathVariable Long codigo, @RequestParam("status") String status){
		try {
			tarefaService.atualizarStatusTarefa(codigo, status);
			return ResponseEntity.ok("Status alterado com sucesso!");
		}catch(Exception ex) {
			return new ResponseEntity("Erro de Consulta", HttpStatusCode.valueOf(504));
		}
	}
	

	@DeleteMapping("/excluir/{codigo}")
	public ResponseEntity<?> excluirTarefa(@PathVariable Long codigo){
		try {
			tarefaService.excluirTarefa(codigo);
			return ResponseEntity.ok("Excluída com Sucesso");
		}catch(Exception ex) {
			return new ResponseEntity("Erro de Consulta", HttpStatusCode.valueOf(504));
		}
	}
	
	@GetMapping("/buscar/{codigo}")
	public ResponseEntity<?> buscarTarefaPorCodigo(@PathVariable Long codigo){
		try {
			Optional<Tarefa> tarefa = tarefaService.listarTarefaPorCodigo(codigo);
			if(Optional.ofNullable(tarefa).isPresent())
				return ResponseEntity.ok(tarefa.get());
			else
				return ResponseEntity.notFound().build();
			
		}catch(Exception ex) {
			return new ResponseEntity("Erro de Consulta", HttpStatusCode.valueOf(504));
		}
	}
	

}
