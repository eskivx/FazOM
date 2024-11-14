package com.example.demo.entities.DTO;

import com.example.demo.entities.Projeto;

public class AtualizarItemProjetoDTO {
    private Long codigo;
    private Projeto projeto;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }
}
