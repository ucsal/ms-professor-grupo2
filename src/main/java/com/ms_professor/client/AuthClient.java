package com.ms_professor.client;

import com.ms_professor.domain.Professor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ms-auth", url = "http://localhost:8080/api/auth")
public interface AuthClient {
    @PostMapping("/register/professor")
    void cadastrarProfessor(@RequestBody UsuarioRegisterDTO usuarioRegisterDTO);
}
