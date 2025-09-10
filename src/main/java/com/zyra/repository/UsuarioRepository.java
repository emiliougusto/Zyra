package com.zyra.repository;

import com.zyra.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Integer> {
    Optional<UsuarioModel> findByIdUsuario(Integer idUsuario);
    Optional<UsuarioModel> findAllByIdUsuario(int idUsuario);
}
