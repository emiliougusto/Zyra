package com.zyra.controller;

import com.zyra.dto.PedidoDto;
import com.zyra.dto.ProdutoDto;
import com.zyra.model.PedidoModel;
import com.zyra.model.ProdutoModel;
import com.zyra.repository.PedidoRepository;
import com.zyra.repository.ProdutoRepository;
import com.zyra.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/pedidos")

public class PedidoController {
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping
    public ResponseEntity<Object> salvarPedido(@RequestBody @Valid PedidoDto pedidoDto) {
        var usuarioOpt = usuarioRepository.findById(pedidoDto.idUsuario());
        if (usuarioOpt.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Usuário não encontrado.");
        }

        var pedidoModel = new PedidoModel();
        pedidoModel.setDataPedido(pedidoDto.dataPedido());
        pedidoModel.setTotalPedido(pedidoDto.totalPedido());
        pedidoModel.setUsuario(usuarioOpt.get());

        var pedidoSalvo = pedidoRepository.save(pedidoModel);

        var resposta = new PedidoDto(
                pedidoSalvo.getIdPedido(),
                pedidoSalvo.getDataPedido(),
                pedidoSalvo.getTotalPedido(),
                usuarioOpt.get().getIdUsuario(),
                usuarioOpt.get().getEmail()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

    @GetMapping("{idPedido}")
    public ResponseEntity<Object> getPedido(@PathVariable(value = "idPedido") Integer idPedido) {
        Optional<PedidoDto> pedido = pedidoRepository.findPedidoWithUsuarioById(idPedido);

        if (pedido.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido não encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(pedido.get());
    }
}
