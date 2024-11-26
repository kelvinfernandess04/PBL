package br.edu.cefsa.macacarefa.repository;

import br.edu.cefsa.macacarefa.model.Ape;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ApeRepository extends JpaRepository<Ape, UUID> {
    Ape findByEmail(String email);
    
    // Método para deletar pelo e-mail
    @Transactional
    void deleteByEmail(String email);

}
