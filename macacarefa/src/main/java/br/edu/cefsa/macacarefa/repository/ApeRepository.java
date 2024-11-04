package br.edu.cefsa.macacarefa.repository;

import br.edu.cefsa.macacarefa.model.Ape;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ApeRepository extends JpaRepository<Ape, UUID> {
    Ape findByEmail(String email);
}
