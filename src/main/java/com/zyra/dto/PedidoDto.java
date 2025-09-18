package com.zyra.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record PedidoDto(
        @NotNull(message = "O ID do usuário não pode ser nulo")
        Integer idUsuario,
        @NotBlank(message = "A data do pedido não pode ser vazia")
        LocalDate dataPedido,
        @NotNull(message = "O total do pedido não pode ser nulo")
        Double totalPedido
) {
}
