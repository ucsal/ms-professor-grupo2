package com.ms_professor.dtos;

public record ProfessorResponseDTO(Long id, String matricula, String nomeCompleto, String email, String telefone, Long escolaId, Boolean ativo) {
}
