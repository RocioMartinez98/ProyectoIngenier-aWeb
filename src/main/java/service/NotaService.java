package service;

import models.Nota;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.NotaRepository;

import java.util.List;

@Service
public class NotaService {
    private final NotaRepository notaRepository;

    public NotaService(NotaRepository notaRepository) {
        this.notaRepository = notaRepository;
    }

    @Transactional
    public List<Nota> findAllNotas(){
        return notaRepository.findAll();
    }

    @Transactional
    public Nota findById(Long id){
        return notaRepository.findById(id).orElse(null);
    }

    @Transactional
    public Nota create(Nota nota){
        return notaRepository.save(nota);
    }

    @Transactional
    public void delete(long id){
        notaRepository.deleteById(id);
    }


}
