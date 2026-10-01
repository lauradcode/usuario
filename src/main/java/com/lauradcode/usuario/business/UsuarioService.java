package com.lauradcode.usuario.business;

import com.lauradcode.usuario.business.converter.UsuarioConverter;
import com.lauradcode.usuario.business.dto.UsuarioDTO;
import com.lauradcode.usuario.infrastructure.entity.Usuario;
import com.lauradcode.usuario.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;

    public UsuarioDTO salvaUsuario (UsuarioDTO usuarioDTO){/*o que está acontecendo aqui: p salvar um usuario, recebemos um usuarioDTO, transformou em tntity, salvo no banco, o banco retorna como entity e convertemos em dto*/
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        return usuarioConverter.paraUsuarioDTO(
                usuarioRepository.save(usuario));
    }
}
