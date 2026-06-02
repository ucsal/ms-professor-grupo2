package com.ms_professor.dtos;

import jakarta.validation.constraints.NotNull;

public record ProfessorStatusDTO(@NotNull Boolean ativo) {
}
