package com.ms_professor.controller;

import com.ms_professor.dtos.ProfessorCadastroDTO;
import com.ms_professor.dtos.ProfessorResponseDTO;
import com.ms_professor.dtos.ProfessorStatusDTO;
import com.ms_professor.dtos.ProfessorUpdateDTO;
import com.ms_professor.service.ProfessorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professores")
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    // 1. CADASTRAR PROFESSOR (Retorna 201 Created)
    @PostMapping
    public ResponseEntity<Void> cadastrarProfessor(@RequestBody @Valid ProfessorCadastroDTO professorCadastroDTO) {
        this.professorService.cadastrarProfessor(professorCadastroDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // 2. LISTAR E FILTRAR PROFESSORES (Retorna 200 OK com a Lista)
    // Exemplo de uso no Front: /api/professores?escolaId=1&ativo=true
    @GetMapping
    public ResponseEntity<List<ProfessorResponseDTO>> listarProfessores(
            @RequestParam(required = false) Long escolaId,
            @RequestParam(required = false) Boolean ativo) {
        List<ProfessorResponseDTO> professores = this.professorService.listProfessores(escolaId, ativo);
        return ResponseEntity.ok(professores);
    }


    // 4. ATUALIZAR DADOS DO PROFESSOR (Retorna 200 OK com os dados atualizados)
    @PutMapping("/{id}")
    public ResponseEntity<ProfessorResponseDTO> atualizarProfessor(
            @PathVariable Long id,
            @RequestBody @Valid ProfessorUpdateDTO professorUpdateDTO) {
        ProfessorResponseDTO response = this.professorService.atualizarDadosProfessor(id, professorUpdateDTO);
        return ResponseEntity.ok(response);
    }

    // 5. ATIVAR OU INATIVAR (Retorna 204 No Content pois só altera status e não retorna corpo)
    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> alterarStatus(
            @PathVariable Long id,
            @RequestBody @Valid ProfessorStatusDTO professorStatusDTO) {
        this.professorService.ativarOuInativarProfessor(id, professorStatusDTO);
        return ResponseEntity.noContent().build();
    }
}