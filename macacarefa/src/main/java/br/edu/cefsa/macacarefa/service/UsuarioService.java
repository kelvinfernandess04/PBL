package br.edu.cefsa.macacarefa.service;

import br.edu.cefsa.macacarefa.model.Usuario;
import br.edu.cefsa.macacarefa.repository.UsuarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void save(Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getPassword())); // Criptografa a senha
        usuarioRepository.save(usuario);
    }
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }
}
