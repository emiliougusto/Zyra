package com.zyra.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zyra.model.usuario.UsuarioModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "TB_PEDIDO")
public class PedidoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PEDIDO")
    private Integer idPedido;
    @Column(name = "DATA_PEDIDO")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataPedido;
    @Column(name = "TOTAL_PEDIDO")
    private Double totalPedido;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    private UsuarioModel usuario;

    @ManyToMany
    @JoinTable(
            name = "TB_PEDIDO_PRODUTO",
            joinColumns = @JoinColumn(name = "ID_PEDIDO"),
            inverseJoinColumns = @JoinColumn(name = "ID_PRODUTO")
    )
    private java.util.List<ProdutoModel> produtos;
}
