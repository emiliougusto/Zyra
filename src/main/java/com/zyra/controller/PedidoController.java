package com.zyra.controller;

import com.zyra.dto.PedidoDto;
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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/pedidos")
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

        if (pedidoDto.produtosIds() == null || pedidoDto.produtosIds().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A lista de produtos não pode ser vazia.");
        }

        // cria o pedido
        var pedidoModel = new PedidoModel();
        pedidoModel.setDataPedido(pedidoDto.dataPedido());
        pedidoModel.setTotalPedido(pedidoDto.totalPedido());
        pedidoModel.setUsuario(usuarioOpt.get());
        List<ProdutoModel> produtos = produtoRepository.findAllById(pedidoDto.produtosIds());

        // verifica se todos os produtos foram encontrados
        if (produtos.size() != pedidoDto.produtosIds().size()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Um ou mais produtos não foram encontrados.");
        }

        // associa os produtos ao pedido
        pedidoModel.setProdutos(new ArrayList<>(produtos));

        var pedidoSalvo = pedidoRepository.save(pedidoModel);

        // resposta da criação do pedido
        var resposta = new PedidoDto(
                pedidoSalvo.getIdPedido(),
                pedidoSalvo.getDataPedido(),
                pedidoSalvo.getTotalPedido(),
                usuarioOpt.get().getIdUsuario(),
                usuarioOpt.get().getEmail(),
                pedidoDto.produtosIds()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

    @GetMapping("{idPedido}")
    public ResponseEntity<Object> getPedido(@PathVariable Integer idPedido) {
        Optional<PedidoModel> pedidoOpt = pedidoRepository.findById(idPedido);
        if (pedidoOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido não encontrado.");
        }

        PedidoModel pedidoModel = pedidoOpt.get();
        List<Integer> produtosIds = pedidoModel.getProdutos().stream()
                .map(ProdutoModel::getIdProduto)
                .collect(Collectors.toList());

        var pedidoDto = new PedidoDto(
                pedidoModel.getIdPedido(),
                pedidoModel.getDataPedido(),
                pedidoModel.getTotalPedido(),
                pedidoModel.getUsuario().getIdUsuario(),
                pedidoModel.getUsuario().getEmail(),
                produtosIds
        );
        return ResponseEntity.status(HttpStatus.OK).body(pedidoDto);
    }
}
