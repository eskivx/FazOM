package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import com.example.demo.entities.DTO.CriarItemDTO;
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

import com.example.demo.entities.Item;
import com.example.demo.service.ItemService;

@RestController
@RequestMapping("/item")
public class ItemController {

	private ItemService itemService;


	public ItemController(ItemService itemService) {
		this.itemService = itemService;
	}

	@GetMapping("/buscar")
	public ResponseEntity<?> buscarItem(){
		try {
			List<Item> item = itemService.listarItems();
			return ResponseEntity.ok(item);
		} catch(Exception ex) {
			return new ResponseEntity("Erro de Consulta", HttpStatusCode.valueOf(504));
		}
	}

	@PostMapping("/add")
	public ResponseEntity<?> criaritem(@RequestBody CriarItemDTO item){
		try {
			Item itemCriado = itemService.criarItem(item);
			return ResponseEntity.ok(itemCriado);
		}catch(Exception ex) {
			return new ResponseEntity("Erro de Consulta", HttpStatusCode.valueOf(504));
		}
	}

	@PutMapping("/alterar/{codigo}")
	public ResponseEntity<?> atualizarItem(@PathVariable Long codigo, @RequestBody Item item) {
		try {

			Item itemAtualizado = itemService.atualizarItem(codigo, item);


			return ResponseEntity.ok(itemAtualizado);
		} catch (Exception ex) {

			return new ResponseEntity("Erro de Consulta", HttpStatusCode.valueOf(504));
		}
	}


	@PatchMapping("/status/{codigo}")
	public ResponseEntity<?> atualizarStatusitem(@PathVariable Long codigo, @RequestParam("status") String status){
		try {
			itemService.atualizarStatusItem(codigo, status);
			return ResponseEntity.ok("Status alterado com sucesso!");
		}catch(Exception ex) {
			return new ResponseEntity("Erro de Consulta", HttpStatusCode.valueOf(504));
		}
	}


	@DeleteMapping("/excluir/{codigo}")
	public ResponseEntity<?> excluiritem(@PathVariable Long codigo){
		try {
			itemService.excluirItem(codigo);
			return ResponseEntity.ok("Excluída com Sucesso");
		}catch(Exception ex) {
			return new ResponseEntity("Erro de Consulta", HttpStatusCode.valueOf(504));
		}
	}

	@GetMapping("/buscar/{codigo}")
	public ResponseEntity<?> buscaritemPorCodigo(@PathVariable Long codigo){
		try {
			Optional<Item> item = itemService.listarItemPorCodigo(codigo);
			if(Optional.ofNullable(item).isPresent())
				return ResponseEntity.ok(item.get());
			else
				return ResponseEntity.notFound().build();

		}catch(Exception ex) {
			return new ResponseEntity("Erro de Consulta", HttpStatusCode.valueOf(504));
		}
	}

	@GetMapping("/buscar/status/{value}")
	public ResponseEntity<?> buscaritemPorStatus(@PathVariable Long value){
		try {
			Optional<Item> item = itemService.listarItemPorCodigo(value);
			if(Optional.ofNullable(item).isPresent())
				return ResponseEntity.ok(item.get().getStatus());
			else
				return ResponseEntity.notFound().build();

		}catch(Exception ex) {
			return new ResponseEntity("Erro de Consulta", HttpStatusCode.valueOf(504));
		}
	}

}
