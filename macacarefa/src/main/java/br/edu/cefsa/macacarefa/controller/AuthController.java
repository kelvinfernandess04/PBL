package br.edu.cefsa.macacarefa.controller;

import br.edu.cefsa.macacarefa.model.Usuario;
import br.edu.cefsa.macacarefa.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/register")
    public String register() {
        return "register"; // Página de registro
    }

    @PostMapping("/register")
    public String registerUser(Usuario usuario, Model model) {
        usuarioService.save(usuario); // Salva o usuário com senha criptografada
        return "redirect:/login"; // Redireciona para login após registro
    }
}
