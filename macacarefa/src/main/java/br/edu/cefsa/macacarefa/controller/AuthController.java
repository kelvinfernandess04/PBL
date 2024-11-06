package br.edu.cefsa.macacarefa.controller;

import br.edu.cefsa.macacarefa.model.Ape;
import br.edu.cefsa.macacarefa.service.ApeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

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

    @GetMapping("/index")
    public String index() {
        return "index";
    }
    
    @GetMapping("/h2-console")
    public String h2() {
        return "h2-console";
    }
    @GetMapping("/cadastro")
    public String cadastro(Model model) {
        model.addAttribute("ape", new Ape());
        return "cadastro";
    }

    @PostMapping("/cadastro")
    public String cadastrar(@ModelAttribute("ape") Ape usuario) {
        System.out.printf("arrombado" + usuario.getEmail());
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        apeService.saveUser(usuario);
        System.out.printf("passou");
        return "redirect:/login";       
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }
}
