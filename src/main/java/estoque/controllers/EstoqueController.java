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
@RequestMapping("/estoque")
public class EstoqueController {
	
	private EstoqueController estoqueController;
	
	public EstoqueController(EstoqueController estoqueController) {
		this.estoqueController = estoqueController;
	}
	
	@GetMapping("/buscar")
	public ResponseEntity<?> buscarEstoque(){
		try {
			List<Tarefa> estoque = estoqueService.listarTarefas();
			return ResponseEntity.ok(estoque);
		} catch(Exception ex) {
			return new ResponseEntity("Erro de Consulta", HttpStatusCode.valueOf(504));
		}
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> criarEstoque(@RequestBody CriarEstoqueDTO estoque){
		try {
			Tarefa estoqueCriado = estoqueService.criarTarefa(estoque);
			return ResponseEntity.ok(estoque);
		}catch(Exception ex) {
			return new ResponseEntity("Erro de Consulta", HttpStatusCode.valueOf(504));
		}
	}
	
	@PutMapping("/alterar")
	public ResponseEntity<?> atualizarEstoque(@RequestBody Estoque estoque){
		try {
			Tarefa estoqueAtualizado = estoqueService.atualizarTarefa(estoque);
			return ResponseEntity.ok(estoque);
		}catch(Exception ex) {
			return new ResponseEntity("Erro de Consulta", HttpStatusCode.valueOf(504));
		}
	}
	
	@PatchMapping("/status/{codigo}")
	public ResponseEntity<?> atualizarStatusEstoque(@PathVariable Long codigo, @RequestParam("status") String status){
		try {
			estoqueService.atualizarStatusEstoque(codigo, status);
			return ResponseEntity.ok("Status alterado com sucesso!");
		}catch(Exception ex) {
			return new ResponseEntity("Erro de Consulta", HttpStatusCode.valueOf(504));
		}
	}
	

	@DeleteMapping("/excluir/{codigo}")
	public ResponseEntity<?> excluirEstoque(@PathVariable Long codigo){
		try {
			estoqueService.excluirEstoque(codigo);
			return ResponseEntity.ok("Excluída com Sucesso");
		}catch(Exception ex) {
			return new ResponseEntity("Erro de Consulta", HttpStatusCode.valueOf(504));
		}
	}
	
	@GetMapping("/buscar/{codigo}")
	public ResponseEntity<?> buscarEstoquePorCodigo(@PathVariable Long codigo){
		try {
			Optional<Estoque> estoque = estoqueService.listarEstoquePorCodigo(codigo);
			if(Optional.ofNullable(estoque).isPresent())
				return ResponseEntity.ok(estoque.get());
			else
				return ResponseEntity.notFound().build();
			
		}catch(Exception ex) {
			return new ResponseEntity("Erro de Consulta", HttpStatusCode.valueOf(504));
		}
	}
	

}
