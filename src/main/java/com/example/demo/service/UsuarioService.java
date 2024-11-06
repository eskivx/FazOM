package com.example.demo.service;

import com.example.demo.entities.DTO.AlterarUsuarioDTO;
import com.example.demo.entities.DTO.AtualizarSenhaDTO;
import com.example.demo.entities.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;
    private PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder){
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario criarUsuario(Usuario usuario){
        String passwd = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(passwd);
        usuarioRepository.save(usuario);
        return usuario;
    }

    public AlterarUsuarioDTO alterarUsuario(AlterarUsuarioDTO alterarUsuarioDTO) throws Exception{
        Optional<Usuario> usuario = usuarioRepository.findById(alterarUsuarioDTO.getCodigo());

        if (Optional.ofNullable(usuario).isPresent()) {
            usuario.get().setEmail(alterarUsuarioDTO.getEmail());
            usuario.get().setCpf(alterarUsuarioDTO.getSenha());
            usuario.get().setNome(alterarUsuarioDTO.getNome());
            usuario.get().setTelefone(alterarUsuarioDTO.getNome());
            usuarioRepository.save(usuario.get());
            return alterarUsuarioDTO;
        }
        throw new Exception("Usuario nao existe");

    }

    public List<Usuario> listarUsuarios(){
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> listarUsuarioPorCodigo(Long codigo){
        Optional<Usuario> usuario = usuarioRepository.findById(codigo);
        return usuario;
    }

    public void excluirUsuario(Long codigo){
        usuarioRepository.deleteById(codigo);
    }

    public Optional<Usuario> getUsuarioAuth(String username){
        return usuarioRepository.findByEmail(username);
    }

    public AlterarUsuarioDTO atualizarUsuario(AlterarUsuarioDTO alterarUsuarioDTO) throws Exception {
        Optional<Usuario> usuario = usuarioRepository.findById(alterarUsuarioDTO.getCodigo());

        if(Optional.ofNullable(usuario).isPresent()) {
            usuario.get().setEmail(alterarUsuarioDTO.getEmail());
            usuario.get().setNome(alterarUsuarioDTO.getNome());
            usuario.get().setPermissao(alterarUsuarioDTO.getPermissao());
            usuario.get().setTelefone(alterarUsuarioDTO.getTelefone());
            usuarioRepository.save(usuario.get());

            return alterarUsuarioDTO;
        }

        throw new Exception("Usuário não existe!");
    }

    public Usuario atualizarSenhaUsuario(AtualizarSenhaDTO senhaUsuario) {
        Optional<Usuario> usuario = usuarioRepository.findById(senhaUsuario.getCodigo());
        String pass = passwordEncoder.encode(senhaUsuario.getSenha());
        usuario.get().setSenha(pass);
        usuarioRepository.save(usuario.get());
        return usuario.get();
    }
}
