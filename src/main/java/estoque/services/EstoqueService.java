

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import rutke.julio.tarefas.entities.Tarefa;
import rutke.julio.tarefas.entities.Usuario;
import rutke.julio.tarefas.entities.dtos.CriarTarefaDTO;
import rutke.julio.tarefas.repositories.TarefaRepository;

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
	}
	
	public Estoque atualizarEstoque(Estoque estoque) {
		estoqueRepository.save(estoque);
		return estoque;
	}
	
	public List<Estoque> listarEstoques(){
		return estoqueRepository.findAll();
	}
	
	public Optional<Estoque> listarEstoquePorCodigo(Long codigo) {
		Optional<Estoque> estoque = EstoqueRepository.findById(codigo);
		return estoque;
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
