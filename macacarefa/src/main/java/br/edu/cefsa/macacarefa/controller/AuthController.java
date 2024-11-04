package br.edu.cefsa.macacarefa.controller;

import br.edu.cefsa.macacarefa.model.Ape;
import br.edu.cefsa.macacarefa.service.ApeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.stereotype.Controller;

@Controller
public class AuthController {

    @Autowired
    private ApeService apeService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/cadastro")
    public String cadastro(Model model) {
        model.addAttribute("usuario", new Ape());
        return "cadastro";
    }

    @PostMapping("/cadastro")
    public String cadastrar(Ape usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        apeService.saveUser(usuario);
        return "redirect:/login";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }
}
