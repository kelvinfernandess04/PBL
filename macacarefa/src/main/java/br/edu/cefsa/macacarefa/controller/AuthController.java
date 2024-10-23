package br.edu.cefsa.macacarefa.controller;

import br.edu.cefsa.macacarefa.model.Usuario;
import br.edu.cefsa.macacarefa.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login")
    public String login() {
        return "login"; // Página de login
    }

    @GetMapping("/index")
    public String index() {
        return "index"; // Página de login
    }

    @GetMapping("/home")
    public String loginSuccess(Authentication authentication) {
        // Você pode adicionar lógica adicional aqui, se necessário
        return "home"; // Nome da página HTML criada
    }

    @GetMapping("/cadastro")
    public String cadastro() {
        return "cadastro"; // Página de registro
    }

    @PostMapping("/cadastro")
    public String cadastroUser(Usuario usuario, Model model) {
        usuarioService.save(usuario); // Salva o usuário com senha criptografada
        return "redirect:/login"; // Redireciona para login após registro
    }
}
