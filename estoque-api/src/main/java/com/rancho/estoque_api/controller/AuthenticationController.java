package com.rancho.estoque_api.controller;

import com.rancho.estoque_api.dto.AuthenticationDTO;
import com.rancho.estoque_api.dto.LoginResponseDTO;
import com.rancho.estoque_api.dto.RegisterDTO;
import com.rancho.estoque_api.model.Usuario;
import com.rancho.estoque_api.repository.UsuarioRepository;
import com.rancho.estoque_api.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository repository;
    private final TokenService tokenService;

    public AuthenticationController(AuthenticationManager authenticationManager,
                                    UsuarioRepository repository,
                                    TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.repository = repository;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.nomeGuerra(), data.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.gerarToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO data) {
        if (this.repository.findByNomeGuerra(data.nomeGuerra()).isPresent()) {
            return ResponseEntity.badRequest().body("Nome de guerra já cadastrado.");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.senha());
        Usuario newUser = new Usuario();
        newUser.setNomeGuerra(data.nomeGuerra());
        newUser.setSenha(encryptedPassword);
        newUser.setRole(data.role());

        this.repository.save(newUser);
        return ResponseEntity.ok().build();
    }
}