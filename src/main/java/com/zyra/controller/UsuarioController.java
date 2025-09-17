package com.zyra.controller;

import com.zyra.dto.UsuarioDto;
import com.zyra.model.usuario.UsuarioModel;
import com.zyra.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/usuarios")

public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<UsuarioModel> salvarUsuario(@RequestBody
                                                      @Valid UsuarioDto usuarioDto) {
        var usuarioModel = new UsuarioModel();
        BeanUtils.copyProperties(usuarioDto, usuarioModel);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(usuarioRepository
                        .save(usuarioModel));
    }
    @GetMapping
    public ResponseEntity<List<UsuarioModel>> listarUsuarios() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(usuarioRepository.findAll());
    }
    @GetMapping("/api/v1/usuarios/{idUsuario}")
    public ResponseEntity<Object> getUsuario(@PathVariable(value = "idUsuario") Integer idUsuario) {
        Optional<UsuarioModel> usuarioModel = usuarioRepository.findById(idUsuario);
        if (usuarioModel.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Usuário não encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuarioModel.get());
    }
}
