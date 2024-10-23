/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.cefsa.macacarefa.controller;

import br.edu.cefsa.macacarefa.model.Usuario;
import br.edu.cefsa.macacarefa.service.UsuarioService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * C:\Users\kelvi\OneDrive\Documentos\GitHub\PBL\macacarefa\src\main\resources\application.properties
 *
 * @author kelvi
 */
@Controller
@RequestMapping("/macacarefa/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/listar")
    public String listar(ModelMap model) {
        List<Usuario> padroes = usuarioService.findAll();

        List<Usuario> sortedPadroes = padroes.stream()
                .sorted((usuario1, usuario2)
                        -> usuario1.getUsername().compareTo(usuario2.getUsername()))
                .collect(Collectors.toList());
        model.addAttribute("padroes", sortedPadroes);
        return "/usuario/listar";
    }

    @GetMapping("/novo")
    public String inserir(ModelMap model) {
        model.addAttribute("usuario", new Usuario()); // Adiciona um objeto vazio para o formulário de criação
        return "/usuario/inserir";
    }

}
