package com.zyra.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter

@Entity
@Table(name = "TB_PRODUTO")
public class ProdutoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PRODUTO")
    private Integer idProduto;
    @Column(name = "NM_PRODUTO")
    private String nmProduto;
    @Column(name = "DS_PRODUTO")
    private String dsProduto;
    @Column(name = "VL_PRODUTO")
    private Double vlProduto;
}

