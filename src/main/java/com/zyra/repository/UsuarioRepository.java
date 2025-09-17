package com.zyra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Integer> {
    Optional<UsuarioModel> findByIdUsuario(Integer idUsuario);
    Optional<UsuarioModel> findAllByIdUsuario(int idUsuario);
    UserDetails findByEmail(String email);
}