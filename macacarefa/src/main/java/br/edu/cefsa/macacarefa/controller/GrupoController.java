package br.edu.cefsa.macacarefa.controller;

import br.edu.cefsa.macacarefa.model.Ape;
import br.edu.cefsa.macacarefa.model.Tarefacaco;
import br.edu.cefsa.macacarefa.repository.TarefacacoRepository;
import br.edu.cefsa.macacarefa.service.ApeService;
import br.edu.cefsa.macacarefa.service.TarefacacoService;
import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
@RequestMapping("/grupo")
public class GrupoController {

    private final TarefacacoService tarefacacoService;
    private final ApeService apeService;

    public GrupoController(TarefacacoService tarefacacoService, ApeService apeService) {
        this.tarefacacoService = tarefacacoService;
        this.apeService = apeService;
    }

    @GetMapping("/tarefas")
    public String listarTarefas(Model model, Principal principal) {
        // Obtém o e-mail do usuário logado
        String email = principal.getName();
        System.out.println("Email do usuário logado: " + email);

        // Obtém o objeto Ape (usuário) baseado no e-mail
        Ape ape = apeService.getApeByEmail(email); // Agora usamos o novo método

        if (ape == null) {
            // Tratar caso o usuário não seja encontrado
            return "redirect:/login"; // Ou qualquer outra ação, como uma página de erro
        }

        // Obtém as tarefas atribuídas ao usuário
        List<Tarefacaco> tarefas = tarefacacoService.getTarefasDoUsuario(ape);

        // Adiciona as tarefas ao modelo
        model.addAttribute("tarefas", tarefas);

        // Retorna a view para exibir as tarefas
        return "grupo"; // Certifique-se de que o nome da view está correto
    }
    
}


