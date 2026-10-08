package com.lauradcode.usuario.controller;

import com.lauradcode.usuario.business.UsuarioService;
import com.lauradcode.usuario.business.dto.EnderecoDTO;
import com.lauradcode.usuario.business.dto.TelefoneDTO;
import com.lauradcode.usuario.business.dto.UsuarioDTO;
import com.lauradcode.usuario.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor

public class UsuarioController {

    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<UsuarioDTO> salvaUsuario (@RequestBody UsuarioDTO usuarioDTO){/*QUAL A DIFERENÇA DESSE SALVA USUARIO DDA CONTROLLER PAA A DA SERVICE?A A LINHA 22 ESTÁ CHAMANDO O SALVA USUARIO DA SERVICE OU ESTÁ ACOPLADO COM O MÉTODO DA CONTROLLER?*/
       return  ResponseEntity.ok(usuarioService.salvaUsuario(usuarioDTO)); /*QUAL A DIFERENÇA DO RESPONSE DE CIMA PARA ESSE OK DE BAIXO*/
    }

    @PostMapping("/login")
    public String login(@RequestBody UsuarioDTO usuarioDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(usuarioDTO.getEmail(),
                        usuarioDTO.getSenha())
        );
        return jwtUtil.generateToken(authentication.getName());
    }

    @GetMapping
    public ResponseEntity<UsuarioDTO> buscaUsuarioPorEmail(@RequestParam("email") String email){
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deletaUsuarioPorEmail(@PathVariable String email){
        usuarioService.deletaUsuarioPorEmail(email);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<UsuarioDTO> atualizaDadosUsuario (@RequestBody UsuarioDTO dto,
                                                            @RequestHeader ("Authorization") String token) {
        return ResponseEntity.ok(usuarioService.atualizaDadosUsuario(token, dto));
    }

    @PutMapping("/endereco")
    public ResponseEntity<EnderecoDTO> atualizaEndereco (@RequestBody EnderecoDTO dto,
                                                         @RequestParam("id") Long id){
        return ResponseEntity.ok(usuarioService.atualizaEndereco(id, dto));
    }

    @PutMapping("/telefone")
    public ResponseEntity<TelefoneDTO> atualizaTelefone (@RequestBody TelefoneDTO dto,
                                                         @RequestParam("id") Long id){
        return ResponseEntity.ok(usuarioService.atualizaTelefone(id, dto));
    }
}

