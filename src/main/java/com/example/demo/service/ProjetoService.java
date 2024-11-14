package com.example.demo.service;


import com.example.demo.entities.DTO.AlterarProjetoDTO;
import com.example.demo.entities.DTO.AlterarUsuarioDTO;
import com.example.demo.entities.Projeto;
import com.example.demo.entities.Usuario;
import com.example.demo.repository.ProjetoRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProjetoService {

    private ProjetoRepository projetoRepository;
    public ProjetoService(ProjetoRepository projetoRepository) {
        this.projetoRepository = projetoRepository;
    }

    public Projeto criarProjeto(Projeto projeto) {
        Projeto proj = new Projeto();
        proj.setNome(projeto.getNome());
        proj.setDescricao(projeto.getDescricao());
        proj.setDataInicio(new Date());
        return projetoRepository.save(proj);
    }

    public AlterarProjetoDTO atualizarProjeto(AlterarProjetoDTO alterarProjetoDTO) throws Exception {
        Optional<Projeto> projeto = projetoRepository.findById(alterarProjetoDTO.getId());

        if(Optional.ofNullable(projeto).isPresent()) {
            projeto.get().setNome(alterarProjetoDTO.getNome());
            projeto.get().setDescricao(alterarProjetoDTO.getDescricao());
            projeto.get().setDataInicio(alterarProjetoDTO.getDataInicio());

            projetoRepository.save(projeto.get());

            return alterarProjetoDTO;
        }

        throw new Exception("Projeto não existe!");
    }

    public List<Projeto> listarProjetos() {
        return projetoRepository.findAll();
    }
    public Optional<Projeto> buscarProjetoPorId(Long id) {
        return projetoRepository.findById(id);

    }
    public void excluirProjeto(Long id) {
        projetoRepository.deleteById(id);
    }

    public void atualizarDescricaoProjeto(Long id, String descricao) {
        Optional<Projeto> projeto = buscarProjetoPorId(id);
        if(Optional.ofNullable(projeto).isPresent()) {
            projeto.get().setDescricao(descricao);
            projetoRepository.save(projeto.get());
        }
    }




}
