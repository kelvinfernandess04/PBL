/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.cefsa.macacarefa.service;

/**
 *
 * @author willi
 */
import br.edu.cefsa.macacarefa.model.Ape;
import br.edu.cefsa.macacarefa.model.Tarefacaco;
import br.edu.cefsa.macacarefa.repository.ApeRepository;
import br.edu.cefsa.macacarefa.repository.TarefacacoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TarefacacoService {
    private final TarefacacoRepository tarefacacoRepository;
    private final ApeRepository apeRepository;

    public TarefacacoService(TarefacacoRepository tarefacacoRepository, ApeRepository apeRepository) {
        this.tarefacacoRepository = tarefacacoRepository;
        this.apeRepository = apeRepository;
    }
    @Transactional
    public List<Tarefacaco> getTarefasDoUsuario(Ape ape) {
        return tarefacacoRepository.findByApe(ape);  // Supondo que haja um método no repositório para buscar tarefas pelo usuário
    }
    

    
    public List<Tarefacaco> listarTodos() {
        return tarefacacoRepository.findAll();
    }

    public Optional<Tarefacaco> buscarPorId(Long id) {
        return tarefacacoRepository.findById(id);
    }

    public Tarefacaco salvar(Tarefacaco tarefacaco) {
        return tarefacacoRepository.save(tarefacaco);
    }

    public void excluir(Long id) {
        tarefacacoRepository.deleteById(id);
    }
    
    public Tarefacaco criarTarefa(Tarefacaco tarefa, UUID apeId) {
    Ape ape = apeRepository.findById(apeId)
        .orElseThrow(() -> new EntityNotFoundException("Ape não encontrado"));

    tarefa.setApe(ape);
    return tarefacacoRepository.save(tarefa);
}

    public void concluirTarefa(Long tarefaId, String apeEmail) {
        // Recupera a tarefa pelo ID
        Tarefacaco tarefacaco = tarefacacoRepository.findById(tarefaId).orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));

        // Recupera o usuário pelo email
        Ape ape = apeRepository.findByEmail(apeEmail);
        // Adiciona os pontos da tarefa ao usuário
        ape.setPontos(ape.getPontos() + tarefacaco.getPontos());

        // Salva o usuário com os pontos atualizados
        apeRepository.save(ape);

        
        tarefacacoRepository.delete(tarefacaco); 
    }
    
}

