/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.cefsa.macacarefa.repository;

/**
 *
 * @author willi
 */
import br.edu.cefsa.macacarefa.model.Ape;
import br.edu.cefsa.macacarefa.model.Tarefacaco;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefacacoRepository extends JpaRepository<Tarefacaco, Long> {
    List<Tarefacaco> findByApeId(UUID apeId);
    List<Tarefacaco> findByApe(Ape ape);

}

