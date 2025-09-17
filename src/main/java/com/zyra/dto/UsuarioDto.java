package com.zyra.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioDto(
        @NotBlank(message = "Não é possível cadastrar usuário sem nome")
        @Size(min = 2, max = 100, message = "O nome do usuário deve ter entre 2 e 100 caracteres")
        String nmUsuario,

        @Email(message = "O email deve ser válido")
        @NotBlank(message = "Não é possível cadastrar usuário sem email")
        @Size(min = 5, max = 100, message = "O email do usuário deve ter entre 5 e 100 caracteres")
        String email,

        @NotBlank(message = "Não é possível cadastrar usuário sem senha")
        @Size(min = 6, max = 30, message = "A senha do usuário deve ter entre 6 e 30 caracteres")
        String senhaUsuario

) {
}