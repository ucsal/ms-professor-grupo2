package com.ms_professor.service;

import com.ms_professor.client.AuthClient;
import com.ms_professor.client.UsuarioRegisterDTO;
import com.ms_professor.domain.Professor;
import com.ms_professor.domain.ProfessorRepository;
import com.ms_professor.dtos.ProfessorCadastroDTO;
import com.ms_professor.dtos.ProfessorResponseDTO;
import com.ms_professor.dtos.ProfessorStatusDTO;
import com.ms_professor.dtos.ProfessorUpdateDTO;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProfessorService {
    private final ProfessorRepository professorRepository;
    private final  AuthClient authClient;

    public ProfessorService(ProfessorRepository professorRepository, AuthClient authClient) {
        this.professorRepository = professorRepository;
        this.authClient = authClient;
    }

    @Transactional
    public void cadastrarProfessor(ProfessorCadastroDTO professorCadastroDTO){
        if (professorRepository.findByEmail(professorCadastroDTO.email()).isPresent()){
            throw new RuntimeException("Professor com esse E-mail já cadastrado");
        }
        if (professorRepository.findByMatricula(professorCadastroDTO.matricula()).isPresent()){
            throw new RuntimeException("Matrícula já cadastrada");
        }

        Professor professor = new Professor();
        professor.setMatricula(professorCadastroDTO.matricula());
        professor.setEmail(professorCadastroDTO.email());
        professor.setAtivo(true);
        professor.setEscolaId(professorCadastroDTO.escolaId());
        professor.setTelefone(professorCadastroDTO.telefone());
        professor.setNomeCompleto(professorCadastroDTO.nomeCompleto());
        professorRepository.save(professor);

        UsuarioRegisterDTO authDto = new UsuarioRegisterDTO(professorCadastroDTO.email(), professorCadastroDTO.senha());
       try {
           authClient.cadastrarProfessor(authDto);
       } catch (Exception e) {
           throw new RuntimeException("Erro ao comunicar com serviço de autenticação.");
       }
    }

    public List<ProfessorResponseDTO> listProfessores(Long escolaId, Boolean ativo){
        List<Professor> professores;

        if (escolaId != null && ativo!=null){
            professores = ativo ? professorRepository.findAllByEscolaIdAndAtivoTrue(escolaId) : professorRepository.findAllByEscolaId(escolaId);
        }else if(escolaId != null){
            professores = professorRepository.findAllByEscolaId(escolaId);
        }else if(ativo != null){
            professores = professorRepository.findAllByEscolaIdAndAtivoTrue(escolaId);
        }else professores = professorRepository.findAll();

        return professores.stream().map(professor ->
                new ProfessorResponseDTO(professor.getId(), professor.getMatricula(), professor.getNomeCompleto(), professor.getEmail(), professor.getTelefone(), professor.getEscolaId(), professor.getAtivo())).toList();
    }


    public ProfessorResponseDTO atualizarDadosProfessor(Long id, ProfessorUpdateDTO professorUpdateDTO){
        Professor professor = buscarProfessorId(id);

        if (professorUpdateDTO.nomeCompleto() != null) professor.setNomeCompleto(professorUpdateDTO.nomeCompleto());
        if (professorUpdateDTO.email() != null) professor.setEmail(professorUpdateDTO.email());
        if (professorUpdateDTO.telefone() != null) professor.setTelefone(professorUpdateDTO.telefone());

        Professor updateProfessor = professorRepository.save(professor);
        return new ProfessorResponseDTO(
                updateProfessor.getId(),
                updateProfessor.getMatricula(),
                updateProfessor.getNomeCompleto(),
                updateProfessor.getEmail(),
                updateProfessor.getTelefone(),
                updateProfessor.getEscolaId(),
                updateProfessor.getAtivo()
        );
    }

    public  void ativarOuInativarProfessor(Long id, ProfessorStatusDTO professorStatusDTO){
        Professor professor = buscarProfessorId(id);
        professor.setAtivo(professorStatusDTO.ativo());
        professorRepository.save(professor);
    }
    private Professor buscarProfessorId(Long id) {
        return professorRepository.findById(id).orElseThrow(() -> new RuntimeException("Professor não encontrado"));
    }
}
