package com.notepad.project.service;

import com.notepad.project.models.Usuario;
import com.notepad.project.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class UsuarioService{

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

    public Usuario update(Long id, Usuario usuario) {
        Usuario usuarioBBDD = usuarioRepository.findById(id).orElse(null);
        if (usuarioBBDD != null) {
            usuarioBBDD.setCorreo(usuario.getCorreo());
            usuarioBBDD.setClave(usuario.getClave());
            return usuarioRepository.save(usuarioBBDD);
        }
        return null;
    }

    @Transactional
    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Transactional
    public Usuario findByCorreoAndClave(String correo, String clave) {
        try {
            return usuarioRepository.findByCorreoAndClave(correo, clave);
        } catch (Exception e) {
            return null;
        }
    }




}
