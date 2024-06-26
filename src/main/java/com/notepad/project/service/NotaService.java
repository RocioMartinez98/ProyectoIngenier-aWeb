package com.notepad.project.service;

import com.notepad.project.models.Nota;
import com.notepad.project.models.Usuario;
import com.notepad.project.repository.NotaRepository;
import com.notepad.project.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NotaService {

    private static final Logger logger = LoggerFactory.getLogger(NotaService.class);

    private final NotaRepository notaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public NotaService(NotaRepository notaRepository) {
        this.notaRepository = notaRepository;
    }



    @Transactional
    public List<Nota> findAllNotas() {
        return notaRepository.findAll();
    }

    @Transactional
    public List<Nota> findByTitulo(String titulo) {
        logger.info("Búsqueda por título: {}", titulo); // Verificar la búsqueda realizada
        return notaRepository.findByTituloContainingIgnoreCase(titulo);
    }

    @Transactional
    public Nota findById(Long id) {
        return notaRepository.findById(id).orElse(null);
    }

    @Transactional
    public Nota create(Nota nota) {
        return notaRepository.save(nota);
    }

    @Transactional
    public void delete(Long id) {
        notaRepository.deleteById(id);
    }

    public Nota update(Long id, Nota nota) {
        Nota notaBBDD = notaRepository.findById(id).orElse(null);
        if (notaBBDD != null) {
            notaBBDD.setTitulo(nota.getTitulo());
            notaBBDD.setTexto(nota.getTexto());
            return notaRepository.save(notaBBDD);
        }
        return null;
    }

    @Transactional
    public void eliminarNota(Long usuarioId, Long notaId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Nota nota = notaRepository.findById(notaId)
                .orElseThrow(() -> new RuntimeException("Nota no encontrada"));

        usuario.eliminarNota(nota);
        usuarioRepository.save(usuario);

        notaRepository.delete(nota);
    }


}
