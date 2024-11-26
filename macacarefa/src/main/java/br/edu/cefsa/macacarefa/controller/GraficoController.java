package br.edu.cefsa.macacarefa.controller;

import br.edu.cefsa.macacarefa.model.Ape;
import br.edu.cefsa.macacarefa.repository.ApeRepository;
import br.edu.cefsa.macacarefa.repository.TarefacacoRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/graficos")
public class GraficoController {

    private final TarefacacoRepository tarefacacoRepository;
    private final ApeRepository apeRepository;

    public GraficoController(TarefacacoRepository tarefacacoRepository, ApeRepository apeRepository) {
        this.tarefacacoRepository = tarefacacoRepository;
        this.apeRepository = apeRepository;
    }

    @GetMapping("/tarefas-por-ape")
    public ResponseEntity<?> obterTarefasPorApe() {
        List<Ape> apes = apeRepository.findAll();
        Map<String, Long> tarefasPorApe = new HashMap<>();

        for (Ape ape : apes) {
            Long count = tarefacacoRepository.findByApe(ape).stream().count();
            tarefasPorApe.put(ape.getName(), count);
        }

        return ResponseEntity.ok(tarefasPorApe);
    }
}
