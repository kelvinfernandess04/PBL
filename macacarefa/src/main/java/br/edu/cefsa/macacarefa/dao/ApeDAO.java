package br.edu.cefsa.macacarefa.dao;

import br.edu.cefsa.macacarefa.model.Ape;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class ApeDAO extends AbstractDAO<Ape> {
    // Nenhum código adicional necessário, pois AbstractDAO já possui os métodos necessários
}
