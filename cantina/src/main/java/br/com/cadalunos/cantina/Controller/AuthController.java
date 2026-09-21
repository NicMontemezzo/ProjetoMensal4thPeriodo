package br.com.cadalunos.cantina.Controller;

import br.com.cadalunos.cantina.DTO.LoginRequest;
import br.com.cadalunos.cantina.DTO.LoginResponse;
import br.com.cadalunos.cantina.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }
}