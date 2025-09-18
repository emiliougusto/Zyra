package com.zyra.repository;

import com.zyra.model.PedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoModel, Integer> {

    @Query("SELECT dataPedido, totalPedido  FROM PedidoModel WHERE usuario.idUsuario = :idUsuario")
    Optional<PedidoModel> findByUsuarioId(Integer idUsuario);

    Optional<PedidoModel> findById(Integer idPedido);
}
