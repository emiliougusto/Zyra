package com.zyra.controller;

import com.zyra.dto.AuthenticationDto;
import com.zyra.dto.LoginResponseDto;
import com.zyra.dto.RegisterDto;
import com.zyra.model.usuario.UsuarioModel;
import com.zyra.repository.UsuarioRepository;
import com.zyra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDto data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.senhaUsuario());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.gerarToken((UsuarioModel) auth.getPrincipal() );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new LoginResponseDto(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDto data) {
        if(this.usuarioRepository.findByEmail(data.email()).isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Email já cadastrado");
        }

        // Criptografa a senha antes de salvar
        String senhaCriptografada = new BCryptPasswordEncoder().encode(data.senhaUsuario());

        UsuarioModel novoUsuario = new UsuarioModel(data.email(), senhaCriptografada, data.roleUsuario());

        this.usuarioRepository.save(novoUsuario);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Usuário registrado com sucesso");
    }
}
