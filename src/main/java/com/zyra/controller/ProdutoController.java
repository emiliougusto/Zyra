package com.zyra.controller;

import com.zyra.dto.ProdutoDto;
import com.zyra.dto.UsuarioDto;
import com.zyra.model.ProdutoModel;
import com.zyra.repository.ProdutoRepository;
import com.zyra.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/produtos")

public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping
    public ResponseEntity<ProdutoModel> salvarProduto(@RequestBody
                                                          @Valid ProdutoDto produtoDto) {
        var produtoModel = new ProdutoModel();
        BeanUtils.copyProperties(produtoDto, produtoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produtoModel));
    }

    @GetMapping("/{idProduto}")
    public ResponseEntity<Object> getProduto(@PathVariable(value = "idProduto") Integer idProduto) {
        Optional<ProdutoModel> produtoModel = produtoRepository.findById(idProduto);
        if (produtoModel.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado.");
        }
        return ResponseEntity.ok(produtoModel);
    }
}
