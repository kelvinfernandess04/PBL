/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.cefsa.macacarefa.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

/**
 *
 * @author kelvi
 */
@Entity
@Table(name = "TB_USUARIO")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "ID_PADRAO")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idPadrao;
    @Column(name = "NOME", nullable = false, unique = true, length = 20)
    private String nome;
    @Column(name = "EMAIL", nullable = false)
    private String email;

    public Usuario() {
    }

    public UUID getIdPadrao() {
        return idPadrao;
    }

    public void setIdPadrao(UUID idPadrao) {
        this.idPadrao = idPadrao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
