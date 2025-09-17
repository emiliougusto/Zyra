package com.zyra.controller;

import com.zyra.dto.PedidoDto;
import com.zyra.model.PedidoModel;
import com.zyra.model.usuario.UsuarioModel;
import com.zyra.repository.PedidoRepository;
import com.zyra.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/pedidos/")

public class PedidoController {
    @Autowired
    private PedidoRepository pedidoRepository;

    @PostMapping
    public ResponseEntity<PedidoModel> salvarPedido(@RequestBody
                                                    @Valid PedidoDto pedidoDto) {
        var pedidoModel = new PedidoModel();
        BeanUtils.copyProperties(pedidoDto, pedidoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoRepository.save(pedidoModel));
    }

    @GetMapping("{idUsuario}")
    public ResponseEntity<Object> getPedido(@PathVariable(value = "idUsuario") Integer idUsuario) {
        Optional<PedidoModel> pedidoModel = pedidoRepository.findByUsuarioId(idUsuario);

        if (pedidoModel.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido não encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(pedidoModel);
    }
}
