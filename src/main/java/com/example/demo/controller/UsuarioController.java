package com.example.demo.controller;

import com.example.demo.entities.Usuario;
import com.example.demo.service.UsuarioService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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





}
