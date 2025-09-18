package com.zyra.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.util.List;

public record PedidoDto(
        Integer idPedido,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
        LocalDate dataPedido,
        Double totalPedido,
        Integer idUsuario,
        String emailUsuario,
        List<Integer> produtosIds
) {}