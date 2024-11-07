package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entities.DTO.CriarItemDTO;
import com.example.demo.entities.Item;
import org.springframework.stereotype.Service;



import com.example.demo.repository.ItemRepository;

@Service
public class ItemService {

	private ItemRepository itemRepository;


	public ItemService(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;

	}

	public Item criarItem(CriarItemDTO itemDTO) throws Exception {

		Item item = new Item();
		item.setDescricao(itemDTO.getDescricao());
		item.setStatus(itemDTO.getStatus());



		return item;
		}


	public Item atualizarItem(Item item) {
		itemRepository.save(item);
		return item;
	}

	public List<Item> listarItems(){
		return itemRepository.findAll();
	}

	public Optional<Item> listarItemPorCodigo(Long codigo) {
		return itemRepository.findById(codigo);
	}

	public void excluirItem(Long codigo) {
		itemRepository.deleteById(codigo);
	}

	public void atualizarStatusItem(Long codigo, String status) {
		Optional<Item> item = listarItemPorCodigo(codigo);
		if(Optional.ofNullable(item).isPresent()) {
			item.get().setStatus(status);
			itemRepository.save(item.get());
		}

	}



}
