package com.notepad.project.service;


import com.notepad.project.models.Usuario;
import com.notepad.project.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public List<Usuario> findAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @Transactional
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Transactional
    public Usuario create(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }
}
