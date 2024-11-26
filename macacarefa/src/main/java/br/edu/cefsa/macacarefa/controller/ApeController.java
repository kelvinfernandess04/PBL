package br.edu.cefsa.macacarefa.controller;

/**
 *
 * @author willi
 */
import br.edu.cefsa.macacarefa.model.Ape;
import br.edu.cefsa.macacarefa.repository.ApeRepository;
import br.edu.cefsa.macacarefa.service.ApeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/ape")

public class ApeController {

    @Autowired
    private ApeRepository apeRepository;
    
    @Autowired
    private ApeService apeService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public String listarApes(Model model) {
        List<Ape> apes = apeRepository.findAll();
        model.addAttribute("ape", apes);
        return "ape";
    }

    @PostMapping("/adicionar")
    public String adicionarApe(Ape ape) {
        apeRepository.save(ape);
        return "redirect:/ape";
    }

    @PostMapping("/editar")
    public String editarApe(Ape ape) {
        // Verifica se o email é fornecido
        if (ape.getEmail() != null) {
            // Tenta buscar o ape pelo email fornecido
            Ape apeExistente = apeRepository.findByEmail(ape.getEmail());

            if (apeExistente != null) {
                // Se a senha for nula ou vazia, mantemos a senha existente
                if (ape.getPassword() == null || ape.getPassword().isEmpty()) {
                    ape.setPassword(apeExistente.getPassword());  // Mantém a senha antiga
                } else {
                    // Se a senha foi alterada, criptografamos a nova senha
                    ape.setPassword(passwordEncoder.encode(ape.getPassword()));
                }

                // Atualiza os outros campos (por exemplo, nome)
                apeExistente.setName(ape.getName());
                apeExistente.setPassword(ape.getPassword());  // Atualiza a senha, se houver alteração

                // Salva as alterações no banco
                apeRepository.save(apeExistente);
            } else {
                // Caso o email não seja encontrado, podemos lançar uma exceção ou retornar uma mensagem
                throw new UsernameNotFoundException("Usuário não encontrado com o e-mail: " + ape.getEmail());
            }
        } else {
            // Se o email não foi fornecido, podemos lançar uma exceção ou tratar de outra forma
            throw new IllegalArgumentException("Email é obrigatório.");
        }

        // Redireciona para a lista de apes ou outra página após a edição
        return "redirect:/ape";
    }

    @GetMapping("/editarApe/{email}")
    public String editarApe(@PathVariable String email, Model model) {
        Ape ape = apeRepository.findByEmail(email);

        model.addAttribute("ape", ape);
        return "editarApe";
    }

    @PostMapping("/remover")
    public String removerApe(@RequestParam String email) {
        // Verifica se o ape existe pelo email
        Ape ape = apeRepository.findByEmail(email);

        if (ape != null) {
            apeRepository.deleteByEmail(email);  // Deleta o Ape pelo email
        } else {
            // Caso o ape com o email não exista
            return "redirect:/ape?error=Email não encontrado";
        }

        return "redirect:/ape";  // Redireciona para a lista de Apes
    }

}
