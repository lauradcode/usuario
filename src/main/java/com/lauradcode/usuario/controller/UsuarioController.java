package com.lauradcode.usuario.controller;

import com.lauradcode.usuario.business.UsuarioService;
import com.lauradcode.usuario.business.dto.UsuarioDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor

public class UsuarioController {

    private final UsuarioService usuarioService; /*injetando dependencia da classe service*/

    @PostMapping
    public ResponseEntity<UsuarioDTO> salvaUsuario (@RequestBody UsuarioDTO usuarioDTO){/*QUAL A DIFERENÇA DESSE SALVA USUARIO DDA CONTROLLER PAA A DA SERVICE?A A LINHA 22 ESTÁ CHAMANDO O SALVA USUARIO DA SERVICE OU ESTÁ ACOPLADO COM O MÉTODO DA CONTROLLER?*/
       return  ResponseEntity.ok(usuarioService.salvaUsuario(usuarioDTO)); /*QUAL A DIFERENÇA DO RESPONSE DE CIMA PARA ESSE OK DE BAIXO*/
    }
}
