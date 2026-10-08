package com.lauradcode.usuario.business.converter;

import com.lauradcode.usuario.business.dto.EnderecoDTO;
import com.lauradcode.usuario.business.dto.TelefoneDTO;
import com.lauradcode.usuario.business.dto.UsuarioDTO;
import com.lauradcode.usuario.infrastructure.entity.Endereco;
import com.lauradcode.usuario.infrastructure.entity.Telefone;
import com.lauradcode.usuario.infrastructure.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component

public class UsuarioConverter {

    public Usuario paraUsuario(UsuarioDTO usuarioDTO){ /*entender melhor esse 'paraUsuario*/
        return Usuario.builder()
                .nome(usuarioDTO.getNome()) /*dentro no atributo nome da entidade usuario (por isso Usuario.builder) estamos passando o usuarioDTO.getnome*/
                .email(usuarioDTO.getEmail())
                .senha(usuarioDTO.getSenha())
                .enderecos(paraListaEndereco(usuarioDTO.getEnderecos())) /*aqui vamos criar um novo metod para transformar a lista de endereco antes de preencher o parâmetro que é o paraListaEndereco*/
                .telefones(paraListaTelefone(usuarioDTO.getTelefones())) /*por ser uma lista, faremos o mesmo que fizemos para o endereco*/
                .build();
    }

    public List<Endereco> paraListaEndereco (List<EnderecoDTO> enderecoDTOS){ /*para converter essa lista, tem que criar outro metodo para converter apenas UM edereco (metodo abaixo)*/
        List<Endereco> enderecos = new ArrayList<>(); /*ENTENDER MELHOR NO 14:21*/
        for(EnderecoDTO enderecoDTO : enderecoDTOS) {
            enderecos.add(paraEndereco(enderecoDTO));
        }
        return enderecos;
    }

    public Endereco paraEndereco (EnderecoDTO enderecoDTO){ /*metodo para conversão de 1 endereço*/
        return Endereco.builder()
                .rua(enderecoDTO.getRua())
                .numero(enderecoDTO.getNumero())
                .cidade(enderecoDTO.getCidade())
                .complemento(enderecoDTO.getComplemento())
                .cep(enderecoDTO.getCep())
                .estado(enderecoDTO.getEstado())
                .build();
    }

    public List<Telefone> paraListaTelefone (List<TelefoneDTO> telefonesDTOS){
        List<Telefone> telefones = new ArrayList<>(); /*ENTENDER MELHOR a construção*/
        for(TelefoneDTO telefoneDTO : telefonesDTOS) {
            telefones.add(paraTelefone(telefoneDTO));
        }
        return telefones;
    }

    public Telefone paraTelefone (TelefoneDTO telefoneDTO){
        return Telefone.builder()
                .numero(telefoneDTO.getNumero())
                .ddd(telefoneDTO.getDdd())
                .build();
    }


    /*CONVERTENDO ENTITY P DTO*/


    public UsuarioDTO paraUsuarioDTO(Usuario usuarioDTO){ /*entender melhor esse 'paraUsuario*/
        return UsuarioDTO.builder()
                .nome(usuarioDTO.getNome()) /*dentro no atributo nome da entidade usuario (por isso Usuario.builder) estamos passando o usuarioDTO.getnome*/
                .email(usuarioDTO.getEmail())
                .senha(usuarioDTO.getSenha())
                .enderecos(paraListaEnderecoDTO(usuarioDTO.getEnderecos())) /*aqui vamos criar um novo metod para transformar a lista de endereco antes de preencher o parâmetro que é o paraListaEndereco*/
                .telefones(paraListaTelefoneDTO(usuarioDTO.getTelefones())) /*por ser uma lista, faremos o mesmo que fizemos para o endereco*/
                .build();
    }

    public List<EnderecoDTO> paraListaEnderecoDTO (List<Endereco> enderecoDTOS){ /*para converter essa lista, tem que criar outro metodo para converter apenas UM edereco (metodo abaixo)*/
        List<EnderecoDTO> enderecos = new ArrayList<>(); /*ENTENDER MELHOR NO 14:21*/
        for(Endereco enderecoDTO : enderecoDTOS) {
            enderecos.add(paraEnderecoDTO(enderecoDTO));
        }
        return enderecos;
    }

    public EnderecoDTO paraEnderecoDTO (Endereco endereco){ /*metodo para conversão de 1 endereço*/
        return EnderecoDTO.builder()

                .id(endereco.getId())
                .rua(endereco.getRua())
                .numero(endereco.getNumero())
                .cidade(endereco.getCidade())
                .complemento(endereco.getComplemento())
                .cep(endereco.getCep())
                .estado(endereco.getEstado())
                .build();
    }

    public List<TelefoneDTO> paraListaTelefoneDTO (List<Telefone> telefonesDTOS){
        List<TelefoneDTO> telefones = new ArrayList<>(); /*ENTENDER MELHOR a construção*/
        for(Telefone telefoneDTO : telefonesDTOS) {
            telefones.add(paraTelefoneDTO(telefoneDTO));
        }
        return telefones;
    }

    public TelefoneDTO paraTelefoneDTO (Telefone telefone){
        return TelefoneDTO.builder()

                .id(telefone.getId())
                .numero(telefone.getNumero())
                .ddd(telefone.getDdd())
                .build();
    }

    public Usuario updateUsuario (UsuarioDTO usuarioDTO, Usuario entity){
        return Usuario.builder()
                .nome(usuarioDTO.getNome() != null ? usuarioDTO.getNome() : entity.getNome())
                .id(entity.getId())
                .senha(usuarioDTO.getSenha() != null ? usuarioDTO.getSenha() : entity.getSenha())
                .email(usuarioDTO.getEmail() != null ? usuarioDTO.getEmail() : entity.getEmail())
                .enderecos(entity.getEnderecos())
                .telefones(entity.getTelefones())

                .build();
    }

    public Endereco updateEndereco (EnderecoDTO dto, Endereco entity){
        return Endereco.builder()
                .id(entity.getId())
                .rua(dto.getRua() != null ? dto.getRua() : entity.getRua())
                .numero(dto.getNumero() != null ? dto.getNumero() : entity.getNumero())
                .cidade(dto.getCidade() != null ? dto.getCidade() : entity.getCidade())
                .cep(dto.getCep() != null ? dto.getCep() : entity.getCep())
                .complemento(dto.getComplemento() != null ? dto.getComplemento() : entity.getComplemento())
                .estado(dto.getEstado() != null ? dto.getEstado() : entity.getEstado())

                .build();
    }

    public Telefone updateTelefone (TelefoneDTO dto, Telefone entity){
        return Telefone.builder()
                .id(entity.getId())
                .numero(dto.getNumero() != null ? dto.getNumero() : entity.getNumero())
                .ddd(dto.getDdd() != null ? dto.getDdd() : entity.getDdd())

                .build();
    }

}
