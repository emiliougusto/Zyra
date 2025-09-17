package com.zyra.model.usuario;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data

@Entity
@Table(name = "TB_USUARIO",
        uniqueConstraints={
                @UniqueConstraint(columnNames={"DS_EMAIL"})})
public class UsuarioModel implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO")
    private Integer idUsuario;
    @Column(name = "DS_EMAIL")
    private String email;
    @Column(name = "DS_SENHA")
    private String senhaUsuario;
    @Column(name = "ROLE_USUARIO")
    private RoleUsuario roleUsuario;

    public UsuarioModel(String email, String senhaUsuario, RoleUsuario roleUsuario) {
        this.email = email;
        this.senhaUsuario = senhaUsuario;
        this.roleUsuario = roleUsuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.roleUsuario == RoleUsuario.ADMIN) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        } else {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    @Override
    public String getPassword() {
        return senhaUsuario;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}