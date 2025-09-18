package com.zyra.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Value;

public record ProdutoDto(
        String nmProduto,

        @NotBlank(message = "Não é possível cadastrar produto sem descrição")
        @Size(min = 5, max = 500, message = "A descrição do produto deve ter entre 5 e 500 caracteres")
        String dsProduto,

        @NotNull(message = "Não é possível cadastrar produto sem preço")
        @DecimalMin(value = "0.01", message = "O valor do produto deve ser maior que 0.01")
        Double vlProduto
) {
}
