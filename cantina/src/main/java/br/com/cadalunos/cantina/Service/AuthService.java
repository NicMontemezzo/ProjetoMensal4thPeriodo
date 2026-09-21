package br.com.cadalunos.cantina.Service;

import br.com.cadalunos.cantina.DTO.LoginRequest;
import br.com.cadalunos.cantina.DTO.LoginResponse;
import br.com.cadalunos.cantina.Entity.Usuario;
import br.com.cadalunos.cantina.Security.JwtUtil;
import br.com.cadalunos.cantina.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public LoginResponse login(LoginRequest request) {
        Usuario usuario = usuarioRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário ou senha inválidos"));

        if (!passwordEncoder.matches(request.getSenha(), usuario.getSenha())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário ou senha inválidos");
        }

        String token = jwtUtil.generateToken(usuario.getUsername(), usuario.getRole());
        return new LoginResponse(token, usuario.getUsername(), usuario.getRole());
    }
}