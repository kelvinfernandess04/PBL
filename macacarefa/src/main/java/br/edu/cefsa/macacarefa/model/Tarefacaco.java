package br.edu.cefsa.macacarefa.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tarefacaco", schema = "macacarefa")
public class Tarefacaco implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;
    private String descricao;
    private Integer pontos;

    @ManyToOne
    @JoinColumn(name = "ape_id") // O nome da coluna no banco deve ser ape_id
    private Ape ape;

    public Tarefacaco() {
    }

    public Tarefacaco(Long id, String nome, String descricao, Integer pontos, Ape ape) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.pontos = pontos;
        this.ape = ape;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getPontos() {
        return pontos;
    }

    public void setPontos(Integer pontos) {
        this.pontos = pontos;
    }

    public Ape getApe() {
        return ape;
    }

    public void setApe(Ape ape) {
        this.ape = ape;
    }
}
