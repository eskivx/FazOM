package com.example.demo.controller;

import com.example.demo.entities.DTO.AlterarUsuarioDTO;
import com.example.demo.entities.DTO.AtualizarSenhaDTO;
import com.example.demo.entities.Usuario;
import com.example.demo.service.UsuarioService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private UsuarioService usuarioService;

    public UsuarioController (UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @GetMapping("/buscar")
    public ResponseEntity<?> buscarUsuarios(){
        try {
            List<Usuario> usuarios = usuarioService.listarUsuarios();
            return ResponseEntity.ok(usuarios);
        } catch(Exception ex){
            return new ResponseEntity("Erro de consulta", HttpStatusCode.valueOf(504));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> criarUsuario(@RequestBody Usuario usuario){
        try {
            Usuario usuarioCriado = usuarioService.criarUsuario(usuario);
            return ResponseEntity.ok(usuarioCriado);
        } catch (Exception ex){
            return new ResponseEntity("Erro", HttpStatusCode.valueOf(504));
        }
    }

    @PutMapping("/alterar")
    public ResponseEntity<?> atualizarUsuario(@RequestBody AlterarUsuarioDTO usuario){
        try {
            usuario = usuarioService.atualizarUsuario(usuario);
            return ResponseEntity.ok(usuario);
        }catch(Exception ex) {
            return new ResponseEntity("Erro de Consulta", HttpStatusCode.valueOf(504));
        }
    }


    @PatchMapping("/alterar/senha")
    public ResponseEntity<?> atualizarSenhaUsuario(@RequestBody AtualizarSenhaDTO atualizarSenhaDTO){
        try {
            Usuario usuario = usuarioService.atualizarSenhaUsuario(atualizarSenhaDTO);
            return ResponseEntity.ok(usuario);
        }catch(Exception ex) {
            return new ResponseEntity("Erro de Consulta", HttpStatusCode.valueOf(504));
        }
    }

    @DeleteMapping("/excluir/{codigo}")
    public ResponseEntity<?> excluirUsuario(@PathVariable Long codigo){
        try {
            usuarioService.excluirUsuario(codigo);
            return ResponseEntity.ok("Excluído com Sucesso");
        }catch(Exception ex) {
            return new ResponseEntity("Erro de Consulta", HttpStatusCode.valueOf(504));
        }
    }


    @GetMapping("/buscar/{codigo}")
    public ResponseEntity<?> buscarUsuarioPorCodigo(@PathVariable Long codigo){
        try {
            Optional<Usuario> usuario = usuarioService.listarUsuarioPorCodigo(codigo);
            if(Optional.ofNullable(usuario).isPresent())
                return ResponseEntity.ok(usuario.get());
            else
                return ResponseEntity.notFound().build();

        }catch(Exception ex) {
            return new ResponseEntity("Erro de Consulta", HttpStatusCode.valueOf(504));
        }
    }



}
