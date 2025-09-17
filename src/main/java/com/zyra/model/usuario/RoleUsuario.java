package com.zyra.model.usuario;

public enum RoleUsuario {
    ADMIN("admin"),
    USER("user");

    private String roleUsuario;

    RoleUsuario(String roleUsuario) {
        this.roleUsuario = roleUsuario;
    }

    public String getRoleUsuario() {
        return roleUsuario;
    }
}