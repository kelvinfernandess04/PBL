package br.edu.cefsa.macacarefa.service;

import br.edu.cefsa.macacarefa.dao.ApeDAO;
import br.edu.cefsa.macacarefa.model.Ape;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

@Service
public class ApeService implements UserDetailsService {

    @Autowired
    private ApeDAO apeDAO;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return apeDAO.findByAttribute("email", email)
                .map(usuario -> User.builder()
                    .username(usuario.getEmail())
                    .password(usuario.getPassword())
                    .roles("USER")
                    .build())
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }

    public void saveUser(Ape ape) {
        apeDAO.save(ape);
    }
}
