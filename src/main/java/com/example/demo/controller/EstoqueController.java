package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import com.example.demo.entities.DTO.AlterarUsuarioDTO;
import com.example.demo.entities.DTO.CriarEstoqueDTO;
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

import com.example.demo.entities.Estoque;
import com.example.demo.service.EstoqueService;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {
	
	private EstoqueService estoqueService;
	
	
	public EstoqueController(EstoqueService estoqueService) {
		this.estoqueService = estoqueService; 
	}
	
	@GetMapping("/buscar")
	public ResponseEntity<?> buscarEstoque(){
		try {
			List<Estoque> estoque = estoqueService.listarEstoques();
			return ResponseEntity.ok(estoque);
		} catch(Exception ex) {
			return new ResponseEntity("Erro de Consulta", HttpStatusCode.valueOf(504));
		}
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> criarEstoque(@RequestBody CriarEstoqueDTO estoque){
		try {
			Estoque estoqueCriado = estoqueService.criarEstoque(estoque);
			return ResponseEntity.ok(estoque);
		}catch(Exception ex) {
			return new ResponseEntity("Erro de Consulta", HttpStatusCode.valueOf(504));
		}
	}
	
	@PutMapping("/alterar")
	public ResponseEntity<?> atualizarEstoque(@RequestBody Estoque estoque){
		try {
			Estoque estoqueAtualizado = estoqueService.atualizarEstoque(estoque);
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

	@GetMapping("/buscar/status/{value}")
	public ResponseEntity<?> buscarEstoquePorStatus(@PathVariable Long value){
		try {
			Optional<Estoque> estoque = estoqueService.listarEstoquePorCodigo(value);
			if(Optional.ofNullable(estoque).isPresent())
				return ResponseEntity.ok(estoque.get());
			else
				return ResponseEntity.notFound().build();

		}catch(Exception ex) {
			return new ResponseEntity("Erro de Consulta", HttpStatusCode.valueOf(504));
		}
	}

}
