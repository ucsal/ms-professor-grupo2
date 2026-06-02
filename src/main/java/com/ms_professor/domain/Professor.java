package com.ms_professor.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tb_professores")
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Mantendo seu padrão do monolito
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String matricula;

    @NotBlank
    @Column(name = "nome_completo")
    private String nomeCompleto;

    @Email
    @NotBlank
    @Column(unique = true)
    private String email;

    @NotBlank
    private String telefone;

    @NotNull
    @Column(name = "escola_id")
    private Long escolaId; // REGRA DE OURO: Guarda apenas o ID da escola (Antônio), não a entidade!

    private Boolean ativo = true;

    public Professor() {
    }

    public Professor(Long id, String matricula, String nomeCompleto, String email, String telefone, Long escolaId, Boolean ativo) {
        this.id = id;
        this.matricula = matricula;
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.telefone = telefone;
        this.escolaId = escolaId;
        this.ativo = ativo;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }

    public String getNomeCompleto() { return nomeCompleto; }
    public void setNomeCompleto(String nomeCompleto) { this.nomeCompleto = nomeCompleto; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public Long getEscolaId() { return escolaId; }
    public void setEscolaId(Long escolaId) { this.escolaId = escolaId; }

    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }
}