package com.notepad.project.service;

import com.notepad.project.models.Nota;
import com.notepad.project.repository.NotaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NotaService {
    private final NotaRepository notaRepository;

    public NotaService(NotaRepository notaRepository) {
        this.notaRepository = notaRepository;
    }

    @Transactional
    public List<Nota> findAllNotas() {
        return notaRepository.findAll();
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


}