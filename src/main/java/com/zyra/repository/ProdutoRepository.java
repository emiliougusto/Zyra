package com.zyra.repository;

import com.zyra.model.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<ProdutoModel, Integer> {
    Optional<ProdutoModel> findByIdProduto(Integer idProduto);
    Optional<ProdutoModel> findAllByIdProduto(int idProduto);
}
