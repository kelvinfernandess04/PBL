package br.edu.cefsa.macacarefa.service;

import br.edu.cefsa.macacarefa.dao.ApeDAO;
import br.edu.cefsa.macacarefa.model.Ape;
import br.edu.cefsa.macacarefa.repository.ApeRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

@Service
public class ApeService implements UserDetailsService {

    private final ApeRepository apeRepository;

    public ApeService(ApeRepository apeRepository) {
        this.apeRepository = apeRepository;
    }

    @Autowired
    private ApeDAO apeDAO;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Ape ape;
        ape = apeRepository.findByEmail(email);
        //.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
        if (ape != null) {
            return new User(ape.getEmail(), ape.getPassword(), List.of(new SimpleGrantedAuthority("USER")));
        }
        throw new UsernameNotFoundException(email);
    }

    @Transactional
    public void deleteByUsername(String email) throws UsernameNotFoundException {
        Ape ape;
        ape = apeRepository.findByEmail(email);
        //.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
        if (ape != null) {
            apeRepository.deleteByEmail(email);
        }
        throw new UsernameNotFoundException(email);
    }

    public void saveUser(Ape ape) {
        apeDAO.save(ape);
    }
    
    // Novo método para obter o objeto Ape completo
    public Ape getApeByEmail(String email) {
        return apeRepository.findByEmail(email); // Retorna o objeto Ape completo
    }
}
