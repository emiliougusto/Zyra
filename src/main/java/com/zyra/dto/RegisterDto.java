package com.zyra.dto;

import com.zyra.model.usuario.RoleUsuario;

public record RegisterDto(
        String email,
        String senhaUsuario,
        RoleUsuario roleUsuario
) {
}

