package com.zyra.controller;

import com.zyra.dto.UsuarioDto;
import com.zyra.model.UsuarioModel;
import com.zyra.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        var clienteModel = new UsuarioModel();
        BeanUtils.copyProperties(usuarioDto, clienteModel);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(clienteModel);
    }
    @GetMapping("/{idUsuario}")
    public ResponseEntity<Object> getCliente(@PathVariable(value = "idUsuario") Integer idUsuario) {
        Optional<UsuarioModel> usuarioModel = usuarioRepository.findById(idUsuario);
        if (usuarioModel.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Usuário não encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuarioModel.get());
    }
}
