package com.lauradcode.usuario.business.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UsuarioDTO { /*primeiro estou criando aqui, depois a classe Endereco e Telefone*/
    private String nome;
    private String email;
    private String senha;
    private List<EnderecoDTO> enderecos; /*o que está dentro <> é a classe*/
    private List<TelefoneDTO> telefones;
}
