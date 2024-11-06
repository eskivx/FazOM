package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entities.DTO.AlterarUsuarioDTO;
import com.example.demo.entities.DTO.CriarEstoqueDTO;
import org.springframework.stereotype.Service;


import com.example.demo.entities.Estoque;
import com.example.demo.repository.EstoqueRepository;

@Service
public class EstoqueService {

	private EstoqueRepository estoqueRepository;


	public EstoqueService(EstoqueRepository estoqueRepository) {
		this.estoqueRepository = estoqueRepository;

	}

	public Estoque criarEstoque(CriarEstoqueDTO estoqueDTO) throws Exception {

		Estoque estoque = new Estoque();
		estoque.setDescricao(estoqueDTO.getDescricao());
		estoque.setStatus(estoqueDTO.getStatus());



		return estoque;
		}


	public Estoque atualizarEstoque(Estoque estoque) {
		estoqueRepository.save(estoque);
		return estoque;
	}

	public List<Estoque> listarEstoques(){
		return estoqueRepository.findAll();
	}

	public Optional<Estoque> listarEstoquePorCodigo(Long codigo) {
		return estoqueRepository.findById(codigo);
	}

	public void excluirEstoque(Long codigo) {
		estoqueRepository.deleteById(codigo);
	}

	public void atualizarStatusEstoque(Long codigo, String status) {
		Optional<Estoque> estoque = listarEstoquePorCodigo(codigo);
		if(Optional.ofNullable(estoque).isPresent()) {
			estoque.get().setStatus(status);
			estoqueRepository.save(estoque.get());
		}

	}



}
