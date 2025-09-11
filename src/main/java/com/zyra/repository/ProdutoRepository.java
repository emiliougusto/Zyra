package com.zyra.repository;

import com.zyra.model.ProdutoModel;
import com.zyra.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<ProdutoModel, Integer> {
    Optional<UsuarioModel> findByIdProduto(Integer idProduto);
    Optional<UsuarioModel> findAllByIdProduto(int idProduto);
}
