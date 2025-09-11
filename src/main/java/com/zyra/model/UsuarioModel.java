package com.zyra.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
@Table(name = "TB_USUARIO")
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO")
    private Integer idUsuario;
    @Column(name = "NM_USUARIO")
    private String nmUsuario;
    @Column(name = "DS_EMAIL")
    private String emailUsuario;
    @Column(name = "DS_SENHA")
    private String senhaUsuario;
}
