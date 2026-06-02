package com.ms_professor.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    Optional<Professor> findByEmail(String email);
    Optional<Professor> findByMatricula(String matricula);

    List<Professor> findAllByEscolaIdAndAtivoTrue(Long escolaId);


    List<Professor> findAllByEscolaId(Long escolaId);
}
