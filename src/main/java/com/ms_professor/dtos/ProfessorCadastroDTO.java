package com.ms_professor.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProfessorCadastroDTO(@NotBlank String matricula,
                                   @NotBlank String nomeCompleto,
                                   @NotBlank @Email String email,
                                   @NotBlank String telefone,
                                   @NotNull Long escolaId,
                                   @NotBlank String senha) {
}
