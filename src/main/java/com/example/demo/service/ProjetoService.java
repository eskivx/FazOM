package com.example.demo.service;


import com.example.demo.entities.Projeto;
import com.example.demo.repository.ProjetoRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

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

    public Projeto AtualizarProjeto(Projeto projeto) {
        projetoRepository.save(projeto);
        return projeto;
    }




}
