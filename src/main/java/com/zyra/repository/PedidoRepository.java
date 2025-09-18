package com.zyra.repository;

import com.zyra.dto.PedidoDto;
import com.zyra.model.PedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoModel, Integer> {
    @Query("SELECT new com.zyra.dto.PedidoDto(p.idPedido, p.dataPedido, p.totalPedido, u.idUsuario, u.email, null) " +
           "FROM PedidoModel p JOIN p.usuario u WHERE p.idPedido = :idPedido")
    Optional<PedidoDto> findPedidoDtoById(Integer idPedido);
}
