package com.notepad.project.repository;

import com.notepad.project.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    public Usuario findByCorreo(String email);

        Usuario findByCorreoAndClave(String correo, String clave);

}
