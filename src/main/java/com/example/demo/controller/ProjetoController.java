package com.example.demo.controller;

import com.example.demo.entities.DTO.AlterarProjetoDTO;
import com.example.demo.entities.Projeto;
import com.example.demo.service.ProjetoService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projeto")
public class ProjetoController {

    private ProjetoService projetoService;

    public ProjetoController(ProjetoService projetoService) {
        this.projetoService = projetoService;
    }

    @GetMapping("/buscar")
    public ResponseEntity<?> buscarProjetos() {
        try{
            List<Projeto> projetos = projetoService.listarProjetos();
            return ResponseEntity.ok(projetos);
        } catch (Exception ex) {
            return new ResponseEntity("Erro de Consulta", HttpStatusCode.valueOf(504));
        }
    }
    @PostMapping("/add")
    public ResponseEntity<?> adicionarProjeto(@RequestBody Projeto projeto) {
        try{
           Projeto proj = projetoService.criarProjeto(projeto);
            return ResponseEntity.ok(proj);

        }catch (Exception ex) {
            return new ResponseEntity("Erro de Consulta", HttpStatusCode.valueOf(504));
        }


    }

    @PutMapping("/alterar")
    public ResponseEntity<?> alterarProjeto(@RequestBody AlterarProjetoDTO alterarProjetoDTO) {
        try{
            alterarProjetoDTO = projetoService.atualizarProjeto(alterarProjetoDTO);
            return ResponseEntity.ok(alterarProjetoDTO);
        } catch (Exception ex) {
            return new ResponseEntity("Erro de Consulta", HttpStatusCode.valueOf(504));
        }
    }

    @PatchMapping("/alterardescricao/{codigo}")
    public ResponseEntity<?> descricaoProjeto(@PathVariable Long codigo, @RequestParam("descricao") String descricao) {
        try{
            projetoService.atualizarDescricaoProjeto(codigo, descricao);
            return ResponseEntity.ok("Descrição atualizada com sucesso");
        } catch (Exception e) {
            return new ResponseEntity("Erro de Consulta", HttpStatusCode.valueOf(504));
        }
    }

    @DeleteMapping("/excluir/{codigo}")
    public ResponseEntity<?> excluirProjeto(@PathVariable Long codigo) {
        try{
            projetoService.excluirProjeto(codigo);
            return ResponseEntity.ok("Excluida com sucesso");
        } catch (Exception ex) {
            return new ResponseEntity("Erro de Consulta", HttpStatusCode.valueOf(504));
        }
    }




}
