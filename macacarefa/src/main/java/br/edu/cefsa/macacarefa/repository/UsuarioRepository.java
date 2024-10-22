/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.cefsa.macacarefa.repository;

import br.edu.cefsa.macacarefa.model.Usuario;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author kelvi
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    // Método para buscar um padrão pelo nome
    Optional<Usuario> findByNome(String nome);
}
