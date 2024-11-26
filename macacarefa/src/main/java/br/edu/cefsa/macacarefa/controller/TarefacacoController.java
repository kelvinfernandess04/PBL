/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.cefsa.macacarefa.controller;

/**
 *
 * @author willi
 */
import br.edu.cefsa.macacarefa.model.Ape;
import br.edu.cefsa.macacarefa.model.Tarefacaco;
import br.edu.cefsa.macacarefa.repository.ApeRepository;
import br.edu.cefsa.macacarefa.repository.TarefacacoRepository;
import br.edu.cefsa.macacarefa.service.TarefacacoService;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/tarefacaco")
public class TarefacacoController {

    private final TarefacacoRepository tarefacacoRepository;
    private final ApeRepository apeRepository;
    private final TarefacacoService tarefacacoService;

    public TarefacacoController(TarefacacoRepository tarefacacoRepository, ApeRepository apeRepository, TarefacacoService tarefacacoService) {
        this.tarefacacoRepository = tarefacacoRepository;
        this.apeRepository = apeRepository;
        this.tarefacacoService = tarefacacoService;
}

    @GetMapping
    public String listarTarefacaco(Model model) {
        model.addAttribute("tarefacaco", tarefacacoRepository.findAll());
        return "listarTarefacaco"; // Nome do template HTML
    }

    @GetMapping("/novo")
public String novoTarefacaco(Model model) {
    model.addAttribute("tarefacaco", new Tarefacaco());
    model.addAttribute("apes", apeRepository.findAll()); // Carrega os usuários
    return "tarefacacoFormulario";
}


    @PostMapping
    public String salvarTarefacaco(@ModelAttribute Tarefacaco tarefacaco) {
        tarefacacoRepository.save(tarefacaco);
        return "redirect:/tarefacaco";
    }

    @GetMapping("/editar/{id}")
public String editarTarefacaco(@PathVariable Long id, Model model) {
    Tarefacaco tarefacaco = tarefacacoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Tarefacaco inválida: " + id));
    model.addAttribute("tarefacaco", tarefacaco);
    model.addAttribute("apes", apeRepository.findAll()); // Carrega os usuários
    return "tarefacacoFormulario";
}


    @GetMapping("/deletar/{id}")
    public String deletarTarefacaco(@PathVariable Long id) {
        Tarefacaco tarefacaco = tarefacacoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tarefacaco inválida: " + id));
        tarefacacoRepository.delete(tarefacaco);
        return "redirect:/tarefacaco";
    }
    
   @GetMapping("/concluir/{tarefaId}/{apeEmail}")
public String concluirTarefa(@PathVariable Long tarefaId, @PathVariable String apeEmail, Model model) {
    try {
        // Chama o serviço para concluir a tarefa
        tarefacacoService.concluirTarefa(tarefaId, apeEmail);

        // Adiciona uma mensagem de sucesso ao modelo
        model.addAttribute("mensagem", "Tarefa concluída e pontos atribuídos!");

        // Redireciona para a página de sucesso
        return "tarefaConcluida";  // Nome da página de sucesso
    } catch (Exception e) {
        model.addAttribute("mensagem", "Erro ao concluir a tarefa: " + e.getMessage());
        return "erro";  // Página de erro (caso ocorra algum problema)
    }
}




    
   

}
